package tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.JsonFileManager;

import java.net.MalformedURLException;
import java.net.URL;

public class CartTest {
    WebDriver driver;
    JsonFileManager testData;
    private String path = "src/test/resources/TestDataFiles/loginUserBody.json";

    @BeforeClass
    public void setupDevice() throws MalformedURLException {
        String AppName = System.getProperty("user.dir") + "\\src\\test\\resources\\TestDataFiles\\Android.SauceLabs.Mobile.Sample.app.2.2.0.apk";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "Android SDK built for x86");
        caps.setCapability("appium:appWaitActivity", "com.swaglabsmobileapp.MainActivity");
        caps.setCapability("appium:app", AppName);
        caps.setCapability("appium:platformVersion", "11");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:ensureWebviewsHavePages", true);
        caps.setCapability("appium:nativeWebScreenshot", true);
        URL remoteUrl = new URL("http://127.0.0.1:4723/");
        driver = new AndroidDriver(remoteUrl, caps);
        testData = new JsonFileManager(path);
        new LoginPage(driver)
                .setUsername(testData.getTestData("user.username"))
                .setPassword(testData.getTestData("user.password"))
                .clickOnLoginButton().clickOnAddToCartButton().clickOnShoppingCartButton();
    }

    @Test
    public void verifyProductDetails() {
        SoftAssert softAssert = new SoftAssert();
        String sauceBagPrice = new ProductsPage(driver).validateSauceBagPrice();
        String sauceBagTitle = new ProductsPage(driver).validateSauceBagTitle();
        String productCartTittle = new CartPage(driver).validateSauceBagCartTitle();
        String productCartPrice = new CartPage(driver).validateProductCartPrice();
        softAssert.assertEquals(productCartPrice, sauceBagPrice);
        softAssert.assertEquals(productCartTittle, sauceBagTitle);
        softAssert.assertAll();
    }

    @Test
    public void removeProduct() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        new CartPage(driver).clickOnRemoveButton();
        int removedProduct = new CartPage(driver).removeProductSuccessfully();
        int emptyCartIcon = new CartPage(driver).removeShoppingCartText();
        softAssert.assertEquals(removedProduct, 0);
        softAssert.assertEquals(emptyCartIcon, 0);
        softAssert.assertAll();
    }
}
