package testClasses;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SettingsPage {

    private static WebDriver driver;

    public SettingsPage(WebDriver driver){
        SettingsPage.driver = driver;
    }

    @Step("Check region name and delivery region name")
    public void checkDeliveryRegionName(){
        WebElement regions_line = driver.findElement(By.className("region-form-opener"))
                                        .findElement(By.className("link__inner"));
        Assert.assertEquals(regions_line.getText(), driver
                                                    .findElement(By.className("settings-list__title"))
                                                    .findElement(By.className("link__inner")).getText(),
                "Region name on the top of the page is not equal to the region name in settings");
    }
}
