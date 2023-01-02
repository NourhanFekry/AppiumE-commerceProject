package pages;

import actions.MobileActions;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutOverviewPage {
    WebDriver driver;
    WebDriverWait driverWait;
    MobileActions mobileActions;
    private By priceCheckoutOverview = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Price\"]/android.widget.TextView\n");
    private By finishButton = AppiumBy.accessibilityId("test-FINISH");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String validatePrice() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(priceCheckoutOverview));
        return driver.findElement(priceCheckoutOverview).getText();
    }

    public CheckoutCompletePage clickOnFinishButton() {
        mobileActions = new MobileActions(driver);
        MobileActions.scrollDownToSpecificTextContained("FINISH");
        driver.findElement(finishButton).click();
        return new CheckoutCompletePage(driver);
    }
}

