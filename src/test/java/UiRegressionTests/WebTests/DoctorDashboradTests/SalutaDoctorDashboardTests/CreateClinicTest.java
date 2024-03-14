package UiRegressionTests.WebTests.DoctorDashboradTests.SalutaDoctorDashboardTests;

import org.testng.annotations.Test;

import Entities.Admin;
import Entities.Doctor;
import Entities.Patient;
import Framework.DataProviderClass;
import PageObjects.AdminHomePage;
import PageObjects.DoctorHomePage;
import PageObjects.LoginPage;
import PageObjects.TestingUtilPage;
import UiRegressionTests.ChLoginBaseTest;

public class CreateClinicTest extends ChLoginBaseTest {
 
    //used for create clinic by admin
    @Test(dataProvider = "create-clinic-by-admin", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyThatTheAdminCanCreateClinicsSuccessfully(Admin admin) {
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsAdmin(admin);

        System.out.println("Admin login success");
        
        //create patient and logout from doctor
        AdminHomePage adminhomepage = new AdminHomePage(driver);
        adminhomepage.createClinicByDoctor(admin);

    }//end

}
