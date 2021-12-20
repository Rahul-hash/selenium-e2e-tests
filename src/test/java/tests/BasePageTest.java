package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import pages.BasePage;
import pages.SurveyTestPage;

import java.io.IOException;
import java.util.Optional;

public class BasePageTest {

    public WebDriver driver;
//    public static String authToken;
    public BasePage basePage;
    public SurveyTestPage surveyTestPage;



    @BeforeAll
    public static void initialSetUP () throws IOException {
        //checking Maven cmd line params
        try {
            if (!(System.getProperty("testEnv")).equals("")) { BasePage.environment = System.getProperty("testEnv");};
            if (!(System.getProperty("driverLocation")).equals("")) {
                BasePage.chromedriverLocation = System.getProperty("driverLocation");};
            if (!(System.getProperty("screensPath")).equals("")) {
                BasePage.screenshotsPath = System.getProperty("screensPath");};
//            if (!(System.getProperty("userName")).equals("")) {
//                BasePage.username = System.getProperty("userName");};
//            if (!(System.getProperty("passWord")).equals("")) {
//                BasePage.password = System.getProperty("passWord");};

        } catch (Exception e) {
            e.printStackTrace();
        }

//        authToken = Authentication.authTokenRequest();
    }

    @BeforeEach
    public void setUP() {
        System.setProperty("webdriver.chrome.driver", BasePage.chromedriverLocation);

        ChromeOptions options = new ChromeOptions();
//
//        options.addArguments("--headless", "--window-size=1920,1080" );
//        options.addArguments("--no-sandbox",  "--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45");

        driver = new ChromeDriver(options);


        basePage = PageFactory.initElements(driver, BasePage.class);
        surveyTestPage = PageFactory.initElements(driver, SurveyTestPage.class);
    }

    @RegisterExtension
    TestWatcher watcher = new TestWatcher() {
        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            basePage.takeScreenShot( context.getDisplayName() + ".jpg");
            driver.quit();
        }

        @Override
        public void testDisabled(ExtensionContext context, Optional<String> reason) {
            driver.quit();
        }

        @Override
        public void testSuccessful(ExtensionContext context) {
            driver.quit();
        }

        @Override
        public void testAborted(ExtensionContext context, Throwable cause) {
            driver.quit();
        }
    };
}

