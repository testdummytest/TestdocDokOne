package UiRegressionTests.HybridTests;

import Entities.Admin;
import Entities.Doctor;
import Entities.Patient;
import Framework.DataProviderClass;
import PageObjects.DoctorHomePage;
import PageObjects.LoginPage;
import PageObjects.Mobile.MobileHomePage;
import UiRegressionTests.ChLoginBaseTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class patientTests extends ChLoginBaseTest {

    @Test(dataProvider = "create-hycare-patient-by-doctor-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyValidationOfNewHyCarePatient(Patient patient, Admin admin, Doctor doctor) throws MalformedURLException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsDoctor(doctor);
        DoctorHomePage doctorHomePage = new DoctorHomePage(driver);
        doctorHomePage.createPatientByHyCareDoctorForMobile(patient);
        MobileHomePage mobileHomePage = new MobileHomePage(androidDriver);
        mobileHomePage.setupForMobile();
        mobileHomePage.loginAsNewPatient(patient);
        mobileHomePage.goToTheMessagesAndClickOnJoinDiGA1();
        mobileHomePage.verifySecondMessage();
        mobileHomePage.verifyDiGA1BoxIsAppearsInTheHomePage();
    }

    @Test(dataProvider = "existing-hycare-patient-and-doctor-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyThatThePatientCanSeeDoctorMessages(Patient patient, Doctor doctor) throws MalformedURLException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsDoctor(doctor);
        DoctorHomePage doctorHomePage = new DoctorHomePage(driver);
        doctorHomePage.selectPatient(patient);
        doctorHomePage.sendMessageToPatient();
        MobileHomePage mobileHomePage = new MobileHomePage(androidDriver);
        mobileHomePage.setupForMobile();
        mobileHomePage.fillUserNameAndPasswordForAnExistingPatient(patient);
        mobileHomePage.verifyMessagesBadgeAppearsInTheHomePage();
        mobileHomePage.verifyMessageContent();
        mobileHomePage.verifyMessagesBadgeIsDisappearsFromHomePage();
    }

    @Test(dataProvider = "existing-hycare-patient-and-doctor-data", dataProviderClass = DataProviderClass.class)
    public void shouldVerifyThatThePatientCanSeeTheSurveyThatTheDoctorSent(Patient patient, Doctor doctor) throws MalformedURLException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAsDoctor(doctor);
        DoctorHomePage doctorHomePage = new DoctorHomePage(driver);
        doctorHomePage.selectPatient(patient);
        doctorHomePage.sendVasSurveyToThePatient();
        MobileHomePage mobileHomePage = new MobileHomePage(androidDriver);
        mobileHomePage.setupForMobile();
        mobileHomePage.fillUserNameAndPasswordForAnExistingPatient(patient);
        mobileHomePage.verifySurveysBadgeAppearsInTheHomePage();
        mobileHomePage.fillTheSurveyAndVerifyTheSurveyStatusIsChangedToComplete();
        mobileHomePage.verifySurveysBadgeIsDisappearsFromHomePage();
    }
}
