package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait driverWait;
    private By usernameEditText = AppiumBy.accessibilityId("test-Username");
    private By passwordEditText = AppiumBy.accessibilityId("test-Password");
    private By loginButton = AppiumBy.accessibilityId("test-LOGIN");
    private By productsTextview = By.xpath("//android.widget.TextView[@text='PRODUCTS']");
    private By errorLoginMessage = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public LoginPage setUsername(String username) {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(usernameEditText));
        driver.findElement(usernameEditText).sendKeys(username);
        return this;
    }

    public LoginPage setPassword(String password) {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(passwordEditText));
        driver.findElement(passwordEditText).sendKeys(password);
        return this;
    }

    public ProductsPage clickOnLoginButton() {
        driver.findElement(loginButton).click();
        // driverWait.until(ExpectedConditions.elementToBeClickable(loginButton));
        return new ProductsPage(driver);
    }

    public String verifyLoginSuccessfully() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(productsTextview));
        return driver.findElement(productsTextview).getText();

    }

    public String verifyLoginFailed() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(errorLoginMessage));
        return driver.findElement(errorLoginMessage).getText();
    }
}
