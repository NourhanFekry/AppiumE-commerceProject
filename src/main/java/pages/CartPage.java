package pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    WebDriver driver;
    WebDriverWait driverWait;
    private By productCartTittle = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]");
    private By productCartPrice = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Price\"]/android.widget.TextView");
    private By removeCartButton = By.xpath("//android.widget.TextView[@text=\"REMOVE\"]");
    private By shoppingCartText = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.TextView");
    private By checkoutButton = AppiumBy.accessibilityId("test-CHECKOUT");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public String validateSauceBagCartTitle() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(productCartTittle));
        return driver.findElement(productCartTittle).getText();
    }

    public String validateProductCartPrice() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(productCartPrice));
        return driver.findElement(productCartPrice).getText();
    }

    //TODO
    public CartPage clickOnRemoveButton() throws InterruptedException {

        //driverWait.until(ExpectedConditions.elementToBeClickable(removeCartButton)).click();
        Thread.sleep(7000);
        driver.findElement(removeCartButton).click();
        Thread.sleep(2000);
        System.out.println("clicked");
        return this;
    }

    public int removeProductSuccessfully() {
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(productCartTittle));
        return driver.findElements(productCartTittle).size();
    }

    public int removeShoppingCartText() {
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(shoppingCartText));
        return driver.findElements(shoppingCartText).size();
    }

    public CheckoutInformationPage clickInCheckoutButton() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));
        driver.findElement(checkoutButton).click();
        return new CheckoutInformationPage(driver);
    }

}
