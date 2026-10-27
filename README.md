# Jumia Egypt - Selenium Test Automation Framework

UI test automation for [jumia.com.eg](https://www.jumia.com.eg) built with **Java 17, Selenium WebDriver 4, TestNG, Maven** and the **Page Object Model**.

## Test coverage (13 test cases)
| ID | Area | Scenario |
|----|------|----------|
| TC01-04 | Home | Title, search box, cart link, domain |
| TC05-08 | Search | Valid keywords (data-driven), relevance, no-results, keyword in URL |
| TC09-11 | Product | Title, valid price, add-to-cart button |
| TC12-13 | Cart | Empty cart message, added product appears in cart |

## Structure
```
src/test/java
  base/    BaseTest (setup / teardown)
  pages/   BasePage, HomePage, SearchResultsPage, ProductPage, CartPage
  tests/   HomePageTests, SearchTests, ProductTests, CartTests
  utils/   DriverFactory (ThreadLocal), ConfigReader, TestListener (screenshot on failure)
src/test/resources  config.properties, testng.xml
.github/workflows   ci.yml (GitHub Actions)
```

## Run
```
mvn clean test                    # with browser
mvn clean test -Dheadless=true    # headless
```
Reports: `target/surefire-reports` - Failure screenshots: `target/screenshots`

## Notes
- Locators live only inside the page classes. If Jumia changes its UI, fix them there.
- CI runs on every push via GitHub Actions.
