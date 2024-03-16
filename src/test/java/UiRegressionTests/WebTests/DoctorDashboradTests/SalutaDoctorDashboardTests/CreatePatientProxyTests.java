package UiRegressionTests.WebTests.DoctorDashboradTests.SalutaDoctorDashboardTests;

import org.testng.annotations.Test;

import Entities.Admin;
import Entities.Doctor;
import Entities.Patient;
import Framework.DataProviderClass;
import PageObjects.DoctorHomePage;
import PageObjects.LoginPage;
import PageObjects.PatientHomePage;
import PageObjects.TestingUtilPage;
import UiRegressionTests.ChLoginBaseTest;


public class CreatePatientProxyTests extends ChLoginBaseTest {

    //used for a child patient
    @Test(dataProvider = "create-saluta-child-patient-by-doctor-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyThatTheDoctorCanCreateChildPatientSuccessfully(Patient patient, Admin admin, Doctor doctor) {
        
        System.out.println("called new file func");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsDoctor(doctor);

        System.out.println("login success");
        
        //create patient and logout from doctor
        DoctorHomePage doctorHomePage = new DoctorHomePage(driver);
        doctorHomePage.createChildPatientByTheDoctor(patient);

        TestingUtilPage testingUtilPage = new TestingUtilPage(driver);
        testingUtilPage.openActivationUrlByTestingUtil(admin);

        loginPage.activateTheUserBySettingNewPassword(patient);

        PatientHomePage patientHomePage = new PatientHomePage(driver);
        patientHomePage.verifyThatTheUserLoggedInSuccessfully();

    }//end child used func

}
