package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import utils.ConfigReader;
import utils.DriverFactory;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverFactory.initDriver();
        driver.get(ConfigReader.get("base.url"));
        homePage = new HomePage(driver);
        homePage.dismissPopups();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
