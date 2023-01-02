package actions;


import io.appium.java_client.MobileBy;
import org.openqa.selenium.WebDriver;

public class MobileActions {
    private static WebDriver driver;

    public MobileActions(WebDriver driver) {
        MobileActions.driver = driver;
    }

    /**
     * This method is to scroll down to a text or Element that containing a text that happened by passing only the text appeared on the screen
     *
     * @param text the text or element you want to scroll to
     */
    public static void scrollDownToSpecificTextContained(String text) {
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)"
                + ".instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))"));
    }
}