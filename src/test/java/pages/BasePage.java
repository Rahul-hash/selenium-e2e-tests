package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasePage {

    //******************Configurations
    public static String environment = "https://survey.quantilope.com/SfidqfGjdrvMzwzWK/ayEBmcoPiyFuhzMPy/9xednGW4bJAGd7sv2/";
    public static String dashboardurl = "https://app.quantilope.com/share/dbSDoh9NXjyXqjdHN?type=dashboard";
    public static String screenshotsPath = "src/screenshots";
    public static String chromedriverLocation = "src/chromedriver";
    //******************

    public static WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait (driver,30);
    }

    public void openPage (String siteAddress) {
        driver.get( siteAddress);
    }

    public void takeScreenShot (String screenShotName) {
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File(screenshotsPath + screenShotName));
        } catch (IOException e) {
            String screenShotError = "Failed to capture screenshot: " + e.getMessage();
            System.out.println("ScreenShotError " + screenShotError);
        }
    }

    public void elementClick (By elementBy) {
        driver.findElement(elementBy).click();
    }

    public void waitElementVisible (By elementBy){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    public void waitElementClickable (By elementBy){
        wait.until(ExpectedConditions.elementToBeClickable(elementBy));
    }

    public void sendKeys (By elementBy, String Text) {
        driver.findElement(elementBy).sendKeys(Text);
    }

    public void sendKeyDownAndEnter (By elementBy) {
        driver.findElement(elementBy).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(elementBy).sendKeys(Keys.ENTER);
    }

    public void clearTextField (By elementBy) {
        driver.findElement(elementBy).sendKeys(Keys.CONTROL + "a");
        driver.findElement(elementBy).sendKeys(Keys.DELETE);
    }

    public void isElementVisible (By elementBy) {
        driver.findElement(elementBy).isDisplayed();
    }

    public void isElementEnabled (By elementBy) {driver.findElement(elementBy).isEnabled(); }
}
