package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class AndroidMyListPageObject extends MyListPageObject {
    static {
        LIST_BY_NAME_XPATH_TPL = "xpath://*[@text='{LIST_NAME}']";
        ARTICLE_BY_NAME_XPATH_TPL = "xpath://*[@text='{ARTICLE_TITLE}']";
    }
    public AndroidMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
