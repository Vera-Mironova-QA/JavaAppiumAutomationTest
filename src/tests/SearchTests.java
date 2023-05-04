package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String searchLine = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(searchLine);
        int amountOfSearchResults = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found to few results!",
                amountOfSearchResults > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String searchLine = "ddddddrrff";
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testCancelResultOfSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String titleOfSearch = "Java";
        SearchPageObject.typeSearchLine(titleOfSearch);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        SearchPageObject.assertCheckResultOfTest(titleOfSearch);
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testSearchResultByTitleAndSubstring() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String titleOfSearch = "Java";
        SearchPageObject.typeSearchLine(titleOfSearch);
        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia, Southeast Asia");
            SearchPageObject.waitForElementByTitleAndDescription("JavaScript", "High-level programming language");
            SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
        } else {
            SearchPageObject.waitForSearchResult("Island in Indonesia");
            SearchPageObject.waitForSearchResult("Object-oriented programming language");
            SearchPageObject.waitForSearchResult("High-level programming language");
        }
    }
}
