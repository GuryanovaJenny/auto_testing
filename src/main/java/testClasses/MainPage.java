package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MainPage {

    private WebDriver driver;
    private WebDriverWait waitTest;

    private WebElement regions_line;

    public MainPage(WebDriver driver, WebDriverWait waitTest){
        this.driver = driver; this.waitTest = waitTest;
    }

    public void clickLoginButton(){
        driver.findElement(By.xpath("//span[@title='Войти в аккаунт']")).click();
    }

    // check button text changes from "Войти в аккаунт" to "Мой профиль"
    public void enterCheck(){
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.header2__nav")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.header2-nav__user")).getText(), "Мой профиль",
                "Кнопка входа профиля не найдена");
    }

    public void pressRegionChangeButton(){
        regions_line = driver.findElement(By.className("region-form-opener")).findElement(By.className("link__inner"));
        regions_line.click();
    }

    public void enterRegion(String newRegionName){
        driver.findElement(By.xpath("//input[@class='input__control']")).sendKeys(newRegionName);
    }

    public void selectRegion(String newRegionName){
        // wait until entered text is displayed on popup
        waitTest.until(ExpectedConditions.visibilityOfElementLocated(By.className("suggestick-list"))).click();
        // submit changes
        driver.findElement(By.xpath("//div[@class='header2-region-popup']//button")).click();
        // wait until page loading is complete (footer social media elements are always loaded last)
        waitTest.until(ExpectedConditions.visibilityOfElementLocated(By.className("footer__social-media")));
        // region name line on the top of the main page
        regions_line = driver.findElement(By.className("region-form-opener")).findElement(By.className("link__inner"));
        // test of region name changes
        Assert.assertEquals(newRegionName, regions_line.getText(),
                "Region name on main page wasn't changed to " + newRegionName);
    }

    public void clickSettingsButton(){
        Actions actor = new Actions(driver);
        actor.moveToElement(driver.findElement(By.cssSelector("span.header2-nav-item__icon." +
                "header2-nav-item__icon_type_profile"))).build().perform();
        driver.findElement(By.className("header2-user-menu__item_type_settings")).click();//.findElement(By.linkText("Настройки")).click();
    }
}
