package UiRegressionTests.WebTests.DoctorDashboradTests.SalutaDoctorDashboardTests;

import Entities.Doctor;
import Entities.Patient;
import Framework.DataProviderClass;
import PageObjects.DoctorHomePage;
import PageObjects.LoginPage;
import UiRegressionTests.ChLoginBaseTest;
import org.testng.annotations.Test;

public class SurveyTests extends ChLoginBaseTest {

    @Test(dataProvider = "login-doctor-And-patient-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyThatTheSurveyIsSentToThePatientByTheDoctor(Doctor doctor, Patient patient) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsDoctor(doctor);
        DoctorHomePage doctorHomePage = new DoctorHomePage(driver);
        doctorHomePage.selectPatient(patient);
        doctorHomePage.sendSurveyToThePatientAndVerifyTheSurveyWasSentSuccessfully();
    }
}
