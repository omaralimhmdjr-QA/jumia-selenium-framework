package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("explicit.wait.seconds")));
    }

    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            el.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }

    protected void type(By locator, String text) {
        WebElement el = waitVisible(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected String getText(By locator) {
        return waitVisible(locator).getText().trim();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return waitVisible(locator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    protected List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    /** Closes cookie / newsletter popups if they show up. Never fails the test. */
    public void dismissPopups() {
        By[] popups = {
                By.cssSelector("button[aria-label='newsletter_popup_close-cta']"),
                By.cssSelector("#pop button.cls"),
                By.xpath("//button[contains(.,'Accept') or contains(.,'Got it')]")
        };
        for (By popup : popups) {
            try {
                List<WebElement> found = driver.findElements(popup);
                if (!found.isEmpty() && found.get(0).isDisplayed()) {
                    found.get(0).click();
                }
            } catch (Exception ignored) {
            }
        }
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
