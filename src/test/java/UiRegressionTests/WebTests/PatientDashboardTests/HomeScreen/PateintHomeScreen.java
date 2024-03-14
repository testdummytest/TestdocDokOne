package UiRegressionTests.WebTests.PatientDashboardTests.HomeScreen;

import org.testng.annotations.Test;

import Entities.Admin;
import Entities.Doctor;
import Entities.Patient;
import Framework.DataProviderClass;
import PageObjects.DoctorHomePage;
import PageObjects.LoginPage;
import PageObjects.PatientHomePage;
import UiRegressionTests.ChLoginBaseTest;

public class PateintHomeScreen extends ChLoginBaseTest {
    @Test(dataProvider = "login-patient-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyTappingoneachfeatureshouldnavigatetorespectivescreens(Patient patient) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsPatient(patient);
        PatientHomePage patientHomePage = new PatientHomePage(driver);
        patientHomePage.respectiveScreenForPateint();
    }

    @Test(dataProvider = "login-patient-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyHomeScreenDashboard(Patient patient) {
        LoginPage loginPage= new LoginPage(driver);
        loginPage.loginAsPatient(patient);
        PatientHomePage patientHomePage = new PatientHomePage(driver);
        patientHomePage.verifyPatientHomeScreenBoxes();

    }

    // @Test(dataProvider = "login-patient-data", dataProviderClass = DataProviderClass.class)
    // public void shouldVerifyTestifrecentconversationsarelistedontheHomescreen(Patient patient) {
    //     LoginPage loginPage = new LoginPage(driver);
    //     loginPage.loginAsPatient(patient);
    //     PatientHomePage patientHomePage = new PatientHomePage(driver);
    //     patientHomePage.RecentConversations();
    // }

    //changes start 03-01-24
    @Test(dataProvider = "login-patient-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyPatientProfileLanguage(Patient patient) {
        LoginPage loginPage= new LoginPage(driver);
        loginPage.loginAsPatient(patient);

        System.out.println("test success login patient");
        
        PatientHomePage patientHomePage = new PatientHomePage(driver);
        patientHomePage.verifyPatientChangeProfilePictureLanguage();

    }

    //changes start 03-01-24
    @Test(dataProvider = "login-patient-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyPatientLoginWithRememberMeGoToHomeScreen(Patient patient) {
        LoginPage loginPage= new LoginPage(driver);
        loginPage.loginAsPatientByRememberMe(patient);
    } //end    

}
