import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class authentication extends auto_settings{

    private WebDriver driver;
    private WebDriverWait waitTest;
    private int productsOnThePage = 24;

    @BeforeMethod
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://beru.ru/");
        waitTest = new WebDriverWait(driver, 10);
    }

    public void signIn(){
        // wait until the page download
        waitTest.until(ExpectedConditions.visibilityOfElementLocated(By.className("header2__nav")));
        // press the Sign In button
        driver.findElement(By.cssSelector("div.header2__nav > div")).click();
        // introduce login
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys("jenny.guryanova@yandex.ru");
        // press button "Войти"
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        // wait until the password appear
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='passp-field-passwd']")));
        driver.findElement(By.xpath("//input[@id='passp-field-passwd']")).sendKeys("Yamomoto80Slim");
        // press button "Войти"
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @AfterTest
    public void signOut(){
        // go to "Мой аккаунт" and press "Выход"
        Actions actor = new Actions(driver);
        WebElement myProfile = waitTest.until
                (ExpectedConditions.presenceOfElementLocated(By.className("header2__nav")));
        actor.moveToElement(myProfile).build().perform();
        waitTest.until(ExpectedConditions.presenceOfElementLocated(By.className("header2-user-menu")));
        driver.findElement(By.className("header2-user-menu")).findElement(By.linkText("Выход")).click();
    }

    @Test
    public void navigateToBeruAuthPage(){
        // closing pop-up window
//        driver.findElement(By.cssSelector("div.modal__content > div")).click();
        // preforms steps to sign in
        signIn();
        // test of changing button text from "Войти в аккаунт" to "Мой профиль"
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.header2__nav")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.header2-nav__user")).getText(), "Мой профиль",
                "Кнопка входа профиля не найдена");

    }

    private static String newRegionName = "Хвалынск";

    @Test
    public void regionChangeTest() {
        // closing pop-up window
//        driver.findElement(By.className("_1ZYDKa22GJ")).click();
        // element with region name on the top of the main page
        WebElement regions_line = driver.findElement(By.className("region-form-opener"))
                .findElement(By.className("link__inner"));
        regions_line.click();
        // region input popup
        WebElement districtPopup = waitTest.until
                (ExpectedConditions.presenceOfElementLocated(By.className("region-select-form")));
        // region input field
        WebElement regionField = districtPopup.findElement(By.className("input__control"));
        regionField.sendKeys(newRegionName);
        // wait until entered text is displayed on popup
        waitTest.until(ExpectedConditions.visibilityOfElementLocated(By.className("region-suggest__list-item"))).click();
        // submit changes
        districtPopup.findElement(By.className("button2")).click();
        // wait until page loading is complete (footer social media elements are always loaded last)
        waitTest.until(ExpectedConditions.visibilityOfElementLocated(By.className("footer__social-media")));
        // region name line on the top of the main page
        regions_line = driver.findElement(By.className("region-form-opener")).findElement(By.className("link__inner"));
        // test of city name changes
        Assert.assertEquals(newRegionName, regions_line.getText(),
                "Region name on main page wasn't changed to" + newRegionName);
        // preforms steps to sign in
        signIn();
        // goes to account settings
        Actions actor = new Actions(driver);
        WebElement myProfile = waitTest.until
                (ExpectedConditions.presenceOfElementLocated(By.className("header2__nav")));
        actor.moveToElement(myProfile).build().perform();
        waitTest.until(ExpectedConditions.presenceOfElementLocated(By.className("header2-user-menu")));
        driver.findElement(By.className("header2-user-menu")).findElement(By.linkText("Настройки")).click();
        // region name on settings page
        WebElement myRegion = waitTest.until(ExpectedConditions.presenceOfElementLocated
                (By.className("settings-list__title"))).findElement(By.className("link__inner"));
        regions_line = driver.findElement(By.className("region-form-opener")).findElement(By.className("link__inner"));
        Assert.assertEquals(regions_line.getText(), myRegion.getText(),
                "Region name on the top of the page is not equal to the region name in settings");

    }
}
