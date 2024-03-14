package UiRegressionTests.MobileTests;

import Entities.Patient;
import Framework.DataProviderClass;
import PageObjects.Mobile.MobileHomePage;
import UiRegressionTests.MobileBaseTest;
import org.testng.annotations.Test;

public class PatientTests extends MobileBaseTest {
    @Test(dataProvider = "existing-hycare-patient-data", dataProviderClass = DataProviderClass.class)
    public void existingHyCarePatientCanLoginSuccessfully(Patient patient) {
        MobileHomePage mobileHomePage = new MobileHomePage(androidDriver);
        mobileHomePage.fillUserNameAndPasswordForAnExistingPatient(patient);
        mobileHomePage.verifyThatTheUserLoginSuccessfully();
        mobileHomePage.verifyDiGA1BoxIsAppearsInTheHomePage();
        mobileHomePage.verifyThatAfterClickingOnTheDIGA1ButtonTheRequiredMessageIsAppeared();
    }

    @Test(dataProvider = "existing-hycare-patient-data", dataProviderClass = DataProviderClass.class)
    public void verifyThatTheTermsAndConditionsIsExistInTheProfileTab(Patient patient) {
        MobileHomePage mobileHomePage = new MobileHomePage(androidDriver);
        mobileHomePage.fillUserNameAndPasswordForAnExistingPatient(patient);
        mobileHomePage.verifyTermsAndConditionsIsExistInTheProfileTab();
    }

    @Test(dataProvider = "existing-hycare-patient-data", dataProviderClass = DataProviderClass.class)
    public void verifyThatCanLogoutSuccessfullyFromTheApp(Patient patient) {
        MobileHomePage mobileHomePage = new MobileHomePage(androidDriver);
        mobileHomePage.fillUserNameAndPasswordForAnExistingPatient(patient);
        mobileHomePage.logoutFromTheApp();
        mobileHomePage.verifyThatCanLogoutSuccessfullyFromTheApp();
    }

}
