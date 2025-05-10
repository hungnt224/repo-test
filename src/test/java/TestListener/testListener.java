package TestListener;

import common.baseTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import report.AllureManager;

import static common.baseTest.driver;


public class testListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result){
        AllureManager.saveTextLog(result.getName() +  " is PASSED");
        AllureManager.saveScreenshot(driver);
    }
    @Override
    public void onTestFailure(ITestResult result) {
        AllureManager.saveTextLog(result.getName() +  " is FAILED");
        AllureManager.saveScreenshot(driver);
    }
    @Override
    public void onTestStart(ITestResult result){
    }
    @Override
    public void onTestSkipped(ITestResult result){
        AllureManager.saveTextLog(result.getName() +  " is SKIPPED");
        AllureManager.saveScreenshot(driver);
    }
}
