package StepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import util.ConfigReader;
import util.DressesPage;
import util.HomePage;
import util.NavigationPage;

import java.time.Duration;


public class SeleniumAutomationTestStepDefs {

    WebDriver driver ;
    NavigationPage navigationPage;
    HomePage homePage ;
    DressesPage dressesPage;
    ConfigReader configReader = new ConfigReader();

    @Before
    public void setUpBrowser() throws Exception {

        String os = System.getProperty("os.name").toLowerCase();
        String browser = configReader.getBrowser();
        if(browser.equalsIgnoreCase("firefox")) {
            if (os.contains("mac")) {
                System.setProperty("webdriver.gecko.driver", "browsers/geckodriver");
            } else {
                System.setProperty("webdriver.gecko.driver", "browsers/geckodriver.exe");
            }
            driver = new FirefoxDriver();
        }
        else if(browser.equalsIgnoreCase("chrome"))
        {
            if(os.contains("mac")) {
                System.setProperty("webdriver.chrome.driver", "browsers/chromedriver");
            }else {
                System.setProperty("webdriver.chrome.driver", "browsers/chromedriver.exe");
            }
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("edge"))
        {
            if(os.contains("mac")) {
                System.setProperty("webdriver.edge.driver", "browsers/msedgedriver");
            }else {
                System.setProperty("webdriver.edge.driver", "browsers/msedgedriver.exe");
            }
            driver = new EdgeDriver();
        }
        else
        {
            throw new Exception("Browser not found");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @io.cucumber.java.en.Given("^open browser and navigate to url$")
    public void openBrowserAndNavigateToUrl() throws Exception {

        navigationPage = new NavigationPage(driver);
        navigationPage.NavigateToURL();
        navigationPage.verifyHomePage();

    }

    @When("click on dresses and get the prices of all dresses displayed")
    public void clickOnDressesAndGetThePricesOfAllDressesDisplayed() throws Exception {

        homePage = new HomePage(driver);
        homePage.clickOnDresses();
    }

    @Then("add the dress with highest price to the cart")
    public void addTheDressWithHighestPriceToTheCart() {

        dressesPage = new DressesPage(driver);
        dressesPage.addHighestPriceDressToCart();
        dressesPage.verifyCartItemPrice();
    }

    @After
    public void quitBrowser()
    {
        driver.close();
        driver.quit();
    }


}
