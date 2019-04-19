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
        //ждем пока страница загрузится
        waitTest.until(ExpectedConditions.visibilityOfElementLocated(By.className("header2__nav")));
        //войти в аккаунт
        driver.findElement(By.cssSelector("div.header2__nav > div")).click();
        //вводим логин
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys("jenny.guryanova@yandex.ru");
        //нажатие на кнопку "Войти"
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //ждем появления пароля
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='passp-field-passwd']")));
        driver.findElement(By.xpath("//input[@id='passp-field-passwd']")).sendKeys("Yamomoto80Slim");
        //нажатие на кнопку "Войти"
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @AfterTest
    public void signOut(){
        //переходим в "Мой аккаунт" и нажимаем "Выйти"
        Actions actor = new Actions(driver);
        WebElement myProfile = waitTest.until
                (ExpectedConditions.presenceOfElementLocated(By.className("header2__nav")));
        actor.moveToElement(myProfile).build().perform();
        waitTest.until(ExpectedConditions.presenceOfElementLocated(By.className("header2-user-menu")));
        driver.findElement(By.className("header2-user-menu")).findElement(By.linkText("Выход")).click();
    }

    @Test
    public void navigateToBeruAuthPage(){
        //убираем рекламный баннер
//        driver.findElement(By.cssSelector("div.modal__content > div")).click();
        //входим в аккаунт
        signIn();
        //проверка соответствия названия заголовка
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.header2__nav")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.header2-nav__user")).getText(), "Мой профиль",
                "Кнопка входа профиля не найдена");

    }

    private static String newRegionName = "Хвалынск";

    @Test
    public void regionChangeTest() {
        //убираем рекламный баннер
//        driver.findElement(By.className("_1ZYDKa22GJ")).click();
        //нажимаем на текущий регион, чтобы сменить его
        WebElement regions_line = driver.findElement(By.className("region-form-opener"))
                .findElement(By.className("link__inner"));
        regions_line.click();
        //ждем, пока появится окно смены региона
        WebElement districtPopup = waitTest.until
                (ExpectedConditions.presenceOfElementLocated(By.className("region-select-form")));
        //находим поле ввода региона
        WebElement regionField = districtPopup.findElement(By.className("input__control"));
        regionField.sendKeys(newRegionName);
        // ждем, пока появится список регионов и выбираем заданный
        waitTest.until(ExpectedConditions.visibilityOfElementLocated(By.className("region-suggest__list-item"))).click();
        //нажать на кнопку "Продолжить с новым регионом"
        districtPopup.findElement(By.className("button2")).click();
        //ждем, пока страница загрузится (элементы "footer social media" всегда загружаются последними)
        waitTest.until(ExpectedConditions.visibilityOfElementLocated(By.className("footer__social-media")));
        //находим элемент с названием региона на странице
        regions_line = driver.findElement(By.className("region-form-opener")).findElement(By.className("link__inner"));
        //проверяем, что название региона изменилось на заданное
        Assert.assertEquals(newRegionName, regions_line.getText(),
                "Region name on main page wasn't changed to" + newRegionName);
        //входим в аккаунт
        signIn();
        //переходим в настройки аккаунта
        Actions actor = new Actions(driver);
        WebElement myProfile = waitTest.until
                (ExpectedConditions.presenceOfElementLocated(By.className("header2__nav")));
        actor.moveToElement(myProfile).build().perform();
        waitTest.until(ExpectedConditions.presenceOfElementLocated(By.className("header2-user-menu")));
        driver.findElement(By.className("header2-user-menu")).findElement(By.linkText("Настройки")).click();
        //проверяем, что регион в настройках идентичен региону вверху страницы
        WebElement myRegion = waitTest.until(ExpectedConditions.presenceOfElementLocated
                (By.className("settings-list__title"))).findElement(By.className("link__inner"));
        regions_line = driver.findElement(By.className("region-form-opener")).findElement(By.className("link__inner"));
        Assert.assertEquals(regions_line.getText(), myRegion.getText(),
                "Region name on the top of the page is not equal to the region name in settings");

    }
}
