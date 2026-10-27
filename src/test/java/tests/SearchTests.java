package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.SearchResultsPage;

import java.util.List;

public class SearchTests extends BaseTest {

    @DataProvider(name = "validKeywords")
    public Object[][] validKeywords() {
        return new Object[][]{{"iphone"}, {"laptop"}, {"samsung"}, {"headphones"}};
    }

    @Test(dataProvider = "validKeywords", description = "TC05 - Valid keyword returns results")
    public void validKeywordReturnsResults(String keyword) {
        SearchResultsPage results = homePage.search(keyword);
        Assert.assertTrue(results.getResultsCount() > 0, "No results for: " + keyword);
    }

    @Test(description = "TC06 - Search results are relevant to the keyword")
    public void searchResultsAreRelevant() {
        SearchResultsPage results = homePage.search("iphone");
        List<String> names = results.getProductNames();
        long matches = names.stream().filter(n -> n.toLowerCase().contains("iphone")).count();
        Assert.assertTrue(matches > 0, "No product name contains 'iphone': " + names);
    }

    @Test(description = "TC07 - Gibberish keyword shows a no-results message")
    public void invalidKeywordShowsNoResults() {
        SearchResultsPage results = homePage.search("zzxqwkj987654");
        Assert.assertTrue(results.isNoResultsMessageDisplayed(), "No-results message not displayed");
    }

    @Test(description = "TC08 - Search keyword appears in the URL")
    public void keywordAppearsInUrl() {
        SearchResultsPage results = homePage.search("iphone");
        results.getResultsCount();
        Assert.assertTrue(results.getCurrentUrl().contains("q=iphone"),
                "URL does not contain the keyword: " + results.getCurrentUrl());
    }
}
