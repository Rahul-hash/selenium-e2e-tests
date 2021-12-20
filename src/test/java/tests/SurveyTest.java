package tests;


import org.junit.jupiter.api.Test;


public class SurveyTest extends BasePageTest{


    @Test
    public void completeSurvey () throws InterruptedException {
        //Validating current no of surveys in existing survey dashboard
        surveyTestPage.opensurveyDashboard();
        surveyTestPage.checkSurvey();

        //Performing a new survey
        surveyTestPage.openMainPage();
        surveyTestPage.startSurvey();
        surveyTestPage.fillSurvey("Yellow","2","4");

        //Validating updated no of surveys in the survey dashboard
        surveyTestPage.opensurveyDashboard();
        surveyTestPage.validateUpdatedSurvey();
    }
    @Test
    public void partialsurvey () throws InterruptedException {
        //Validating current no of surveys in existing survey dashboard
        surveyTestPage.opensurveyDashboard();
        surveyTestPage.checkSurvey();

        //Performing a new survey
        surveyTestPage.openMainPage();
        surveyTestPage.startSurvey();
        surveyTestPage.fillSurvey("Yellow");

        //Validating updated no of surveys in the survey dashboard
        surveyTestPage.opensurveyDashboard();
        surveyTestPage.validateUpdatedSurvey();
    }

}
