package common;

import TestListener.testListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(testListener.class)
public class baseTest {
    public static WebDriver driver;
    @BeforeMethod
    public void launchApp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HungNT\\.cache\\selenium\\chromedriver\\win64\\132.0.6834.110\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\HungNT\\Desktop\\seminar_t5\\win-unpacked\\OSM-App.exe");
        driver = new ChromeDriver(options);
        new WebUI(driver);
    }
    @AfterMethod
    public void closeApp() {
        if (driver != null) {
            driver.quit();
        }
    }
    public WebDriver getDriver(){
        return driver;
    }
}