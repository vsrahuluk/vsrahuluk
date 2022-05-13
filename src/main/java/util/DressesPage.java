package util;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DressesPage {

    WebDriver driver;

    public DressesPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='right-block']//span[@itemprop='price']")
    List<WebElement> dressesprice;

    @FindBy(xpath = "//span[@id='layer_cart_product_price']")
    WebElement totalCartPrice;

    Float highestPrice;
    public void addHighestPriceDressToCart()
    {
        List<Float> mylist = new ArrayList<>();
        for(WebElement dressprice:dressesprice)
        {
            mylist.add(Float.parseFloat(dressprice.getText().replace("$","")));
        }
        highestPrice = Collections.max(mylist);
        System.out.println(Collections.max(mylist));
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//span[contains(text(),'"+Collections.max(mylist)+"')]/following::a[1]/span"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void verifyCartItemPrice()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(totalCartPrice));
        Float getCartTotalPrice = Float.parseFloat(totalCartPrice.getText().replace("$",""));
        Assert.assertEquals(highestPrice,getCartTotalPrice);
    }

}
