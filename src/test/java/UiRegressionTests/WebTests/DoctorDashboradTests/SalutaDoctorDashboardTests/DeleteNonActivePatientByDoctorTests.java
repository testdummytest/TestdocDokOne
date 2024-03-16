package UiRegressionTests.WebTests.DoctorDashboradTests.SalutaDoctorDashboardTests;

import org.testng.annotations.Test;
import Entities.Admin;
import Entities.Doctor;
import Entities.Patient;
import Framework.DataProviderClass;
import PageObjects.DoctorHomePage;
import PageObjects.LoginPage;
// import PageObjects.PatientHomePage;
// import PageObjects.TestingUtilPage;
import UiRegressionTests.ChLoginBaseTest;

public class DeleteNonActivePatientByDoctorTests extends ChLoginBaseTest {
    
    @Test(dataProvider = "create-saluta-patient-by-doctor-data", dataProviderClass = DataProviderClass.class)
    public void createPatientAndDeleteNonActivePatientByDoctor(Patient patient, Admin admin, Doctor doctor) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsDoctor(doctor);
        //create patient and logout from doctor
        DoctorHomePage doctorHomePage = new DoctorHomePage(driver);
        doctorHomePage.createanddeletepatcallbackfunc(patient);

    }

}
