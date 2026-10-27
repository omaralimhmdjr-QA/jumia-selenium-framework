package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends BasePage {

    private final By productCards = By.cssSelector("article.prd");
    private final By productNames = By.cssSelector("article.prd h3.name");
    private final By firstProductLink = By.cssSelector("article.prd a.core");
    private final By noResultsMessage =
            By.xpath("//*[contains(translate(normalize-space(.),"
                    + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
                    + "'abcdefghijklmnopqrstuvwxyz'),"
                    + "'no results')"
                    + " or contains(translate(normalize-space(.),"
                    + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
                    + "'abcdefghijklmnopqrstuvwxyz'),"
                    + "'there are no')]");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public int getResultsCount() {
        try {
            waitVisible(productCards);
        } catch (Exception e) {
            return 0;
        }
        return findAll(productCards).size();
    }

    public List<String> getProductNames() {
        waitVisible(productNames);
        return findAll(productNames).stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public boolean isNoResultsMessageDisplayed() {
        return isDisplayed(noResultsMessage);
    }

    public ProductPage openFirstProduct() {
        click(firstProductLink);
        return new ProductPage(driver);
    }
}
