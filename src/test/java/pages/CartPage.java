package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class CartPage extends BasePage {

    private final By emptyCartMessage =
            By.xpath("//*[contains(translate(text(),'EMPTY','empty'),'empty')]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage open() {
        driver.get(ConfigReader.get("cart.url"));
        dismissPopups();
        return this;
    }

    public boolean isEmptyMessageDisplayed() {
        return isDisplayed(emptyCartMessage);
    }

    /** Cart titles are often truncated, so we match on the first 3 words only. */
    public boolean isProductInCart(String productName) {
        String[] words = productName.trim().split("\\s+");
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < Math.min(3, words.length); i++) {
            key.append(i == 0 ? "" : " ").append(words[i]);
        }
        By item = By.xpath("//*[contains(text(),\"" + key.toString().replace("\"", "") + "\")]");
        return isDisplayed(item);
    }
}
