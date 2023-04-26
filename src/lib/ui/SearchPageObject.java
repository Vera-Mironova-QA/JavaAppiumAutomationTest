package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    public static final String
            SEARCH_INIT_ELEMENT = "//*[@text='Search Wikipedia']",
            SEARCH_INPUT = "//*[@text='Search…']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{TITLE}']/following-sibling::*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
            SEARCH_RESULT_IS_EMPTY_LABEL = "//*[@text='No results found']",
            SEARCH_RESULT_TITLE_ELEMENT = "org.wikipedia:id/page_list_item_title";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    //TEMPLATES METHODS
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getResultSearchElementByTitleAndSustring(String title, String substring){
        return SEARCH_RESULT_BY_TITLE_AND_SUBSTRING_TPL.replace("{TITLE}", title).replace("{SUBSTRING}", substring);
    }
    //TEMPLATES METHODS

    public void initSearchInput(){
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element", 5);
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element",5);
    }
    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find cancel search button",5);
    }
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is presented still", 5);
    }
    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click on search cancel button", 5);
    }
    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKey(By.xpath(SEARCH_INPUT), searchLine, "Cannot find and type i nto search input",5);
        driver.hideKeyboard();
    }
    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring);
    }
    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click on search result with substring " + substring,15);
    }
    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anithing by request",
                15
        );
        return this.getAmountOfElement(By.xpath(SEARCH_RESULT_ELEMENT));
    }
    public void waitForEmptyResultLabel() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_IS_EMPTY_LABEL),
                "Cannot find empty result by request ",
                15
        );
    }
    public void waitForElementByTitleAndDescription(String title, String substring) {
        String search_result_by_xpath = getResultSearchElementByTitleAndSustring(title, substring);
        this.waitForElementPresent(
                By.xpath(search_result_by_xpath),
                "Cannot find article at search result with title " + title + " and substring " + substring,
                5
        );
    }
    public void assertThereIsNoResultOfSearch() {
        this.assertEelementNotPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "We've found some result by request"
        );
    }
    public void assertCheckResultOfTest(String titleOfSearch) {
        this.assertElementHasText(
                By.id(SEARCH_RESULT_TITLE_ELEMENT),
                titleOfSearch,
                "Cannot find article with " + titleOfSearch
        );
    }
}
