package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
// import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PatientHomePage extends BasePage {
    public PatientHomePage(WebDriver driver) {
        super(driver);
    }

    public void verifyThatTheUserLoggedInSuccessfully() {
        agreeTermsAndConditions();
        try {
            driver.findElement(By.className("chat-content"));
            driver.findElement(By.id("chat-input"));
            driver.findElement(By.className("events"));
        } catch (Exception e) {
            Assert.fail("There is an issue in the patient home page, please check! ");
        }
    }
    public void verifyPatientHomeScreenBoxes() {

        try{
            //click on home page
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()=\"Home\"])[1]")));
            driver.findElement(By.xpath("(//span[text()=\"Home\"])[1]")).click();

            //Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='people']")));
            boolean patientbox = driver.findElement(By.xpath("//*[text()='people']")).isDisplayed();
            System.out.println("Is 'patientbox' element displayed? " + patientbox);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='chat']")));
            boolean messagesbox = driver.findElement(By.xpath("//*[text()='chat']")).isDisplayed();
            System.out.println("Is 'messagesbox' element displayed? " + messagesbox);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='account_circle']")));
            boolean Profilebox = driver.findElement(By.xpath("//*[text()='account_circle']")).isDisplayed();
            System.out.println("Is 'Profilebox' element displayed? " + Profilebox);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='schedule']")));
            boolean appointment = driver.findElement(By.xpath("//*[text()='schedule']")).isDisplayed();
            System.out.println("Is 'appointment' element displayed? " + appointment);

            Thread.sleep(2000);
        
        } catch (Exception e) {

            Assert.fail("Issue on patient homepage checking the content of boxes..");
        }
    }

    public void respectiveScreenForPateint() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()=\"Home\"])[1]")));

        driver.findElement(By.xpath("(//span[text()=\"Home\"])[1]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()=\"Patients\"])[2]")));

        driver.findElement(By.xpath("(//span[text()=\"Patients\"])[2]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4//span[text()=\"Patients\"]")));
        assert driver.findElement(By.xpath("//h4//span[text()=\"Patients\"]")).isDisplayed();
        driver.findElement(By.xpath("(//span[text()=\"Home\"])[1]")).click();
        System.out.println("Checked Patient Page");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=\"Messages\"])[2]")));
        driver.findElement(By.xpath("(//span[text()=\"Messages\"])[2]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()=\"Messages\"])[2]")));
        assert driver.findElement(By.xpath("(//span[text()=\"Messages\"])[2]")).isDisplayed();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=\"Home\"])[1]")));
        driver.findElement(By.xpath("(//span[text()=\"Home\"])[1]")).click();
        System.out.println("Checked Messages Page");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=\"Profile\"])[2]")));
        driver.findElement(By.xpath("(//span[text()=\"Profile\"])[2]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4//span[contains(text(),\"Welcome\")]")));
        assert driver.findElement(By.xpath("//h4//span[contains(text(),\"Welcome\")]")).isDisplayed();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=\"Home\"])[1]")));
        driver.findElement(By.xpath("(//span[text()=\"Home\"])[1]")).click();
        System.out.println("Checked Profile Page");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=\"Appointments\"])[2]")));
        driver.findElement(By.xpath("(//span[text()=\"Appointments\"])[2]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"Time\"]")));
        assert driver.findElement(By.xpath("//span[text()=\"Time\"]")).isDisplayed();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=\"Home\"])[1]")));
        driver.findElement(By.xpath("(//span[text()=\"Home\"])[1]")).click();
        System.out.println("Checked Appointments Page");

    }

    public void RecentConversations() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()=\"Home\"])[1]")));
        driver.findElement(By.xpath("(//span[text()=\"Home\"])[1]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"Recent Conversations\"]")));
        assert driver.findElement(By.xpath("//span[text()=\"Recent Conversations\"]")).isDisplayed();
        System.out.println("Displayed Recent Conversations");

    }

    public void verifyPatientChangeProfilePictureLanguage() {

        try{

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=\"Profile\"])")));
            driver.findElement(By.xpath("(//span[text()=\"Profile\"])")).click();
            
            //clicking on dropdown for opening the dropdown options
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='English']")));
            WebElement dropdown = driver.findElement(By.xpath("//div[text()='English']"));
            dropdown.click();

            //selecting the language Deutsch
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@id='de'])")));
            WebElement languageSelectDeutsch = driver.findElement(By.xpath("//li[@id='de']"));
            languageSelectDeutsch.click();

            System.out.println("After selecting dutch language");

            //selecting the language English
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@id='en'])")));
            WebElement languageSelectenglish = driver.findElement(By.xpath("//li[@id='en']"));
            languageSelectenglish.click();
            
            System.out.println("After selecting english language");

            //profile change : //button[@id='profile-change-photo']
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='profile-change-photo']")));
            WebElement profileSettingBtn = driver.findElement(By.xpath("//button[@id='profile-change-photo']"));
            profileSettingBtn.click();

            System.out.println("after profile change");

            //upload
            WebElement upload_file = driver.findElement(By.xpath("//input[@id='browse-button']"));
            upload_file.sendKeys("D:/Yogesh/patient-picture.jpg");

            System.out.println("after profile upload");

            //save profile picture jss62
            WebElement save_profile = driver.findElement(By.xpath("//span[text()=\"Save\"]"));
            save_profile.click();

            System.out.println("Profile SAVED Successfully");
            
            Thread.sleep(8000);


        } catch(Exception e){

            Assert.fail("Issue in patient changing profile picture or language change..");
        }
    }

    //change start : 03-01
    /*public void rememberMeFunWithHomeScreenUrlVerifyOfPatient() {

        try{
            System.out.println("called patient homepage..");
            //verify homescreen url
            String ActualUrl = driver.getCurrentUrl();
            String ExpectedUrl = "https://qa.dev.docdok.ch/private/app";
            Assert.assertEquals(ExpectedUrl, ActualUrl);

            System.out.println("HomeScreen URL is Match of patient");
        
        } catch(Exception e){

            Assert.fail("Error in url of user as patient..");
        }
    } */
    //change end

}
