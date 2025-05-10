package report;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureManager {
    @Attachment(value = "0", type = "text/plain")
    public static String saveTextLog(String message){
        return message;
    }
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshot(WebDriver driver){
        if (driver == null) {
            return new byte[0]; // Tránh lỗi nếu driver null
        }
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
