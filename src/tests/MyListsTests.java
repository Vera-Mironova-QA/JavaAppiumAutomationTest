package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String
            name_of_folder = "Learning programming";
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();
        if(Platform.getInstance().isIOS()) {
            SearchPageObject.clickCancelSearch();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isIOS()) {
            MyListPageObject.closePopup();
        }
        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openListByName(name_of_folder);
        }
        MyListPageObject.swipeArticleToDelete(article_title);
    }

    @Test
    public void testCheckSavingSecondArticleInFavorite() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title_first = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("High-level programming language");

        ArticlePageObject.waitForTitleElement();
        String article_title_second = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addSecondArticleToMyList();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        MyListPageObject.openListByName(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);;
        NavigationUI.clickMyList();

        MyListPageObject.openListByName(name_of_folder);
        MyListPageObject.swipeArticleToDelete(article_title_first);
        MyListPageObject.waitForArticleToDisappearByTitle(article_title_first);
        MyListPageObject.waitForArticleToAppearByTitle(article_title_second);
    }
}
