package UiRegressionTests.WebTests.DoctorDashboradTests.SalutaDoctorDashboardTests;

import org.testng.annotations.Test;

import Entities.Admin;
import Entities.Doctor;
import Entities.Patient;
import Framework.DataProviderClass;
import PageObjects.AdminHomePage;
import PageObjects.DoctorHomePage;
import PageObjects.LoginPage;
import PageObjects.PatientHomePage;
import PageObjects.TestingUtilPage;
import UiRegressionTests.ChLoginBaseTest;

public class CreateMPATest extends ChLoginBaseTest {

    //used for a child patient
    @Test(dataProvider = "create-doctor-assistant-by-admin", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyThatTheAdminCanCreateAssistant_MPASuccessfully(Patient patient, Admin admin, Doctor doctor) {
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsAdmin(admin);

        System.out.println("Admin login success");
        
        //create patient and logout from doctor
        AdminHomePage adminhomepage = new AdminHomePage(driver);
        adminhomepage.createAssistantByTheAdmin(admin);

        TestingUtilPage testingUtilPage = new TestingUtilPage(driver);
        testingUtilPage.openActivationUrlByTestingUtil(admin);

        // loginPage.activateTheDocAssistantBySettingNewPassword(admin);
        loginPage.activateTheDocAssistantBySettingNewPassword(doctor);

        DoctorHomePage doctorHomePage = new DoctorHomePage(driver);
        doctorHomePage.verifyThatTheDocAssistLoggedInSuccessfully();

    }//end child used func

}
