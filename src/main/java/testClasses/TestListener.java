package testClasses;

import io.qameta.allure.Attachment;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult result) {
        saveScreenShot();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenShot() {
        return ((TakesScreenshot) Auto_settings.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
