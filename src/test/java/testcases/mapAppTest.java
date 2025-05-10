package testcases;
import common.baseTest;
import org.testng.annotations.Test;
import pages.mapAppPage;

public class mapAppTest extends baseTest{
    mapAppPage map;
    @Test
    public void checkUpdateLatLon1Click(){
        map = new mapAppPage(driver);
        map.clickOnCenter();
        map.openTableHistory();
        map.checkUpdateLatLon();
    }
    @Test
    public void checkOpenCloseTableHistory() {
        map = new mapAppPage(driver);
        map.openTableHistory();
        map.closeTableHistory();
    }
    @Test
    public void checkPopupLatLonDisplay() {
        map = new mapAppPage(driver);
        map.clickOnScreen(20, 50);
        map.checkPopupLatLonDisplayed();
    }
    @Test
    public void checkUpdateLatLonMultipleClicks() {
        map = new mapAppPage(driver);
        map.clickOnScreen(100,50);
        map.clickOnScreen(150,150);
        map.clickOnScreen(100,250);
        map.openTableHistory();
        map.checkUpdateLatLon();
    }
}
