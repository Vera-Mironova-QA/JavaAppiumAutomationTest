package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class IOSMyListPageObject extends MyListPageObject {
    static {
        ARTICLE_BY_NAME_XPATH_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{ARTICLE_TITLE}')]";
        CLOSE_POPUP_BUTTON = "id:Close";
    }
    public IOSMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
