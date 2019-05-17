import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testClasses.*;
import org.testng.annotations.DataProvider;

@Listeners(TestListener.class)
public class Tests extends Auto_settings {

    @DataProvider(name = "regionChangeTest")
    public Object[][] createData() {
        return new Object[][] {
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

    @Test
    public void toothbrushTest() {
        MainPage home = new MainPage(getDriver(), getWaitTest());
        // click on catalog button
        home.pressCatalogButton();
        // click on Electric Toothbrushes
        home.chooseElectricToothbrushes();

        ToothbrushesShoppingPage toothbrush = new ToothbrushesShoppingPage(getDriver(), getWaitTest());
        // choose the range of prices
        toothbrush.enterPriceRange();
        // open all toothbrushes
        toothbrush.showMoreToothbrushes();
        toothbrush.checkPriceRange();

        // add toothbrush and go to the cart page
        toothbrush.addToothbrush();
        toothbrush.clickCartButton();

        // check free delivery and total price
        ShoppingCartPage cart = new ShoppingCartPage(getDriver(), getWaitTest());
        cart.checkDelivery();
        int totalPrice = cart.checkTotalPrice();
        // add one more toothbrush and check it again
        cart.addItemsForFreeDelivery(totalPrice);
        cart.checkFreeDelivery();
        cart.checkTotalPriceWithFreeDelivery();
    }

}
