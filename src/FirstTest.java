import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities  capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","9");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Volumes/KINGSTON/LearnQA/JavaAppiumAutomationTest/JavaAppiumAutomationTest/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find Search line on Main screen",
                5
        );
        waitForElementAndSendKey(
                By.xpath("//*[@text='Search…']"),
                "Java",
                "Cannot find input search",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' after input 'Java' in Search line",
                15
        );
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search line on Main screen",
                5
        );
        waitForElementAndSendKey(
                By.xpath("//*[@text='Search…']"),
                "Java",
                "Cannot find input search",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
       );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }

    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find Search line on Main screen",
                5
        );
        waitForElementAndSendKey(
                By.xpath("//*[@text='Search…']"),
                "Java",
                "Cannot find input search",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' after input 'Java' in Search line",
                5
        );
        WebElement titleElement = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        String articleTitle = titleElement.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                articleTitle
        );
    }

    @Test
    public void testSwipeOfArticle() {
        waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find Search line on Main screen",
                5
        );
        waitForElementAndSendKey(
                By.xpath("//*[@text='Search…']"),
                "Appium",
                "Cannot find input search",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' after input 'Appium' in Search line",
                5
        );
        swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Cannot find end of screen",
                20
        );
    }

    @Test
    public void testCheckTextInSearch() {
        assertElementHasText(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Search Wikipedia",
                "Text in search line other"
        );
    }

    @Test
    public void testCheckListCards() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find Search line on Main screen",
                5
        );
        waitForElementAndSendKey(
                By.xpath("//*[@text='Search…']"),
                "Java",
                "Cannot find input search",
                5
        );
        assertElementHasText(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Java",
                "Text doesn't contain 'Java'"
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Seach result not empty",
                15
        );
    }

    @Test
    public void saveFirstArticleToMyList() {
        waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find Search line on Main screen",
                5
        );
        waitForElementAndSendKey(
                By.xpath("//*[@text='Search…']"),
                "Java",
                "Cannot find input search",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' after input 'Java' in Search line",
                5
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find More options button on screen",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                "Cannot find Add to reading list in menu",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find button Got it on onbording",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of article",
                5
        );
        String titleOfList = "Learning programming";
        waitForElementAndSendKey(
                By.id("org.wikipedia:id/text_input"),
                titleOfList,
                "Cannot put text in input Name of this list",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find button OK",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find X button",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find My list in navigation menu",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='"+ titleOfList + "']"),
                "Cannot find ny list Learning programming",
                5
        );
        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );
        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Still see article in list",
                5
        );
    }

    @Test
    public void testAmoutOfNotEmptySearch() {
        waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find Search line on Main screen",
                5
        );
        String searchLine = "Linkin Park Diskography";
        waitForElementAndSendKey(
                By.xpath("//*[@text='Search…']"),
                searchLine,
                "Cannot find input search",
                5
        );

        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        waitForElementPresent(
                By.xpath(searchResultLocator),
                "Cannot find anithing by request" + searchLine,
                15
        );
        int amountfSearchResults = getAmountOfElement(
                By.xpath(searchResultLocator)
        );
        Assert.assertTrue(
                "We found to few results!",
                amountfSearchResults>0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find Search line on Main screen",
                5
        );
        String searchLine = "Java";
        waitForElementAndSendKey(
                By.xpath("//*[@text='Search…']"),
                searchLine,
                "Cannot find input search",
                5
        );
        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String emptyResultLabel = "//*[@text='No results found']";

        waitForElementPresent(
                By.xpath(emptyResultLabel),
                "Cannot find empty result by request " + searchLine,
                15
        );
        assertEelementNotPresent(
                By.xpath(searchResultLocator),
                "We've found some result by request" + searchLine
        );
    }

    @Test
    public void testChangeScreenOrientationOnSearchScreen() {
        waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find Search line on Main screen",
                5
        );
        String searchInput = "Java";
        waitForElementAndSendKey(
                By.xpath("//*[@text='Search…']"),
                searchInput,
                "Cannot find input search",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Java (programming language)']"),
                "Cannot find 'Java (programming language)' after input " + searchInput,
                5
        );
        String titleBeforeRotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String titleAfterRotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        Assert.assertEquals(
                "Title of article have been changed after rotation",
                titleBeforeRotation,
                titleAfterRotation
        );
        driver.rotate(ScreenOrientation.PORTRAIT);
        String titleAfterSecondRotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        Assert.assertEquals(
                "Title of article have been changed after second rotation",
                titleBeforeRotation,
                titleAfterSecondRotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find Search line on Main screen",
                5
        );
        waitForElementAndSendKey(
                By.xpath("//*[@text='Search…']"),
                "Java",
                "Cannot find input search",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' after input 'Java' in Search line",
                5
        );
        driver.runAppInBackground(2);
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' after input 'Java' in Search line",
                5
        );
    }

    @Test
    public void testCheckSavingSecondArticleInFavorite() {
        waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find Search line on Main screen",
                5
        );
        String searchInput = "Java";
        waitForElementAndSendKey(
                By.xpath("//*[@text='Search…']"),
                searchInput,
                "Cannot find input search",
                5
        );
        String firstArticle = "Java (programming language)";
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='"+ firstArticle +"']"),
                "Cannot find" + firstArticle + " after input " + searchInput + " in Search line",
                5
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find More options button on screen",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                "Cannot find Add to reading list in menu",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find button Got it on onbording",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of article",
                5
        );
        String titleOfList = "Learning programming";
        waitForElementAndSendKey(
                By.id("org.wikipedia:id/text_input"),
                titleOfList,
                "Cannot put text in input Name of this list",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find button OK",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find X button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Search Wikipedia']"),
                "Cannot find Search line on Main screen",
                5
        );
        waitForElementAndSendKey(
                By.xpath("//*[@text='Search…']"),
                searchInput,
                "Cannot find input search",
                5
        );
        String secondArticle = "JavaScript";
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='"+ secondArticle +"']"),
                "Cannot find" + secondArticle +" after input " + searchInput+ "in Search line",
                5
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find More options button on screen",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find Add to reading list in menu",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='"+ titleOfList +"']"),
                "Cannot find favorite list " + titleOfList,
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find X button",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find My list in navigation menu",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='"+ titleOfList + "']"),
                "Cannot find ny list " + titleOfList,
                5
        );
        swipeElementToLeft(
                By.xpath("//*[@text='" + secondArticle + "']"),
                "Cannot find saved article"
        );
        waitForElementNotPresent(
                By.xpath("//*[@text='" + secondArticle + "']"),
                "Still see article in list",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='"+ firstArticle + "']"),
                "Cannot find article" + firstArticle,
                15
        );
        assertElementHasText(
                By.id("org.wikipedia:id/view_page_title_text"),
                firstArticle,
                "Title of article doesn't match"
        );
    }

    private WebElement assertElementHasText(By by, String expected, String errorMessage) {
        WebElement textElement = waitForElementPresent(by, errorMessage);
        String titleText = textElement.getAttribute("text");
        Assert.assertEquals(
                errorMessage,
                expected,
                titleText
        );
        return textElement;
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String errorMessage) {
        return waitForElementPresent(by ,errorMessage,5);
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage ,timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKey(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }

    protected void swipeUp(Integer timeOfSwipe) {

        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int y_start = (int) (size.height*0.8);
        int y_end = (int) (size.height*0.2);

        action
                .press(x, y_start)
                .waitAction(timeOfSwipe)
                .moveTo(x, y_end)
                .release()
                .perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String errorMessage, Integer maxSwipes) {
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {
            if(alreadySwiped > maxSwipes) {
                waitForElementPresent(by, "Cannot find element by swipping up \n" + errorMessage,0);
            }
            swipeUpQuick();
            ++ alreadySwiped;
        }
    }

    protected void swipeElementToLeft(By by, String errorMessage) {
        WebElement element = waitForElementPresent(
                by,
                errorMessage,
                10
        );
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }
    private int getAmountOfElement(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertEelementNotPresent(By by, String errorMessage) {
        int amountEelementOnScreen = getAmountOfElement(by);
        if (amountEelementOnScreen > 0) {
            String defaultMessage = "An element '" +by.toString() +"' supposed to be not presented";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String errorMessage,long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}
