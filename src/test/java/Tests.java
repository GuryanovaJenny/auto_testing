import org.testng.annotations.Test;
import testClasses.AccountAuthorization;
import testClasses.Auto_settings;
import org.testng.annotations.DataProvider;
import testClasses.MainPage;
import testClasses.SettingsPage;

public class Tests extends Auto_settings {

    @DataProvider(name = "regionChangeTest")
    public Object[][] createData(){
        return new Object[][]{
                {"Саратов"},
                {"Хвалынск"}
        };
    }

    @Test
    public void navigateToBeruAuthPage() {
        MainPage home = new MainPage(getDriver(), getWaitTest());
        // click to "Войти в аккаунт"
        home.clickLoginButton();
        AccountAuthorization accEnter = new AccountAuthorization(getDriver());
        // preforms steps to sign in
        accEnter.enterLogin();
        accEnter.enterPassword();

        // check button text changes from "Войти в аккаунт" to "Мой профиль"
        home.enterCheck();
    }

    @Test (dataProvider = "regionChangeTest")
    public void regionChangeTest(String newRegionName) {
        MainPage home = new MainPage(getDriver(), getWaitTest());
        // element with region name on the top of the main page
        home.pressRegionChangeButton();
        // enter region
        home.enterRegion(newRegionName);
        // select from region list
        home.selectRegion(newRegionName);
        // click to "Войти в аккаунт"
        home.clickLoginButton();

        AccountAuthorization accEnter = new AccountAuthorization(getDriver());
        // preforms steps to sign in
        accEnter.enterLogin();
        accEnter.enterPassword();

        // go to account settings
        home.clickSettingsButton();

        SettingsPage settings = new SettingsPage(getDriver());
        // check that name of the region is change correct
        settings.checkDeliveryRegionName();
    }

/*    @Test
    public void toothbrushTest(){
        // preforms steps to sign in
        signIn();

        // click on catalog button
        driver.findElement(By.className("header2__navigation")).click();
    }
*/
}
