package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {

    @Test(description = "TC01 - Home page title contains Jumia")
    public void homePageTitleContainsJumia() {
        Assert.assertTrue(homePage.getTitle().toLowerCase().contains("jumia"),
                "Unexpected title: " + homePage.getTitle());
    }

    @Test(description = "TC02 - Search box is displayed on home page")
    public void searchBoxIsDisplayed() {
        Assert.assertTrue(homePage.isSearchBoxDisplayed(), "Search box not displayed");
    }

    @Test(description = "TC03 - Cart link is displayed on home page")
    public void cartLinkIsDisplayed() {
        Assert.assertTrue(homePage.isCartLinkDisplayed(), "Cart link not displayed");
    }

    @Test(description = "TC04 - Home page URL is the Jumia Egypt domain")
    public void homePageUrlIsJumiaEgypt() {
        Assert.assertTrue(homePage.getCurrentUrl().contains("jumia.com.eg"),
                "Unexpected URL: " + homePage.getCurrentUrl());
    }
}
