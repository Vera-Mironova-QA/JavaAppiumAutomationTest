import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception{
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }


    @Test
    public void testCheckTextInSearch() {
        MainPageObject.assertElementHasText(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Search Wikipedia",
                "Text in search line other"
        );
    }

    @Test
    public void testCheckListCards() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search line on Main screen",
                5
        );
        MainPageObject.waitForElementAndSendKey(
                By.xpath("//*[@text='Search…']"),
                "Java",
                "Cannot find input search",
                5
        );
        MainPageObject.assertElementHasText(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Java",
                "Text doesn't contain 'Java'"
        );
        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );
        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Seach result not empty",
                15
        );
    }


    @Test
    public void testCheckSavingSecondArticleInFavorite() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find Search line on Main screen",
                5
        );
        String searchInput = "Java";
        MainPageObject.waitForElementAndSendKey(
                By.xpath("//*[@text='Search…']"),
                searchInput,
                "Cannot find input search",
                5
        );
        String firstArticle = "Java (programming language)";
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='"+ firstArticle +"']"),
                "Cannot find" + firstArticle + " after input " + searchInput + " in Search line",
                5
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find More options button on screen",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                "Cannot find Add to reading list in menu",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find button Got it on onbording",
                5
        );
        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of article",
                5
        );
        String titleOfList = "Learning programming";
        MainPageObject.waitForElementAndSendKey(
                By.id("org.wikipedia:id/text_input"),
                titleOfList,
                "Cannot put text in input Name of this list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find button OK",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find X button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find Search line on Main screen",
                5
        );
        MainPageObject.waitForElementAndSendKey(
                By.xpath("//*[@text='Search…']"),
                searchInput,
                "Cannot find input search",
                5
        );
        String secondArticle = "JavaScript";
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='"+ secondArticle +"']"),
                "Cannot find" + secondArticle +" after input " + searchInput+ "in Search line",
                5
        );
        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find More options button on screen",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find Add to reading list in menu",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='"+ titleOfList +"']"),
                "Cannot find favorite list " + titleOfList,
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find X button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find My list in navigation menu",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='"+ titleOfList + "']"),
                "Cannot find ny list " + titleOfList,
                5
        );
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='" + secondArticle + "']"),
                "Cannot find saved article"
        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='" + secondArticle + "']"),
                "Still see article in list",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='"+ firstArticle + "']"),
                "Cannot find article" + firstArticle,
                15
        );
        MainPageObject.assertElementHasText(
                By.id("org.wikipedia:id/view_page_title_text"),
                firstArticle,
                "Title of article doesn't match"
        );
    }

    @Test
    public void testCheckTitleOfArticle() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find Search line on Main screen",
                5
        );
        String searchInput = "Java";
        MainPageObject.waitForElementAndSendKey(
                By.xpath("//*[@text='Search…']"),
                searchInput,
                "Cannot find input search",
                5
        );
        String firstArticle = "Java (programming language)";
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='"+ firstArticle +"']"),
                "Cannot find" + firstArticle + " after input " + searchInput + " in Search line",
                5
        );
        MainPageObject.assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Article does not have title"
        );
    }
}
