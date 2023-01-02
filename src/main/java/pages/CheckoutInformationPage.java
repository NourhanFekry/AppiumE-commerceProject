package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutInformationPage {
    WebDriver driver;
    WebDriverWait driverWait;
    private By checkoutFirstName = AppiumBy.accessibilityId("test-First Name");
    private By checkoutLastName = AppiumBy.accessibilityId("test-Last Name");
    private By checkoutPostalCode = AppiumBy.accessibilityId("test-Zip/Postal Code");
    private By checkoutContinueButton = AppiumBy.accessibilityId("test-CONTINUE");

    public CheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }
    public CheckoutInformationPage setFirstName(String firstName)
    {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(checkoutFirstName));
        driver.findElement(checkoutFirstName).sendKeys(firstName);
        return this;
    }
    public CheckoutInformationPage setLastName(String lastName)
    {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(checkoutLastName));
        driver.findElement(checkoutLastName).sendKeys(lastName);
        return this;
    }
    public CheckoutInformationPage setPostalCode(String postalCode)
    {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(checkoutPostalCode));
        driver.findElement(checkoutPostalCode).sendKeys(postalCode);
        return this;
    }
    public CheckoutOverviewPage clickOnContinueButton()
    {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(checkoutContinueButton));
        driver.findElement(checkoutContinueButton).click();
        return new CheckoutOverviewPage(driver);
    }

}
