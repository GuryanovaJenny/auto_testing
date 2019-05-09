package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AccountAuthorization{

    private WebDriver driver;
    public WebDriverWait waitTest;

    public AccountAuthorization(WebDriver driver){
        this.driver = driver;
    }

    public void enterLogin() {
        // wait until the page download
     //   WebElement profileBtn = waitTest.until(ExpectedConditions.visibilityOfElementLocated(By.className("header2-nav__user")));
        // press the Sign In button
     //   profileBtn.click();
     //   profileBtn.click();
     //   MainPage home = new MainPage(getDriver());
     //   clickLoginButton();
        // introduce login
        driver.findElement
                (By.xpath("//input[@name='login']")).sendKeys("jenny.guryanova@yandex.ru");
        // press button "Войти"
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

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
