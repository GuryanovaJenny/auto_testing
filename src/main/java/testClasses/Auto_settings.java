package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class Auto_settings {

    private static EventFiringWebDriver driver;
    private static WebDriverWait waitTest;

    @BeforeClass
    public static void setupClass() { WebDriverManager.chromedriver().setup();}

    @BeforeMethod
    public void setupTest() {
        WebDriverManager.chromedriver().setup();
        driver = new EventFiringWebDriver (new ChromeDriver());
        driver.register(new WebDriverEventListener());

        driver.manage().window().maximize();
        driver.get("https://beru.ru/");
        waitTest = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    protected static WebDriver getDriver() {
        return driver;
    }
    protected static WebDriverWait getWaitTest() {
        return waitTest;
    }

    @AfterMethod
    public void signOut() {
        // go to "Мой аккаунт" and press "Выход"
        WebElement profileName = waitTest.until
                (ExpectedConditions.visibilityOfElementLocated(By.className("header2__nav")));
        if (profileName.getText().equals("Мой профиль")) {
            profileName.click();
            driver.findElement(By.linkText("Выход")).click();
        }
        // close driver
        driver.quit();
    }
}
