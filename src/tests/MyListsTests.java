package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openListByName(name_of_folder);
        MyListPageObject.swipeArticleToDelete(article_title);
    }

    @Test
    public void testCheckSavingSecondArticleInFavorite() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title_first = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("High-level programming language");

        ArticlePageObject.waitForTitleElement();
        String article_title_second = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addSecondArticleToMyList();

        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openListByName(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListPageObject.openListByName(name_of_folder);
        MyListPageObject.swipeArticleToDelete(article_title_first);
        MyListPageObject.waitForArticleToDisappearByTitle(article_title_first);
        MyListPageObject.waitForArticleToAppearByTitle(article_title_second);
    }
}