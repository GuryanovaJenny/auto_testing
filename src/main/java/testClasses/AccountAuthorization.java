package testClasses;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountAuthorization{

    private WebDriver driver;

    public AccountAuthorization(WebDriver driver){
        this.driver = driver;
    }

    @Step("Enter login")
    public void enterLogin() {
        // introduce login
        driver.findElement
                (By.xpath("//input[@name='login']")).sendKeys("jenny.guryanova@yandex.ru");
        // press button "Войти"
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Step("Enter password")
    public void enterPassword() {
        // wait until the password appear
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//input[@id='passp-field-passwd']")));
        driver.findElement
                (By.xpath("//input[@id='passp-field-passwd']")).sendKeys("Yamomoto80Slim");
        // press button "Войти"
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
}
