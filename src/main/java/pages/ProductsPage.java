package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage {
    WebDriver driver;
    WebDriverWait driverWait;

    private By addToCartButton = By.xpath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]");
    private By sauceBagPrice = By.xpath("(//android.widget.TextView[@content-desc=\"test-Price\"])[1]");
    private By sauceBagTitle = By.xpath("(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]");
    private By shoppingCartButton = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView");


    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }

    public ProductsPage clickOnAddToCartButton() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
        driver.findElement(addToCartButton).click();
        return this;
    }

    public String validateSauceBagPrice() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(sauceBagPrice));
        return driver.findElement(sauceBagPrice).getText();
    }

    public String validateSauceBagTitle() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(sauceBagTitle));
        return driver.findElement(sauceBagTitle).getText();
    }

    public CartPage clickOnShoppingCartButton() {
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartButton));
        driver.findElement(shoppingCartButton).click();
        return new CartPage(driver);
    }
}
