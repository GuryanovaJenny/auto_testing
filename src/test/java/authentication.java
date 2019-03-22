import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class authentication extends auto_settings{

    @Test
    public void navigateToBeruAuthPage(){
      //  driver = new ChromeDriver();
      //  driver.manage().window().maximize();
      //  driver.get("https://beru.ru/");
        //убираем рекламный баннер
        driver.findElement(By.cssSelector("div.modal__content > div")).click();
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
        //проверка соответствия названия заголовка
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.header2__nav")));
        Assert.assertEquals("Кнопка входа профиля не найдена", driver.findElement(By.cssSelector("div.header2__nav")).getText().equals("Мой профиль"));
    }
}
