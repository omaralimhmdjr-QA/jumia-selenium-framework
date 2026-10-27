package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductPage;

public class CartTests extends BaseTest {

    @Test(description = "TC12 - Empty cart shows the empty message")
    public void emptyCartShowsMessage() {
        CartPage cart = new CartPage(driver).open();
        Assert.assertTrue(cart.isEmptyMessageDisplayed(), "Empty cart message not displayed");
    }

    @Test(description = "TC13 - Added product appears in the cart")
    public void addedProductAppearsInCart() {
        ProductPage product = homePage.search("laptop").openFirstProduct();
        String name = product.getTitleText();
        product.addToCart();

        CartPage cart = new CartPage(driver).open();
        Assert.assertTrue(cart.isProductInCart(name), "Product not found in cart: " + name);
    }
}
