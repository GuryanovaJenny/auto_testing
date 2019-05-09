import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import testClasses.AccountAuthorization;
import testClasses.Auto_settings;
import testClasses.MainPage;
import testClasses.SettingsPage;

public class Tests extends Auto_settings {

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

    @Test
    public void regionChangeTest() {
        MainPage home = new MainPage(getDriver(), getWaitTest());
        // element with region name on the top of the main page
        home.pressRegionChangeButton();
        // enter the "Хвалынск" region
        home.enterRegion();
        // select "Хвалынск" from region list
        home.selectRegion();
        // click to "Войти в аккаунт"
        home.clickLoginButton();

        AccountAuthorization accEnter = new AccountAuthorization(getDriver());
        // preforms steps to sign in
        accEnter.enterLogin();
        accEnter.enterPassword();

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
