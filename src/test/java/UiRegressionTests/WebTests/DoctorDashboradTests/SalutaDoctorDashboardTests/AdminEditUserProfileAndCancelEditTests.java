package UiRegressionTests.WebTests.DoctorDashboradTests.SalutaDoctorDashboardTests;

import org.testng.annotations.Test;

import Entities.Admin;
import Entities.Doctor;
import Entities.Patient;
import Framework.DataProviderClass;
import PageObjects.AdminHomePage;
import PageObjects.DoctorHomePage;
import PageObjects.LoginPage;
import UiRegressionTests.ChLoginBaseTest;

public class AdminEditUserProfileAndCancelEditTests extends ChLoginBaseTest {
    
    //used for profile edit and cancel edit profile by doctor
    @Test(dataProvider = "create-saluta-patient-by-doctor-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyThatTheAdminCanEditAndCancelEditProfileOfUser(Patient patient, Admin admin, Doctor doctor) {
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsAdmin(admin);

        System.out.println("Admin login success");
        
        //create patient and logout from doctor
        AdminHomePage adminHomePage = new AdminHomePage(driver);
        adminHomePage.UserProfileEditAndCancelEditByAdmin(patient);

    }//end

}
