package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutCompletePage {
    WebDriver driver;
    WebDriverWait driverWait;

    private By finishOrderTextView = By.xpath("//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: COMPLETE!\"]/android.view.ViewGroup/android.widget.TextView[1]");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }


    public String validateCompletePurchase() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(finishOrderTextView));
        return driver.findElement(finishOrderTextView).getText();
    }


}
