package pages;

import common.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class mapAppPage {
    By buttonHistory = By.xpath("//button[@id='toggle-table-btn']");
    By buttonLogSimulation = By.xpath("//button[normalize-space()='Log Simulator']");
    By tableHistory = By.xpath("//div[@id='coord-table']");
    By tableLogSimulation = By.xpath("//div[@id='data-log']");
    By titleLogSimulation = By.xpath("//h4[normalize-space()='Log Simulator']");
    By buttonDeleteAllHistory = By.xpath("//div//table//thead//tr//th//button[@id='delete-all-btn']");
    By popupSelectedPoint = By.xpath("//div[contains(@class, 'leaflet-popup-content')]//div");
    By lastHistoryRow = By.xpath("//table/tbody/tr[last()]");
    private WebDriver driver;
    public mapAppPage(WebDriver _driver){
        driver = _driver;
    }

    @Step("Chờ phần mềm load thành công")
    public void waitForPageLoad(){
        //WebUI.waitForVisibility(buttonHistory);
        WebUI.waitForElementPresent(buttonHistory);
    }
    @Step("Mở bảng lịch sử chọn")
    public void openTableHistory(){
        String a = driver.findElement(tableHistory).getCssValue("display");
        if("none".equals(a)){
        driver.findElement(buttonHistory).click();
        WebUI.waitForVisibility(tableHistory);
        Assert.assertEquals(driver.findElement(tableHistory).getCssValue("display"), "block");
        System.out.println(driver.findElement(tableHistory).getCssValue("display"));
    }else {
        Assert.fail("Bảng đang mở");
    }}
    @Step("Đóng bảng lịch sử chọn")
    public void closeTableHistory(){
        String a = driver.findElement(tableHistory).getCssValue("display");
        if("block".equals(a)){
        driver.findElement(buttonHistory).click();
        WebUI.waitForElementInvisibility(tableHistory);
        Assert.assertEquals(driver.findElement(tableHistory).getCssValue("display"), "none");
        System.out.println(driver.findElement(tableHistory).getCssValue("display"));
    }else {
        Assert.fail("Bảng đang đóng");
    }}
    @Step("Kiểm tra thành phần header của bảng lịch sử chọn")
    public void checkHeaderTableHistory(){
        Assert.assertTrue(driver.findElement(By.xpath("//div//table//thead//tr//th[normalize-space()='STT']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div//table//thead//tr//th[normalize-space()='Vĩ độ']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div//table//thead//tr//th[normalize-space()='Kinh độ']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div//table//thead//tr//th[normalize-space()='Ghi chú']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div//table//thead//tr//th[normalize-space()='Định vị']")).isDisplayed());
        Assert.assertTrue(driver.findElement(buttonDeleteAllHistory).isDisplayed());
    }

    @Step("Click vào vị trí chọn lựa trên màn hình: {0} {1}")
    public void clickOnScreen(int x, int y){
        WebUI.moveToOffset(x,y);
        new Actions(driver).click().perform();
        WebUI.sleep(0.3);
    }
    @Step("Click chột vào giữa màn hình")
    public void clickOnCenter(){
        WebUI.moveMouseToCenter();
        new Actions(driver).click().perform();
        WebUI.sleep(0.3);
    }
    @Step("Kiểm tra popup tọa độ hiển thị")
    public void checkPopupLatLonDisplayed(){
        WebUI.waitForVisibility(popupSelectedPoint);
        Assert.assertTrue(driver.findElement(popupSelectedPoint).isDisplayed());
    }
    @Step("Kiểm tra giá trị Lat Lon mới nhập vào bảng lịch sử chọn")
    public void checkUpdateLatLon(){
        WebElement lastRow = driver.findElement(lastHistoryRow);
        WebUI.scrollToElement(lastRow);
        String viDoTable = lastRow.findElement(By.xpath("td[2]")).getText();
        String kinhDoTable = lastRow.findElement(By.xpath("td[3]")).getText();

        // 🔹 Tìm phần tử popup chứa Vĩ độ và Kinh độ
        WebElement parentElement = driver.findElement(popupSelectedPoint);
        String fullText = parentElement.getText();
        String[] lines = fullText.split("\n");
        String viDoPopup = lines[1].replace("Vĩ độ: ", "").trim();
        String kinhDoPopup = lines[2].replace("Kinh độ: ", "").trim();
        //So sánh giá trị đã thêm và popup
        Assert.assertEquals(viDoTable,viDoPopup);
        Assert.assertEquals(kinhDoTable, kinhDoPopup);
    }
    @Step("Mở bảng Log Simulation")
    public void openTableSimulation(){
        String a = driver.findElement(tableLogSimulation).getCssValue("display");
        if("none".equals(a)){
            driver.findElement(buttonLogSimulation).click();
            WebUI.waitForVisibility(titleLogSimulation);
            Assert.assertEquals(driver.findElement(tableLogSimulation).getCssValue("display"), "block");
        }else {
            Assert.fail("Bảng Simulation đang mở");
        }}



}
