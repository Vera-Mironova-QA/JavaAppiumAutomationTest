package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject{
    private static final String
            LIST_BY_NAME_XPATH_TPL = "//*[@text='{LIST_NAME}']",
            ARTICLE_BY_NAME_XPATH_TPL = "//*[@text='{ARTICLE_TITLE}']";

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    //TEMPLATES METHODS
    private static String getListByNameXpath(String titleOfList) {
        return LIST_BY_NAME_XPATH_TPL.replace("{LIST_NAME}", titleOfList);

    }
    private static String getArticleByNameXpath(String article_title) {
        return ARTICLE_BY_NAME_XPATH_TPL.replace("{ARTICLE_TITLE}", article_title);

    }
    //TEMPLATES METHODS

    public void openListByName(String titleOfList) {
        String title_of_list = getListByNameXpath(titleOfList);
        this.waitForElementAndClick(
                By.xpath(title_of_list),
                "Cannot find ny list with name" + titleOfList,
                5
        );
    }
    public void waitForArticleToAppearByTitle(String article_title) {
        String title_of_article = getArticleByNameXpath(article_title);
        this.waitForElementPresent(By.xpath(title_of_article),"Cannot find article with title " + article_title, 5);
    }
    public void waitForArticleToDisappearByTitle(String article_title) {
        String title_of_article = getArticleByNameXpath(article_title);
        this.waitForElementNotPresent(By.xpath(title_of_article),"Saved article with title " + article_title + "is presented still", 5);
    }
    public void swipeArticleToDelete(String article_title) {
        this.waitForArticleToAppearByTitle(article_title);
        String title_of_article = getArticleByNameXpath(article_title);
        this.swipeElementToLeft(
                By.xpath(title_of_article),
                "Cannot find saved article"
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }

}