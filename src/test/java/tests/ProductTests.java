package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductPage;

public class ProductTests extends BaseTest {

    @Test(description = "TC09 - Product page shows a non-empty title")
    public void productPageShowsTitle() {
        ProductPage product = homePage.search("laptop").openFirstProduct();
        Assert.assertFalse(product.getTitleText().isEmpty(), "Product title is empty");
    }

    @Test(description = "TC10 - Product page shows a valid price")
    public void productPageShowsValidPrice() {
        ProductPage product = homePage.search("laptop").openFirstProduct();
        Assert.assertTrue(product.isPriceDisplayed(), "Price not displayed");
        Assert.assertTrue(product.getPrice() > 0, "Price should be greater than zero");
    }

    @Test(description = "TC11 - Add to cart button is displayed")
    public void addToCartButtonIsDisplayed() {
        ProductPage product = homePage.search("laptop").openFirstProduct();
        Assert.assertTrue(product.isAddToCartDisplayed(), "Add to cart button not displayed");
    }
}
