package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    private final By productTitle = By.cssSelector("h1");
    private final By productPrice = By.cssSelector("span[class*='-fs24'][class*='-b']");
    private final By addToCartButton = By.cssSelector("button.add");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleText() {
        return getText(productTitle);
    }

    public boolean isPriceDisplayed() {
        return isDisplayed(productPrice);
    }

    /** Converts "EGP 1,299.00" to 1299.00 */
    public double getPrice() {
        String raw = getText(productPrice).replaceAll("[^0-9.]", "");
        return Double.parseDouble(raw);
    }

    public boolean isAddToCartDisplayed() {
        return isDisplayed(addToCartButton);
    }

    public void addToCart() {
        click(addToCartButton);
    }
}
