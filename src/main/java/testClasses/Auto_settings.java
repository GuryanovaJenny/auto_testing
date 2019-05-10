package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class Auto_settings{

    private WebDriver driver;
    private WebDriverWait waitTest;
    protected WebDriver getDriver(){
        return driver;
    }
    protected WebDriverWait getWaitTest(){
        return waitTest;
    }

    @BeforeClass
    public static void setupClass() { WebDriverManager.chromedriver().setup();}

    @BeforeMethod
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://beru.ru/");
        waitTest = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void signOut() {
        // go to "Мой аккаунт" and press "Выход"
        Actions actor = new Actions(driver);
        WebElement myProfile = waitTest.until
                (ExpectedConditions.presenceOfElementLocated(By.className("header2-nav__user")));
        actor.moveToElement(myProfile).build().perform();
        waitTest.until(ExpectedConditions.presenceOfElementLocated(By.className("header2-user-menu")));
        driver.findElement(By.className("header2-user-menu")).findElement(By.linkText("Выход")).click();

        // close driver
        driver.quit();
    }
}
