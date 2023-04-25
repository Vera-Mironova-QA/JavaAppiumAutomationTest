package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    private static final String
            TITLE_ID = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "//*[@text='View page in browser']",
            MORE_OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTION_ADD_TO_MY_LIST_BUTTON = "//android.widget.TextView[@text='Add to reading list']",
            GOT_IT_ONBORDING_BUTTON = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME = "org.wikipedia:id/text_input",
            OK_BUTTON = "//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(TITLE_ID),"Cannot find article title",5);
    }
    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }
    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Cannot find end of screen", 20);
    }
    public void addArticleToMyList(String titleOfList) {
        this.waitForElementAndClick(
                By.xpath(MORE_OPTIONS_BUTTON),
                "Cannot find More options button on screen",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OPTION_ADD_TO_MY_LIST_BUTTON),
                "Cannot find Add to reading list in menu",
                5
        );
        this.waitForElementAndClick(
                By.id(GOT_IT_ONBORDING_BUTTON),
                "Cannot find button Got it on onbording",
                5
        );
        this.waitForElementAndClear(
                By.id(MY_LIST_NAME),
                "Cannot find input to set name of article",
                5
        );
        this.waitForElementAndSendKey(
                By.id(MY_LIST_NAME),
                titleOfList,
                "Cannot put text in input Name of this list",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OK_BUTTON),
                "Cannot find button OK",
                5
        );
    }
    public void closeArticle() {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot find X button",
                5
        );
    }
}
