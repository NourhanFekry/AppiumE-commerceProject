package tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.JsonFileManager;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginTest {
    WebDriver driver;
    private String path = "src/test/resources/TestDataFiles/loginUserBody.json";
    JsonFileManager testData;

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
    }

    @Test
    public void loginSuccessfully() {
        testData = new JsonFileManager(path);
        new LoginPage(driver)
                .setUsername(testData.getTestData("user.username"))
                .setPassword(testData.getTestData("user.password"))
                .clickOnLoginButton();
        String productsText = new LoginPage(driver).verifyLoginSuccessfully();
        Assert.assertEquals(productsText, "PRODUCTS");
    }

    @Test
    public void loginFailed() {
        testData = new JsonFileManager(path);
        new LoginPage(driver)
                .setUsername(testData.getTestData("invalidUser.username"))
                .setPassword(testData.getTestData("invalidUser.password"))
                .clickOnLoginButton();
        String errorMessage = new LoginPage(driver).verifyLoginFailed();
        Assert.assertEquals(errorMessage, "Username and password do not match any user in this service.");
    }

//    @AfterMethod
//    public void TakeScreenshot(ITestResult iTestResult) throws IOException {
//        var camera = (TakesScreenshot) driver;
//        File screenShot = camera.getScreenshotAs(OutputType.FILE);
//        Files.move(screenShot, new File("src/main/screenShots/" + "login" + ".png"));
//        Allure.addAttachment("src/main/screenShots", new FileInputStream(screenShot));
//        driver.quit();
//    }

}

