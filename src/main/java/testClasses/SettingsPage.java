package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SettingsPage {

    private static WebDriver driver;
    public WebDriverWait waitTest;

    public WebElement regions_line;

    public SettingsPage(WebDriver driver){
        this.driver = driver;
    }

    public void checkDeliveryRegionName(){
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
