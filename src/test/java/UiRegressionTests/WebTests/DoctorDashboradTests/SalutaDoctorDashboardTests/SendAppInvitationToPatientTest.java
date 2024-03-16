package UiRegressionTests.WebTests.DoctorDashboradTests.SalutaDoctorDashboardTests;

import org.testng.annotations.Test;

import Entities.Admin;
import Framework.DataProviderClass;
import PageObjects.AdminHomePage;
import PageObjects.DoctorHomePage;
import PageObjects.LoginPage;
import UiRegressionTests.ChLoginBaseTest;
import Entities.Admin;
import Entities.Doctor;
import Entities.Patient;

public class SendAppInvitationToPatientTest extends ChLoginBaseTest {
 
    //used for send app invitation to patient by doctor
    @Test(dataProvider = "create-saluta-patient-by-doctor-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyThatTheDoctorSendAppInvitationToPatient(Patient patient, Admin admin, Doctor doctor) {
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsDoctor(doctor);

        System.out.println("Doctor login success");
        
        //create patient and logout from doctor
        DoctorHomePage doctorHomePage = new DoctorHomePage(driver);
        doctorHomePage.sendAppInvitationToPatientByDoctor(patient);

    }//end

    //used for send app invitation to patient by admin
    @Test(dataProvider = "create-saluta-patient-by-doctor-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyThatTheAdminSendAppInvitationToPatient(Patient patient, Admin admin, Doctor doctor) {
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsAdmin(admin);

        System.out.println("Admin login success");
        
        //create patient and logout from doctor
        AdminHomePage adminHomePage = new AdminHomePage(driver);
        adminHomePage.sendAppInvitationToPatientByAdmin(patient);

    }//end


    //used for send invitation to the Users tab user by admin
    @Test(dataProvider = "create-saluta-patient-by-doctor-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyThatTheAdminSendAppInvitationToUsersTabUser(Patient patient, Admin admin, Doctor doctor) {
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsAdmin(admin);

        System.out.println("Admin login success");
        
        //create patient and logout from doctor
        AdminHomePage adminHomePage = new AdminHomePage(driver);
        adminHomePage.sendAppInvitationToUsersTabUserByAdmin(patient);

    }//end



    //used for check if app activated then disabled send invitation
    @Test(dataProvider = "create-saluta-patient-by-doctor-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyThatDoctorNotSendAppInvitationToPatientWhichPatientAppActivated(Patient patient, Admin admin, Doctor doctor) {
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsDoctor(doctor);

        System.out.println("Doctor login success");
        
        //create patient and logout from doctor
        DoctorHomePage doctorHomePage = new DoctorHomePage(driver);
        doctorHomePage.checkAdminNotSendAppInvitationToAppActivePatient(patient);

    }//end

}
