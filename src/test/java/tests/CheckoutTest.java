package tests;

import com.google.common.io.Files;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutInformationPage;
import pages.CheckoutOverviewPage;
import pages.LoginPage;
import utils.JsonFileManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CheckoutTest {
    WebDriver driver;
    JsonFileManager testData;
    private String loginDataPath = "src/test/resources/TestDataFiles/loginUserBody.json";
    private String checkOutDataPath = "src/test/resources/TestDataFiles/checkoutDataBody.json";

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
        testData = new JsonFileManager(loginDataPath);
        new LoginPage(driver)
                .setUsername(testData.getTestData("user.username"))
                .setPassword(testData.getTestData("user.password"))
                .clickOnLoginButton().clickOnAddToCartButton()
                .clickOnShoppingCartButton()
                .clickInCheckoutButton();
    }

    @Test
    public void completeCheckout() {
        testData = new JsonFileManager(checkOutDataPath);
        new CheckoutInformationPage(driver)
                .setFirstName(testData.getTestData("checkout.firstName"))
                .setLastName(testData.getTestData("checkout.lastName"))
                .setPostalCode(testData.getTestData("checkout.postalCode"))
                .clickOnContinueButton();
        String priceOnOverview = new CheckoutOverviewPage(driver).validatePrice();
        String priceOnCart = new CartPage(driver).validateProductCartPrice();
        Assert.assertEquals(priceOnOverview, priceOnCart);
        new CheckoutOverviewPage(driver)
                .clickOnFinishButton()
                .validateCompletePurchase();
    }

    @AfterMethod
    public void TakeScreenshot(ITestResult iTestResult) throws IOException {
        File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File newScreenShot = new File("src/main/screenShots/" + iTestResult.getName() + ".png");
        Files.move(screenShot, newScreenShot);
        Allure.addAttachment(iTestResult.getName(), new FileInputStream(newScreenShot));
        driver.quit();
    }
}
