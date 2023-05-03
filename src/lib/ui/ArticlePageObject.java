package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract  public class ArticlePageObject extends MainPageObject{
    protected static  String
            TITLE_ID,
            FOOTER_ELEMENT,
            MORE_OPTIONS_BUTTON,
            OPTION_ADD_TO_MY_LIST_BUTTON,
            GOT_IT_ONBORDING_BUTTON,
            MY_LIST_NAME,
            OK_BUTTON,
            CLOSE_ARTICLE_BUTTON;


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE_ID,"Cannot find article title",5);
    }
    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if(Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }
    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find end of screen", 40);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find end of screen", 40);
        }
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
    public void addArticleToMySaved() {
        this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON, "Cannot find My saved button", 10);
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
