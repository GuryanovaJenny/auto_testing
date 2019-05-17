package testClasses;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class ToothbrushesShoppingPage {

    private WebDriver driver;
    private WebDriverWait waitTest;

    public ToothbrushesShoppingPage(WebDriver driver, WebDriverWait waitTest){
        this.driver = driver; this.waitTest = waitTest;
    }

    @Step("Enter price range")
    public void enterPriceRange(){
        driver.findElement(By.xpath("//input[@name='Цена от']")).sendKeys("999");
        driver.findElement(By.xpath("//input[@name='Цена до']")).sendKeys("1999");
        waitTest.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("div.NZiH_Kn8Fj"))));
    }

    @Step("Show all electric toothbrushes")
    public void showMoreToothbrushes(){
        WebElement showMoreButton = driver.findElement(By.xpath("//div[@class='n-pager-more__button " +
                "pager-loader_preload']"));

        while(showMoreButton.isDisplayed()){
            showMoreButton.click();
            // wait for all prices to load
            waitTest.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector
                    ("div.grid-snippet.grid-snippet_react.b-zone.b-spy-visible")));
        }
    }

    @Step("Check price range")
    public void checkPriceRange() {
        List<WebElement> listPrice = driver.findElements(By.cssSelector("div.search-result-snippet span._1u3j_pk1db span"));
        for (int i = 0; i < listPrice.size(); i += 2) {
            int val_price = Integer.parseInt(listPrice.get(i).getText().replaceAll("\\s", ""));
            Assert.assertTrue(val_price > Integer.parseInt("999"));
            Assert.assertTrue(val_price < Integer.parseInt("1999"));
        }
    }

    @Step("Add the toothbrush")
    public void addToothbrush(){
        List<WebElement> cartButtonList = driver.findElements(By.cssSelector("button._4qhIn2-ESi._3OWdR9kZRH." +
                "THqSbzx07u"));
        cartButtonList.get(cartButtonList.size() - 2).click();
    }

    @Step("Click on the cart button")
    public void clickCartButton(){
        waitTest.until(ExpectedConditions.textToBePresentInElementLocated
                (By.className("_3UjOWy-LbN"), "Товар добавлен в корзину!"));
        driver.findElement(By.linkText("Перейти в корзину")).click();
    }
}
