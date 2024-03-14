package PageObjects;

import Entities.Patient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SalutaSelfRegistrationPage extends BasePage{
    public SalutaSelfRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void fillFieldsForCreatePatient(Patient patient) {
        fillMandatoryFields(patient);
        agreeCheckbox();
        clickStartNowButton();
    }

    private void fillMandatoryFields(Patient patient) {
        setFirstName(patient.getFirstName());
        setLastName(patient.getLastName());
        setEmail(patient.getEmail());
        setEmailConfirm(patient.getEmail());
        setMobileNumber(patient.getMobileNumber());
        setConfirmMobileNumber(patient.getMobileNumber());
        setBirthdate(patient.getBirthDate());
    }

    private void setBirthdate(String birthdate) {
        fillTextByName(birthdate, "birthdate");
    }

    private void setMobileNumber(String mobileNumber) {
        fillTextByName(mobileNumber, "mobileNumber");
    }

    private void setConfirmMobileNumber(String mobileNumber) {
        WebElement telephone = driver.findElements(By.className("react-tel-input")).get(1);
        WebElement mobile = telephone.findElement(By.className("form-control"));
        click(mobile);
        mobile.clear();
        mobile.sendKeys(mobileNumber);
    }

    private void setEmail(String email) {
        fillTextByName(email, "email");
    }

    private void setEmailConfirm(String email) {
        fillTextByName(email, "emailConfirm");
    }

    private void setLastName(String lastName) {
        fillTextByName(lastName, "lastName");
    }

    private void setFirstName(String firstName) {
        fillTextByName(firstName, "firstName");
    }

    public void clickStartNowButton() {
        WebElement startNowButton = driver.findElement(By.className("btn-dark"));
        click(startNowButton);
    }

    public void agreeCheckbox() {
        WebElement TermsCheckbox = driver.findElement(By.name("terms"));
        click(TermsCheckbox);
    }

    public void verifyGettingSuccessPage() {
        try {
            waitFewSeconds(5000);
            driver.findElement(By.className("checkmark"));
            driver.findElement(By.className("message-sent"));
        } catch (Exception e) {
            Assert.fail("There is an issue in the self-registration page, please check! ");
        }
    }

}
