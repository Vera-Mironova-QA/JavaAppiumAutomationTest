package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    private static final String
            TITLE_ID = "id:org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
            MORE_OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
            OPTION_ADD_TO_MY_LIST_BUTTON = "xpath://android.widget.TextView[@text='Add to reading list']",
            GOT_IT_ONBORDING_BUTTON = "id:org.wikipedia:id/onboarding_button",
            MY_LIST_NAME = "id:org.wikipedia:id/text_input",
            OK_BUTTON = "xpath://*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE_ID,"Cannot find article title",5);
    }
    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }
    public void swipeToFooter() {
        this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find end of screen", 20);
    }
    public void addArticleToMyList(String titleOfList) {
        this.waitForElementAndClick(
                MORE_OPTIONS_BUTTON,
                "Cannot find More options button on screen",
                5
        );
        this.waitForElementAndClick(
                OPTION_ADD_TO_MY_LIST_BUTTON,
                "Cannot find Add to reading list in menu",
                5
        );
        this.waitForElementAndClick(
                GOT_IT_ONBORDING_BUTTON,
                "Cannot find button Got it on onbording",
                5
        );
        this.waitForElementAndClear(
                MY_LIST_NAME,
                "Cannot find input to set name of article",
                5
        );
        this.waitForElementAndSendKey(
                MY_LIST_NAME,
                titleOfList,
                "Cannot put text in input Name of this list",
                5
        );
        this.waitForElementAndClick(
                OK_BUTTON,
                "Cannot find button OK",
                5
        );
    }
    public void closeArticle() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot find X button",
                5
        );
    }
    public void addSecondArticleToMyList() {
        this.waitForElementAndClick(
                MORE_OPTIONS_BUTTON,
                "Cannot find More options button on screen",
                5
        );
        this.waitForElementAndClick(
                OPTION_ADD_TO_MY_LIST_BUTTON,
                "Cannot find Add to reading list in menu",
                5
        );
    }
    public void assertCheckTitleOfArticle() {
        assertElementPresent(
                TITLE_ID,
                "Article does not have title"
        );
    }
}
