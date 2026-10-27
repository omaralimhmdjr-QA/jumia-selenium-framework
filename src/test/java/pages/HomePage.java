package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    // NOTE: if Jumia changes its UI, update locators here only (one place).
    private final By searchInput = By.id("fi-q");
    private final By searchButton = By.cssSelector("form button[type='submit']");
    private final By cartLink = By.cssSelector("a[href*='/cart/']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isSearchBoxDisplayed() {
        return isDisplayed(searchInput);
    }

    public boolean isCartLinkDisplayed() {
        return isDisplayed(cartLink);
    }

    public SearchResultsPage search(String keyword) {
        type(searchInput, keyword);
        click(searchButton);
        return new SearchResultsPage(driver);
    }
}
