package UiRegressionTests.WebTests.DoctorDashboradTests.SalutaDoctorDashboardTests;

import org.testng.annotations.Test;

import Entities.Admin;
import Entities.Doctor;
import Entities.Patient;
import Framework.DataProviderClass;
import PageObjects.DoctorHomePage;
import PageObjects.LoginPage;
import UiRegressionTests.ChLoginBaseTest;

public class DoctorsHomeScreenTests extends ChLoginBaseTest {

    @Test(dataProvider = "create-saluta-patient-by-doctor-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyTappingoneachfeatureshouldnavigatetorespectivescreens(Patient patient, Admin admin,
            Doctor doctor) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsDoctor(doctor);
        DoctorHomePage doctorHomePage = new DoctorHomePage(driver);
        doctorHomePage.respectiveScreenForDoctor();
    }

    @Test(dataProvider = "create-saluta-patient-by-doctor-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyHomeScreenDashboard(Patient patient, Admin admin,
            Doctor doctor) {
        LoginPage loginPage= new LoginPage(driver);
        loginPage.loginAsDoctor(doctor);
        DoctorHomePage DoctorHomePage = new DoctorHomePage(driver);
        DoctorHomePage.verifyDoctorHomeScreenBoxes();

    }

    // @Test(dataProvider = "create-saluta-patient-by-doctor-data", dataProviderClass = DataProviderClass.class)
    // public void shouldVerifyVerifyiftutorialsaredisplayed(Patient patient, Admin admin, Doctor doctor) {
    //     LoginPage loginPage = new LoginPage(driver);
    //     loginPage.loginAsDoctor(doctor);
    //     DoctorHomePage doctorHomePage = new DoctorHomePage(driver);
    //     doctorHomePage.tutorials();
    // }
    
    //changes start 02-01-24
    @Test(dataProvider = "create-saluta-patient-by-doctor-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyProfileLanguage(Patient patient, Admin admin,
            Doctor doctor) {
        LoginPage loginPage= new LoginPage(driver);
        loginPage.loginAsDoctor(doctor);
        
        DoctorHomePage DoctorHomePage = new DoctorHomePage(driver);
        DoctorHomePage.verifyDoctorChangeProfilePictureLanguage();

    }

    //changes start 03-01-24
    @Test(dataProvider = "create-saluta-patient-by-doctor-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyLoginWithRememberMeGoToHomeScreen(Patient patient, Admin admin,
            Doctor doctor) {
        LoginPage loginPage= new LoginPage(driver);
        loginPage.loginAsDoctorByRememberMe(doctor);
        
        //DoctorHomePage DoctorHomePage = new DoctorHomePage(driver);
        //DoctorHomePage.verifyDoctorChangeProfilePictureLanguage();

    }
}
