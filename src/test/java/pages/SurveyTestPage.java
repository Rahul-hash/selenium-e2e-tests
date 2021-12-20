package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.util.Assert;
import tests.BasePageTest;

import java.util.concurrent.TimeUnit;

public class SurveyTestPage extends BasePage{

    Integer noofSurveys = 0;
    Integer noofUpdatedSurveys = 0;

    public SurveyTestPage(WebDriver driver) {
        super(driver);
    }

    public By startsurveybutton = By.xpath("//*[@id='startSurvey']");
    public By nextpagebutton = By.xpath("//*[@id='nextPage']");
    public By noofengineersinput = By.xpath("//input[@type='text']");
    public By surveycomplete = By.xpath("//*[@class='surveyEndPage']");
    public By noofSurveyslabel = By.xpath("//*[contains(text(),\"Total\")]");

    public void openMainPage () {
        openPage(environment);
    }
    public void opensurveyDashboard () {
        openPage(dashboardurl);
    }
    public void startSurvey () {
        waitElementClickable(startsurveybutton);
        driver.findElement(startsurveybutton).click();
    }
    public void fillSurvey(String color, String noofEng, String loveforcheese ){
        waitElementClickable(By.xpath("//p[text()='"+color+"']"));
        driver.findElement(By.xpath("//p[text()='"+color+"']")).click();
        driver.findElement(nextpagebutton).click();
        waitElementClickable(noofengineersinput);
        driver.findElement(noofengineersinput).sendKeys(noofEng);
        driver.findElement(nextpagebutton).click();
        waitElementClickable(By.xpath("(//div[@class=\"qRadioDisplay\"])["+loveforcheese+"]"));
        driver.findElement(By.xpath("(//div[@class=\"qRadioDisplay\"])["+loveforcheese+"]")).click();
        driver.findElement(nextpagebutton).click();
        waitElementVisible(surveycomplete);
    }
    public void fillSurvey(String color){
        waitElementClickable(By.xpath("//p[text()='"+color+"']"));
        driver.findElement(By.xpath("//p[text()='"+color+"']")).click();
        driver.findElement(nextpagebutton).click();
        waitElementClickable(noofengineersinput);

    }
    public void checkSurvey() throws InterruptedException {
        waitElementVisible(noofSurveyslabel);
        noofSurveys = (int) driver.findElement(noofSurveyslabel).getText().split("=")[1].charAt(0);
        System.out.println("Initial no of surveys are " + noofSurveys);

    }
    public void validateUpdatedSurvey(){
        waitElementVisible(noofSurveyslabel);
        noofUpdatedSurveys = (int) driver.findElement(noofSurveyslabel).getText().split("=")[1].charAt(0);
        System.out.println("Updated no of surveys are " + noofUpdatedSurveys);
        Assertions.assertEquals(noofUpdatedSurveys, noofSurveys + 1);
    }

}
