package UiRegressionTests.WebTests.SelfRegistrationTests.SalutaSelfRegistrationTests;

import Entities.Admin;
import Entities.Patient;
import Framework.DataProviderClass;
import PageObjects.*;
import UiRegressionTests.SalutaBaseTest;
import org.testng.annotations.Test;

public class SalutaSelfRegistrationTests extends SalutaBaseTest {
    @Test(dataProvider = "create-saluta-patient-by-self-Registration-data", dataProviderClass = DataProviderClass.class)
    public void createPatient(Patient patient, Admin admin) {
        SalutaSelfRegistrationPage salutaSelfRegistrationPage = new SalutaSelfRegistrationPage(driver);
        salutaSelfRegistrationPage.fillFieldsForCreatePatient(patient);
        salutaSelfRegistrationPage.verifyGettingSuccessPage();
        TestingUtilPage testingUtilPage = new TestingUtilPage(driver);
        testingUtilPage.openActivationUrlByTestingUtil(admin);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.activateTheUserBySettingNewPassword(patient);
        PatientHomePage patientHomePage = new PatientHomePage(driver);
        patientHomePage.verifyThatTheUserLoggedInSuccessfully();
    }

}
