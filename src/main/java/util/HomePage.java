package util;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.junit.Assert;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@id='category-thumbnail']/following::a[text()='Dresses']")
     WebElement dressesLink;


    public void clickOnDresses()
    {
        dressesLink.click();
    }

}
