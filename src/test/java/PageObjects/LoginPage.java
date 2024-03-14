package PageObjects;

import Entities.Admin;
import Entities.Doctor;
import Entities.Patient;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void activateTheUserBySettingNewPassword(Patient patient) {
        setCodeReceivedToTheNewUser(patient.getSmsCode());
        setNewPassword(patient.getPassword(), patient.getConfirmPassword());
    }

    // //new added for doc assistant activation
    // public void activateTheDocAssistantBySettingNewPassword(Admin admin) {
    //     setCodeReceivedToTheNewUser(admin.getSmsCode());
    //     setNewPassword(admin.getPassword(), admin.getConfirmPassword());
    // }//end


    //new added for doc assistant activation
    public void activateTheDocAssistantBySettingNewPassword(Doctor doctor) {
        setCodeReceivedToTheNewUser(doctor.getSmsCode());
        setNewPassword(doctor.getPassword(), doctor.getConfirmPassword());
    }//end


    public void loginAsPatient(Patient patient) {
        loginAsUser(patient.getEmail(), patient.getPassword());
    }

    public void loginAsDoctor(Doctor doctor) {
        loginAsUser(doctor.getEmail(), doctor.getPassword());
    }

    //change start for patient 03-01
    public void loginAsDoctorByRememberMe(Doctor doctor) {
        loginAsUserByCheckingRememberMe(doctor.getEmail(), doctor.getPassword());
    }
    //change end

    //change start for doctor 03-01
    public void loginAsPatientByRememberMe(Patient patient) {
        loginAsUserByCheckingRememberMe(patient.getEmail(), patient.getPassword());
    }
    //change end

    //for admin
    public void loginAsAdmin(Admin admin) {
        loginAsUser(admin.getEmail(), admin.getPassword());
    }//end admin

}
