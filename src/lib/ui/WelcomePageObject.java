package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {
    private static final String
        STEP_LEARN_MORE_ABOUT_LINK = "id:Learn more about Wikipedia",
        STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
        STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK = "id:Add or edit preferred languages",
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
        NEXT_LINK = "id:Next",
        GET_STARTED_BUTTON = "id:Get started",
        SKIP = "xpath://XCUIElementTypeButton[@name='Skip']";


    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_LINK,"Cannot find element More about link",10);
    }
    public void waitForNewWaysToExploreText() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT,"Cannot find 'New ways to explore' text",10);
    }

    public void waitForAddOrEditLanguages() {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK,"Cannot find element Add or edit languages link",10);
    }

    public void waitForLearnMoreAboutDataCollectedLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,"Cannot find link 'Learn more about data collected'",10);
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find Get started button", 10);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_LINK,"Cannot find and click on element Next",10);
    }

    public void clickSkip() {
        this.waitForElementAndClick(SKIP, "Cannot find Skip button", 10);
    }
}
