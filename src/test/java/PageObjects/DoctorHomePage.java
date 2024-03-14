package PageObjects;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;


import Entities.Patient;
import Framework.DataProviderClass;
import Framework.Generate;

public class DoctorHomePage extends BasePage {
    WebDriver webDriver;
    String email_id_of_patient;
    public DoctorHomePage(WebDriver driver) {
        super(driver);
    }

    public void selectPatient(Patient patient) {
        clickOnPatientsTab();
        WebElement searchButton = driver.findElement(By.id("patient-search-bar-search-button"));
        click(searchButton);
        fillTextById(patient.getEmail(), "patient-search-bar-search-field");
        waitFewSeconds(5000);
        WebElement myPatients = driver.findElement(By.id("myPatients"));
        WebElement myPatient = myPatients.findElement(By.xpath(("//a[contains(@href, '/private/app/patients/PAT')]")));
        click(myPatient);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("close")));
            WebElement closeButton = driver.findElement(By.id("close"));
            click(closeButton);

        } catch (Exception e) {
            System.out.println("Not shown");

        }
    }

    private void clickOnPatientsTab() {
        WebElement patientsTab = driver.findElement(By.id("menu.patients"));
        click(patientsTab);
        System.out.println("CLICKED PATIENT TAB SUCCESS");
    }

    public void addAnEventForTodayDate() {
        WebElement addButton = driver.findElement(By.id("add-events-button"));
        click(addButton);
        WebElement addEventButton = driver.findElement(By.id("add-events-particular-button"));
        click(addEventButton);
        fillEventData();
        WebElement saveButton = driver.findElement(By.id("save"));
        click(saveButton);
    }

    private void fillEventData() {
        fillTextById("automationTest", "editEvent-name");
        fillTextById("my event " + Generate.todayDate(), "editEvent-notes");
    }

    public void verifyThatTheEventAddedSuccessfully() {
        WebElement closeSuccessfulMessageButton = driver.findElement(By.className("jss57"));
        click(closeSuccessfulMessageButton);
        clickOnTheLastEvent();
        String notes = driver.findElement(By.id("editEvent-notes")).getText();
        Assert.assertEquals(notes, "my event " + Generate.todayDate());
    }

    public void updateEvent() {
        WebElement startTime = driver.findElement(By.name("startTime"));
        click(startTime);
        startTime.sendKeys(Keys.ARROW_UP);
        fillTextByName("myLocation", "location");
        fillTextById("my event " + Generate.dateForAppointment(1), "editEvent-notes");
    }

    public void verifyThatTheEventUpdatedSuccessfully() {
        String getStartTime = driver.findElement(By.name("startTime")).getAttribute("value");
        String getLocation = driver.findElement(By.name("location")).getAttribute("value");
        String getNotes = driver.findElement(By.id("editEvent-notes")).getText();

        WebElement saveButton = driver.findElement(By.id("save"));
        click(saveButton);
        WebElement closeSuccessfulMessageButton = driver.findElement(By.className("jss57"));
        click(closeSuccessfulMessageButton);
        clickOnTheLastEvent();

        Assert.assertEquals(getStartTime, driver.findElement(By.name("startTime")).getAttribute("value"));
        Assert.assertEquals(getLocation, "myLocation");
        Assert.assertEquals(getNotes, "my event " + Generate.dateForAppointment(1));

        WebElement closeEventButton = driver.findElement(By.id("close"));
        click(closeEventButton);
    }

    private void clickOnTheLastEvent() {
        WebElement today = driver.findElements(By.className("fc-day-today")).get(2);
        List<WebElement> events = today.findElements(By.className("fc-timegrid-event-harness-inset"));
        WebElement theLastEvent = events.get(events.size() - 1);
        click(theLastEvent);
    }

    public void deleteEventAndVerifyThatTheEventIsDeleted() {
        List<WebElement> events = driver.findElements(By.className("fc-timegrid-event-harness-inset"));
        Integer eventsSize = events.size();
        WebElement theLastEvent = events.get(events.size() - 1);
        click(theLastEvent);
        deleteEvent();
        List<WebElement> eventsAfterDeleteAnEvent = driver
                .findElements(By.className("fc-timegrid-event-harness-inset"));
        Integer eventsSizeAfterDeleteAnEvent = eventsAfterDeleteAnEvent.size();

        Assert.assertEquals(eventsSizeAfterDeleteAnEvent, eventsSize - 1, "The event does not deleted ! ");
    }

    public void deleteEvent() {
        WebElement deleteButton = driver.findElement(By.xpath("//*[contains(text(), 'Delete')]"));
        click(deleteButton);

        driver.switchTo().alert().accept();

        waitFewSeconds(2000);
    }

    public void addAnEventInPastTime() {
        WebElement addButton = driver.findElement(By.id("add-events-button"));
        click(addButton);
        WebElement addEventButton = driver.findElement(By.id("add-events-particular-button"));
        click(addEventButton);
        fillTextById("automationTest", "editEvent-name");
        WebElement startTime = driver.findElement(By.name("startTime"));
        click(startTime);
        startTime.sendKeys(Keys.ARROW_DOWN);
        fillTextById("status done " + Generate.dateForAppointment(10), "editEvent-notes");
        WebElement saveButton = driver.findElement(By.id("save"));
        click(saveButton);
    }

    public void verifyEventStatus() {
        WebElement closeSuccessfulMessageButton = driver.findElement(By.className("jss57"));
        click(closeSuccessfulMessageButton);
        String eventColor = driver.findElement(By.className("fc-event-today")).getAttribute("style");
        Assert.assertEquals(eventColor, "background-color: rgb(255, 191, 131);", "event color is not correct! ");
        clickOnTheFirstEvent();
        String notes = driver.findElement(By.id("editEvent-notes")).getText();
        Assert.assertEquals(notes, "status done " + Generate.dateForAppointment(10));
        WebElement done = driver.findElement(By.id("DONE"));
        String status = done.findElement(By.className("jss62")).getAttribute("class");
        Integer length = status.length();
        if (length < 40) {
            Assert.fail("The event status should be Done! ");
        }
        deleteEvent();
    }

    private void clickOnTheFirstEvent() {
        WebElement today = driver.findElements(By.className("fc-day-today")).get(2);
        WebElement event = today.findElement(By.className("fc-timegrid-event-harness-inset"));
        click(event);
    }

    public void verifyTheTheWeeklyViewIsAsExpected() {
        Integer daysNumber = driver.findElements(By.className("fc-daygrid-day-bottom")).size();
        Assert.assertEquals(daysNumber, 7);
    }

    public void verifyTheTheDailyViewIsAsExpected() {
        WebElement dayButton = driver.findElement(By.className("fc-timeGridDay-button"));
        click(dayButton);
        Integer daysNumber = driver.findElements(By.className("fc-daygrid-day-bottom")).size();
        Assert.assertEquals(daysNumber, 1);
    }

    public void verifyTheTheMonthlyViewIsAsExpected() {
        WebElement dayButton = driver.findElement(By.className("fc-dayGridMonth-button"));
        click(dayButton);
        Integer daysNumber = driver.findElements(By.className("fc-daygrid-day-bottom")).size();
        Assert.assertEquals(daysNumber, 42);
    }

    public void sendSurveyToThePatientAndVerifyTheSurveyWasSentSuccessfully() {
        WebElement surveysButton = driver.findElements(By.className("tab")).get(2);
        click(surveysButton);
        APIsPage apIsPage = new APIsPage(driver);
        int SurveysNumberBeforeAddingSurveyByCallingAPI = apIsPage.getSurveysNumber();
        String SurveysNumberBeforeAddingSurvey = getNumberOfSurveys();
        System.out.println(SurveysNumberBeforeAddingSurvey);
        sendSurvey();
        String SurveysNumberAfterAddedSurvey = getNumberOfSurveys();
        int SurveysNumberAfterAddedSurveyByCallingAPI = apIsPage.getSurveysNumber();
        System.out.println(SurveysNumberAfterAddedSurvey);
        Assert.assertNotEquals(SurveysNumberAfterAddedSurvey, SurveysNumberBeforeAddingSurvey);
        Assert.assertEquals(SurveysNumberBeforeAddingSurveyByCallingAPI + 1, SurveysNumberAfterAddedSurveyByCallingAPI);
    }

    public void sendSurveyToThePatient() {
        WebElement surveysButton = driver.findElements(By.className("tab")).get(2);
        click(surveysButton);
        sendSurvey();
    }

    private String getNumberOfSurveys() {
        WebElement element = driver.findElements(By.className("sc-fjdhpX")).get(2);
        String element2 = element.findElements(By.tagName("span")).get(3).getText();
        String numberOfSurveys = element2.substring(element2.lastIndexOf(' ') + 1);
        return numberOfSurveys;
    }

    private void sendSurvey() {
        WebElement sendSurveyButton = driver.findElement(By.id("send-survey-button"));
        click(sendSurveyButton);
        fillTextById("Gesundheitsfragebogen final", "sendSurveyFromPatient-surveys-search-field");
        WebElement agreeCheckbox = driver.findElement(By.cssSelector("input[type='checkbox']"));
        click(agreeCheckbox);
        WebElement submitButton = driver.findElement(By.id("save"));
        click(submitButton);
    }

    // public void createPatientByTheDoctor(Patient patient) {
    //     clickOnPatientsTab();
    //     clickOnCreatePatientButton();
    //     fillMandatoryFields(patient);
    //     clickOnSaveButton();
    //     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        
    //     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("*[text()='No Thanks']")));
    //     WebElement consultationcloseButton = driver.findElement(By.xpath("*[text()='No Thanks']"));
    //     click(consultationcloseButton);

    //     //System.out.println("closed consultation..");


    //     //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='close' and @title='']")));

    //     //System.out.println("after visible element close");

    //     ////*[text()='No Thanks']

    //     ////button[@id='close' and @title='']
    //     //WebElement closeButton = driver.findElement(By.xpath("//button[@id='close' and @title=''][1]"));

    //     //System.out.println("closed the popup success");

    //     //click(closeButton);
    //     logoutFromUser();
    // }


    public void createPatientByTheDoctor(Patient patient) {
        clickOnPatientsTab();
        clickOnCreatePatientButton();
        fillMandatoryFields(patient);
        clickOnSaveButton();
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // wait.until(ExpectedConditions.elementToBeClickable(By.id("close")));
        try {
            Thread.sleep(5000);
            driver.findElement(By.xpath("//span[text()=\"No Thanks\"]")).click();
        } catch (Exception e) {
            System.out.println("Error in clicking close/No thanks button");
        }
        try {
            Thread.sleep(3000);
            WebElement closeButton = driver.findElement(By.xpath("(//button[@id=\"close\"])[2]"));
            click(closeButton);
        } catch (Exception e) {
            System.out.println("Error in clicking close/No thanks button");
        }
        
        logoutFromUser();
    }


    public void createanddeletepatcallbackfunc(Patient patient) {
        clickOnPatientsTab();
        clickOnCreatePatientButton();
        fillMandatoryFields(patient);
        clickOnSaveButton();
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // wait.until(ExpectedConditions.elementToBeClickable(By.id("close")));
        try {
            Thread.sleep(5000);
            driver.findElement(By.xpath("//span[text()=\"No Thanks\"]")).click();
        } catch (Exception e) {
            System.out.println("Error in clicking close/No thanks button");
        }
        try {
            Thread.sleep(3000);
            WebElement closeButton = driver.findElement(By.xpath("(//button[@id=\"close\"])[2]"));
            click(closeButton);
        } catch (Exception e) {
            System.out.println("Error in clicking close/No thanks button");
        }
        email_id_of_patient = patient.getEmail();
        System.out.println(email_id_of_patient);
        searchAndDeleteNonActivepatient();
    }

    public void searchAndDeleteNonActivepatient(){
        
        clickOnPatientsTab();
        try{
            WebElement searchButton = driver.findElement(By.id("patient-search-bar-search-button"));
            click(searchButton);
            fillTextById(email_id_of_patient, "patient-search-bar-search-field");
            Thread.sleep(3000);

            driver.findElement(By.xpath("(//button[@aria-label='More'])[1]")).click();
            System.out.println("Cliked on More svg menu opens");
            waitFewSeconds(3000);

            driver.findElement(By.xpath("//*[text()='Delete Patient']")).click();
            waitFewSeconds(2000);
            
            String actualMessage = driver.findElement(By.id("infopanel")).getText();
            String expectedMessage = "Patient deleted successfully";
            Assert.assertEquals(actualMessage, expectedMessage);
            System.out.println("Sucessfully Non active patient deleted!!");

        } catch(Exception e){
            System.out.println("Error in deleting non active patient!!");
        }
        
    }

    //create a child patient func, create new func fillMandatoryFieldsForChildPatient(patient)
    public void createChildPatientByTheDoctor(Patient patient) {
        clickOnPatientsTab();
        clickOnCreatePatientButton();
        fillMandatoryFieldsForChildPatient(patient);
        clickOnSaveButton();
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // wait.until(ExpectedConditions.elementToBeClickable(By.id("close")));
        try {
            Thread.sleep(5000);
            driver.findElement(By.xpath("//span[text()=\"No Thanks\"]")).click();
        } catch (Exception e) {
            System.out.println("Error in clicking close/No thanks button");
        }
        try {
            Thread.sleep(3000);
            WebElement closeButton = driver.findElement(By.xpath("(//button[@id=\"close\"])[2]"));
            click(closeButton);
        } catch (Exception e) {
            System.out.println("Error in clicking close/No thanks button");
        }
        
        logoutFromUser();
    }//end



    public void createPatientByHyCareDoctor(Patient patient) {
        clickOnPatientsTab();
        clickOnCreatePatientButton();
        fillMandatoryHyCareFields(patient);
        // chooseIcdCode();
        clickOnSaveButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@id=\"close\"])[2]")));
        WebElement closeButton = driver.findElement(By.xpath("(//button[@id=\"close\"])[2]"));
        click(closeButton);

        logoutFromUser();
    }

    public void createPatientByHyCareDoctorForMobile(Patient patient) {
        clickOnPatientsTab();
        clickOnCreatePatientButton();
        fillMandatoryHyCareFields(patient);
        chooseIcdCode();
        clickOnSaveButton();
    }

    private void chooseIcdCode() {
        WebElement createOnPEPButton = driver.findElement(By.id("createPatient-pepIntegration"));
        click(createOnPEPButton);
        WebElement sibling = driver.findElement(By.id("createPatient-disease"));
        WebElement selectDisease = sibling.findElement(By.xpath("preceding-sibling::div[1]"));
        click(selectDisease);
        WebElement icdCode = driver.findElement(By.tagName("li"));
        click(icdCode);
    }

    private void fillMandatoryHyCareFields(Patient patient) {
        fillTextById("Mrs.", "createPatient-salutation");
        setFirstName(patient.getFirstName());
        setLastName(patient.getLastName());
        setGender();
        setBirthdate(patient.getBirthDate());
        setMobileNumber(patient.getMobileNumber());
        setEmail(patient.getEmail());
        setLanguage();
    }

    private void clickOnSaveButton() {
        WebElement saveButton = driver.findElement(By.id("save"));
        click(saveButton);
        waitFewSeconds(2500);
    }

    private void clickOnCreatePatientButton() {
        WebElement dayButton = driver.findElement(By.id("createPatient-table"));
        click(dayButton);
    }

    private void fillMandatoryFields(Patient patient) {
        setFirstName(patient.getFirstName());
        setLastName(patient.getLastName());
        setGender();
        setBirthdate(patient.getBirthDate());
        setMobileNumber(patient.getMobileNumber());
        setEmail(patient.getEmail());
        setLanguage();
    }

    //modifying as a child patient
    private void fillMandatoryFieldsForChildPatient(Patient patient) {

        setChildCheckboxCheck(); //for a checkbox check
        setFirstName(patient.getFirstName());
        setLastName(patient.getLastName());
        setGender();
        setBirthdate(patient.getBirthDate());

        setChildSalutation(patient.getChildSalutation());
        setChildFirstName(patient.getChildFirstName());
        setChildLastName(patient.getChildLastName());
        setChildMobileNumber(patient.getChildMobileNumber());

        setChildEmail(patient.getChildEmail());

        setChildPatientGender();
        setChildLanguage();

        System.out.println("modify docorhomepage done.");

    }//end

    private void setChildCheckboxCheck() {
        //check the Checkbox
        WebElement checkingCheckbox = driver.findElement(By.xpath("//input[@id='createPatient-isDependent']"));
        checkingCheckbox.click();
    }

    private void setLanguage() {
        WebElement languageButton = driver.findElement(By.id("createPatient-langKey"));
        WebElement language = languageButton.findElement(By.xpath("preceding-sibling::div[1]"));
        click(language);
        WebElement deButton = driver.findElement(By.id("de"));
        click(deButton);
    }

    //for child patient
    private void setChildLanguage() {
        WebElement languageButton = driver.findElement(By.id("createPatient-langKey"));
        WebElement language = languageButton.findElement(By.xpath("preceding-sibling::div[1]"));
        click(language);
        WebElement deButton = driver.findElement(By.id("de"));
        click(deButton);
    }//end


    private void setGender() {
        WebElement genderButton = driver.findElement(By.id("createPatient-gender"));
        WebElement gender = genderButton.findElement(By.xpath("preceding-sibling::div[1]"));
        click(gender);
        WebElement femaleButton = driver.findElement(By.id("FEMALE"));
        click(femaleButton);
    }
    
    //for child
    private void setChildPatientGender() {
        WebElement genderButton = driver.findElement(By.id("createPatient-proxyGender"));
        WebElement gender = genderButton.findElement(By.xpath("preceding-sibling::div[1]"));
        click(gender);
        WebElement femaleButton = driver.findElement(By.id("FEMALE"));
        click(femaleButton);
    }


    private void setBirthdate(String birthdate) {
        fillTextById(birthdate, "createPatient-birthdate");
    }

    private void setMobileNumber(String mobileNumber) {
        fillTextById(mobileNumber, "createPatient-mobileNumber");
    }

    private void setEmail(String email) {
        fillTextById(email, "createPatient-email");
    }

    private void setLastName(String lastName) {
        fillTextByName(lastName, "lastName");
    }

    private void setFirstName(String firstName) {
        fillTextByName(firstName, "firstName");
    }

    //set the proxyfirstname
    private void setChildFirstName(String firstChildName) {
        fillTextByNameForChild(firstChildName, "proxyFirstName");
    }

    //set the proxyfirstname
    private void setChildLastName(String lastChildName) {
        fillTextByNameForChild(lastChildName, "proxyLastName");
    }

    //set the proxysalutation
    private void setChildSalutation(String lastChildSal) {
        //fillTextByNameForChild(lastChildSal, "");
        fillTextById(lastChildSal, "createPatient-proxySalutation");
    }

    //set the mobile no
    private void setChildMobileNumber(String childMobbNo) {
        fillTextByNameForChild(childMobbNo, "proxyMobileNumber");
    }//end


    //set the child email
    private void setChildEmail(String childemailis) {
        fillTextByNameForChild(childemailis, "proxyEmail");
    }//end


    public void sendMessageToPatient() {
        fillTextById(Generate.todayDate(), "chat-input");
        WebElement sendButton = driver.findElement(By.id("send-button"));
        click(sendButton);
    }

    public void sendVasSurveyToThePatient() {
        WebElement surveysButton = driver.findElements(By.className("tab")).get(2);
        click(surveysButton);
        WebElement sendSurveyButton = driver.findElement(By.id("send-survey-button"));
        click(sendSurveyButton);
        fillTextById("vas 12 ", "sendSurveyFromPatient-surveys-search-field");
        waitFewSeconds(2000);
        WebElement agreeCheckbox = driver.findElement(By.cssSelector("input[type='checkbox']"));
        click(agreeCheckbox);
        WebElement submitButton = driver.findElement(By.id("save"));
        click(submitButton);
    }

    public void respectiveScreenForDoctor() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
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

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=\"Catalog\"])")));
        driver.findElement(By.xpath("(//span[text()=\"Catalog\"])")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4//span[text()=\"Catalog\"]")));
        assert driver.findElement(By.xpath("//h4//span[text()=\"Catalog\"]")).isDisplayed();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=\"Home\"])[1]")));
        driver.findElement(By.xpath("(//span[text()=\"Home\"])[1]")).click();
        System.out.println("Checked Catalog Page");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=\"Surveys\"])")));
        driver.findElement(By.xpath("(//span[text()=\"Surveys\"])")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4//span[text()=\"Surveys\"]")));
        assert driver.findElement(By.xpath("//h4//span[text()=\"Surveys\"]")).isDisplayed();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=\"Home\"])[1]")));
        driver.findElement(By.xpath("(//span[text()=\"Home\"])[1]")).click();
        System.out.println("Checked Surveys Page");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=\"Profile\"])")));
        driver.findElement(By.xpath("(//span[text()=\"Profile\"])")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4//span[contains(text(),\"Welcome\")]")));
        assert driver.findElement(By.xpath("//h4//span[contains(text(),\"Welcome\")]")).isDisplayed();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=\"Home\"])[1]")));
        driver.findElement(By.xpath("(//span[text()=\"Home\"])[1]")).click();
        System.out.println("Checked Profile Page");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=\"Team\"])")));
        driver.findElement(By.xpath("(//span[text()=\"Team\"])")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4//span[text()=\"Team\"]")));
        assert driver.findElement(By.xpath("//h4//span[text()=\"Team\"]")).isDisplayed();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=\"Home\"])[1]")));
        driver.findElement(By.xpath("(//span[text()=\"Home\"])[1]")).click();
        System.out.println("Checked Team Page");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=\"Studies\"])")));
        driver.findElement(By.xpath("(//span[text()=\"Studies\"])")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4//span[text()=\"Studies\"]")));
        assert driver.findElement(By.xpath("//h4//span[text()=\"Studies\"]")).isDisplayed();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()=\"Home\"])[1]")));
        driver.findElement(By.xpath("(//span[text()=\"Home\"])[1]")).click();
        System.out.println("Checked Studies Page");

        System.out.println("Tapping on each feature should navigate to respective screens.");

    }

    public void verifyDoctorHomeScreenBoxes() {

        try{
            boolean patientbox = driver.findElement(By.xpath("//*[text()='people']")).isDisplayed();
            System.out.println("Is 'patientbox' element displayed? " + patientbox);

            boolean messagesbox = driver.findElement(By.xpath("//*[text()='chat']")).isDisplayed();
            System.out.println("Is 'messagesbox' element displayed? " + messagesbox);

            boolean Catalogbox = driver.findElement(By.xpath("//*[text()='library_books']")).isDisplayed();
            System.out.println("Is 'Catalogbox' element displayed? " + Catalogbox);

            boolean Surveysbox = driver.findElement(By.xpath("//*[text()='show_chart']")).isDisplayed();
            System.out.println("Is 'Surveysbox' element displayed? " + Surveysbox);

            boolean Profilebox = driver.findElement(By.xpath("//*[text()='account_circle']")).isDisplayed();
            System.out.println("Is 'Profilebox' element displayed? " + Profilebox);

            boolean Teambox = driver.findElement(By.xpath("//*[text()='group_work_item']")).isDisplayed();
            System.out.println("Is 'Teambox' element displayed? " + Teambox);

            boolean Studies = driver.findElement(By.xpath("//*[text()='assignment']")).isDisplayed();
            System.out.println("Is 'Studies' element displayed? " + Studies);

            boolean Billing = driver.findElement(By.xpath("//*[text()='cloud_download']")).isDisplayed();
            System.out.println("Is 'Billing' element displayed? " + Billing);
        
        } catch (Exception e) {

            Assert.fail("Issue on doctor homepage checking the content of boxes..");
        }
    }

    public void verifyDoctorChangeProfilePictureLanguage() {

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
            upload_file.sendKeys("D:/Yogesh/doctor-picture.jpg");

            System.out.println("after profile upload");

            //save profile picture jss62
            WebElement save_profile = driver.findElement(By.xpath("//span[text()=\"Save\"]"));
            save_profile.click();

            System.out.println("Profile SAVED Successfully");
            
            Thread.sleep(8000);


        } catch(Exception e){

            Assert.fail("Issue in doctor changing profile picture or language change..");
        }
    }

    /*public void rememberMeFunWithHomeScreenUrlVerify() {

        try{
            System.out.println("called doctorhomepage..");
            //verify homescreen url
            String ActualUrl = driver.getCurrentUrl();
            String ExpectedUrl = "https://qa.dev.docdok.ch/private/app";
            Assert.assertEquals(ExpectedUrl, ActualUrl);

            System.out.println("HomeScreen URL is Match");
        
        } catch(Exception e){

            Assert.fail("Error in url..");
        }
    }*/

    public void verifyThatTheUserLoggedInSuccessfully() {
        agreeTermsAndConditions();
        System.out.println("into doctor homepage");
        waitFewSeconds(4000);

        try {
            driver.findElement(By.className("chat-content"));
            driver.findElement(By.id("chat-input"));
            driver.findElement(By.className("events"));
        } catch (Exception e) {
            Assert.fail("There is an issue in the patient home page, please check! ");
        }
    }

    // public void activateTheUserBySettingNewPassword(Doctor doctor) {
    //     setCodeReceivedToTheNewUser(doctor.getSmsCode());
    //     setNewPassword(doctor.getPassword(), doctor.getConfirmPassword());
    // }

    public void verifyThatTheDocAssistLoggedInSuccessfully() {
        //agreeTermsAndConditions();
        System.out.println("WELCOME TO Doc Assistant HOMEPAGE");
        waitFewSeconds(5000);

        try {
            // driver.findElement(By.xpath("//*[text()='assignment']"));
            driver.findElement(By.xpath("//*[text()='group_work_item']"));
        } catch (Exception e) {
            Assert.fail("There is an issue in the doctor home page, please check! ");
        }
    }

    public void verifyThatThePhysicianDoctorLoggedInSuccessfully() {
        //agreeTermsAndConditions();
        System.out.println("WELCOME TO PHYSSICIAN HOMEPAGE");
        waitFewSeconds(5000);

        try {
            driver.findElement(By.xpath("//*[text()='assignment']"));
            System.out.println("Physician verification is successfull");
        } catch (Exception e) {
            Assert.fail("There is an issue in the physician doctor home page, please check! ");
        }
    }


    public void sendAppInvitationToPatientByDoctor(Patient patient) {
        clickOnPatientsTab();
        sendInvitationToPatient();
        // logoutFromUser();
    }

    private void sendInvitationToPatient() {

        //for pagination click
        /*driver.findElement(By.xpath("//*[text()='App Activated']")).click();
        waitFewSeconds(2000);

        //click on more svg
        driver.findElement(By.xpath("(//button[@aria-label='More'])[1]")).click();
        waitFewSeconds(1000);

        driver.findElement(By.xpath("//*[text()='Renew invitation']")).click();
        waitFewSeconds(2000);

        try{

            //verify msg: Invitation Email was sent again.
            WebElement ele = driver.findElement(By.xpath("//*[text()='Invitation Email was sent again.']"));
            String ActualTitle = ele.getText();
            String ExpectedTitle = "Invitation Email was sent again.";
            Assert.assertEquals(ExpectedTitle, ActualTitle);
            System.out.println("Message is successfully verified");

            // waitFewSeconds(5000);

            // driver.findElement(By.xpath("(//span[text()='Patients'])[3]")).click();
            // driver.findElement(By.xpath("//span[text()='App Activated']")).click();
            // waitFewSeconds(10000);

        }catch(Exception e){

            Assert.fail("Titles do not match");

        }*/


        driver.findElement(By.xpath("//*[text()='App Activated']")).click();

        System.out.println("Clicked on App activated filter");

        waitFewSeconds(6000);

        try{
            //find and store activatedstatus
            WebElement chkingActivatedStatus = driver.findElement(By.xpath("//*[contains(@d,'M9 16.2L4.8 12l-1.4 1.4L9 19 21 7l-1.4-1.4L9 16.2z')]"));

            //check for ACTIVATED STATUS OR NOT[true sign]
            if(chkingActivatedStatus.isDisplayed()){
                // Assert.fail("Activated Status find..So not Send App Invitation to PATIENT.");
                System.out.println("Activated Status found. Not sending App Invitation to PATIENT.");
            }
        }
        catch(NoSuchElementException e){

            System.out.println("Element not found So in catch block executes..: " + e.getMessage());

            System.out.println("Verified that this page has not any ACTIVATED STATUS for a paient");

            System.out.println("Into catch block");

            //click on more svg
            driver.findElement(By.xpath("(//button[@aria-label='More'])[1]")).click();
            System.out.println("Cliked on More svg menu opens");

            waitFewSeconds(3000);

            driver.findElement(By.xpath("//*[text()='Renew invitation']")).click();
            waitFewSeconds(2000);

            System.out.println("Cliked on renew invitation btn..");

            try{
                System.out.println("In try block");

                waitFewSeconds(4000);
                //verify msg: Invitation Email was sent again.
                WebElement ele = driver.findElement(By.xpath("//*[text()='Invitation Email was sent again.']"));
                String ActualTitle = ele.getText();
                String ExpectedTitle = "Invitation Email was sent again.";
                waitFewSeconds(2000);
                Assert.assertEquals(ExpectedTitle, ActualTitle);
                System.out.println("Invitation App Message is successfully verified");

                // waitFewSeconds(5000);

                // driver.findElement(By.xpath("(//span[text()='Patients'])[3]")).click();
                // driver.findElement(By.xpath("//span[text()='App Activated']")).click();
                // waitFewSeconds(10000);

            }catch(Exception exe){

                System.out.println("Into catch block");
                Assert.fail("Titles do not match");

            }

        }

    }


    public void UserProfileEditAndCancelEdit(Patient patient) {
        clickOnPatientsTab();
        selectPrimaryUserForProfileEdit();
        EditPrimaryCancelProfile();
        EditContactDetailsCancelProfile();
    }

    public void selectPrimaryUserForProfileEdit(){

        WebElement searchButton = driver.findElement(By.id("patient-search-bar-search-button"));
        click(searchButton);
        fillTextById("692b5_1710230305@saluta.ch", "patient-search-bar-search-field");
        waitFewSeconds(5000);
        WebElement myPatients = driver.findElement(By.id("myPatients"));
        WebElement myPatient = myPatients.findElement(By.xpath(("//a[contains(@href, '/private/app/patients/PAT')]")));
        click(myPatient);

        System.out.println("Clickd on patient");
        // String ranNo = DataProviderClass.getRandomMobileNumber();
        // System.out.println("//////----////");
        // System.out.println(ranNo);
        // System.out.println("//////----////");

        //click on shoe more arrow btn
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(@aria-label,'Show more')])[1]"))).click();
        waitFewSeconds(4000);
    }

    public void EditPrimaryCancelProfile(){

        WebElement contactEditpath = driver.findElement(By.xpath("(//button[contains(@class, 'jss62') and @tabindex='0'])[10]"));
        click(contactEditpath); 
        System.out.println("Edit btn clicked");

        //used to scroll up, use because element not seen and clickable
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, -500);");
        //end

		//for primary data
        //adding paths for a edit salutation:
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement primaryEditSalutation = driver.findElement(By.xpath("//input[@id='patientData-salutation']"));
        wait.until(ExpectedConditions.elementToBeClickable(primaryEditSalutation));

        primaryEditSalutation.click();

        waitFewSeconds(2000);

        //to clear the existing code in field
        int lengthSlautation = primaryEditSalutation.getAttribute("value").length();
        for (int i = 0; i < lengthSlautation; i++) {
            primaryEditSalutation.sendKeys(Keys.BACK_SPACE);
        }//end

        primaryEditSalutation.sendKeys("Patient");
        System.out.println("salutation done");
		
        //adding paths for a edit firstname:
        WebElement primaryEditfirstname = driver.findElement(By.xpath("//input[@name='firstName']"));
        primaryEditfirstname.click();
        waitFewSeconds(1000);
        primaryEditfirstname.clear();
        primaryEditfirstname.sendKeys("YS");
        waitFewSeconds(1000);
        System.out.println("firstname done");
		
        //adding paths for a edit lastname:
        WebElement primaryEditLastName = driver.findElement(By.xpath("//input[@name='lastName']"));
        primaryEditLastName.click();
        waitFewSeconds(1000);
        primaryEditLastName.clear();
        primaryEditLastName.sendKeys("Updated Last Name up");
        waitFewSeconds(1000);
        System.out.println("lastname done");
		
        //adding paths for a edit birthdate:
        WebElement primaryEditBirthDate = driver.findElement(By.xpath("//input[@id='patientData-birthdate']"));
        primaryEditBirthDate.click();
        waitFewSeconds(1000);
        primaryEditBirthDate.clear();
        primaryEditBirthDate.sendKeys("20/01/1998");
        waitFewSeconds(2000);
        System.out.println("birthday done");

        JavascriptExecutor jsdown = (JavascriptExecutor) driver;
        jsdown.executeScript("window.scrollBy(0, 200);");

        WebElement clickGenderDropdown = driver.findElement(By.xpath("//div[@aria-pressed='false' and @role='button']"));
        waitFewSeconds(2000);
        clickGenderDropdown.click();
        waitFewSeconds(2000);
        clickGenderDropdown.click();
        System.out.println("clicked dropdown");
        waitFewSeconds(2000);
        WebElement current_selected_gender_element = driver.findElement(By.xpath("//input[@name='gender']"));
        String genderVal = current_selected_gender_element.getAttribute("value");
        System.out.println("gender value: " + genderVal);

        String genderxpath;
        if ("MALE".equals(genderVal)) {
            genderxpath = "//span[text()='Female']";
            System.out.println("first..");
        } else if("FEMALE".equals(genderVal)) {
            genderxpath = "//span[text()='Male']";
            System.out.println("second");
        } else{
            genderxpath = "//span[text()='Male']";
            System.out.println("third");
        }

        System.out.println("After update val:" + genderxpath);

        WebElement selectDropdownOption = driver.findElement(By.xpath(genderxpath));
        waitFewSeconds(4000);
        System.out.println("selected option:" +selectDropdownOption);
        selectDropdownOption.click();

        System.out.println("clicked option");

        //adding paths for a edit socialsecurity no/insurance no:
        WebElement primaryEditSocialSecurityNo = driver.findElement(By.xpath("//input[@name='insuranceNumber']"));
        primaryEditSocialSecurityNo.click();
        waitFewSeconds(1000);
        primaryEditSocialSecurityNo.clear();
        primaryEditSocialSecurityNo.sendKeys("333");
        waitFewSeconds(1000);
        System.out.println("insurance no done");
		
        //adding paths for a edit patient no:
        WebElement primaryEditPatientNo = driver.findElement(By.xpath("//input[@name='lifelongId']"));
        primaryEditPatientNo.click();
        waitFewSeconds(1000);
        primaryEditPatientNo.clear();
        primaryEditPatientNo.sendKeys("777");
        waitFewSeconds(1000);
        System.out.println("patient num done");

        WebElement contactFieldSave = driver.findElement(By.xpath("//button[@id='save']"));
        click(contactFieldSave);
        System.out.println("Successfully saved primary fields..");

        waitFewSeconds(4000);

        //cancel field process for primary fields
        WebElement contactEditpathAgain = driver.findElement(By.xpath("(//button[contains(@class, 'jss62') and @tabindex='0'])[10]"));
        click(contactEditpathAgain); 

        waitFewSeconds(1000);

        WebElement primaryEditfirstnameAgain = driver.findElement(By.xpath("//input[@name='firstName']"));
        // primaryEditfirstnameAgain.click();
        click(primaryEditfirstnameAgain);
        waitFewSeconds(1000);
        primaryEditfirstnameAgain.clear();
        primaryEditfirstnameAgain.sendKeys("AGAIN Updated First Name");
        waitFewSeconds(1000);
        
        WebElement cancelBtnpath = driver.findElement(By.xpath("//span[text()='Cancel']"));
        waitFewSeconds(3000);
        cancelBtnpath.click();

        System.out.println("Cancel btn click for primary fields success");

        waitFewSeconds(6000);

    }


    //working properly for edit/cancel contactDetail user profile
    public void EditContactDetailsCancelProfile(){

        //edit and save process start
        //used to scroll up, use because element not seen and clickable
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, -600);");
        //end

        WebElement contactDetailsEditpath = driver.findElement(By.xpath("(//button[contains(@class, 'jss62') and @tabindex='0'])[11]"));

        // waitFewSeconds(2000);

        click(contactDetailsEditpath); 

        System.out.println("Edit btn clicked");

        //adding paths for a edit email:
        // WebElement contactEditEmail = driver.findElement(By.xpath("//input[@name='email']"));
        
        // contactEditEmail.click();
        // waitFewSeconds(1000);

        // contactEditEmail.clear();

        // // contactEditEmail.sendKeys(DataProviderClass.getRandomEmailForSaluta());
        // String updateMail = DataProviderClass.getRandomEmailForSaluta();
        // contactEditEmail.sendKeys(updateMail);

        // System.out.println("Updated Patient Mail is:" + updateMail);

        // waitFewSeconds(1000);
        
        //adding paths for a edit mobno:
        WebElement contactEditMobileno = driver.findElement(By.xpath("//input[@name='mobileNumber']"));
        
        contactEditMobileno.click();
        waitFewSeconds(1000);

        contactEditMobileno.clear();
        
        // update dynamic mob here
        contactEditMobileno.sendKeys(DataProviderClass.randomNumberGetWithPlusFormat());
        waitFewSeconds(1000);

        System.out.println("mobileno updated");

        waitFewSeconds(1000);

        
        WebElement contactFieldSave = driver.findElement(By.xpath("//button[@id='save']"));
        click(contactFieldSave);

        waitFewSeconds(6000);

        System.out.println("Saved successfully");

        //used to scroll up, use because element not seen and clickable
        JavascriptExecutor jsupnew = (JavascriptExecutor) driver;
        jsupnew.executeScript("window.scrollBy(0, -500);");
        //end

        System.out.println("Again scroll up");

        waitFewSeconds(6000);
        //end edit and save profile process


        //for cancel process start
        //edit btn click
        WebElement contactDetailsEditpathTwo = driver.findElement(By.xpath("(//button[contains(@class, 'jss62') and @tabindex='0'])[11]"));
        
        System.out.println("Secondtime find xath of edit");
        
        click(contactDetailsEditpathTwo); 

        System.out.println("for Cancel process Edit btn clicked");

        WebElement contactEditEmailTwo = driver.findElement(By.xpath("//input[@name='email']"));

        contactEditEmailTwo.click();
        waitFewSeconds(1000);

        contactEditEmailTwo.clear();

        contactEditEmailTwo.sendKeys("updatedemailThreeNewCancel@gmail.com");
        waitFewSeconds(2000);

        System.out.println("Cancel email field updated");

        //target cancel btn click
        //end cancel process

        WebElement cancelBtnpath = driver.findElement(By.xpath("//span[text()='Cancel']"));
        waitFewSeconds(3000);
        cancelBtnpath.click();

        System.out.println("Cancel btn click success");

        waitFewSeconds(4000);

    }


    public void checkAdminNotSendAppInvitationToAppActivePatient(Patient patient) {
        clickOnPatientsTab();
        searchPatientAndAppActivatedOrNotCheck();
    }

    public void searchPatientAndAppActivatedOrNotCheck(){

        WebElement searchButton = driver.findElement(By.id("patient-search-bar-search-button"));
        waitFewSeconds(2000);
        click(searchButton);
        fillTextById("as@as.com", "patient-search-bar-search-field");
        waitFewSeconds(7000);

        //find and store activatedstatus
        WebElement chkingActivatedStatus = driver.findElement(By.xpath("//*[contains(@d,'M9 16.2L4.8 12l-1.4 1.4L9 19 21 7l-1.4-1.4L9 16.2z')]"));

        if(chkingActivatedStatus.isDisplayed()){
            driver.findElement(By.xpath("(//button[@aria-label='More'])[1]")).click();
            waitFewSeconds(3000);
            WebElement renewInvitationElement = driver.findElement(By.xpath("//li[@data-test='renewInvitation-action']"));
            String tabIndexAttribute = renewInvitationElement.getAttribute("tabindex");
            boolean isDisabled = tabIndexAttribute != null && tabIndexAttribute.equals("-1");
            if (isDisabled) {
                System.out.println("Passed: App is Activated, Renew invitation element is disabled.");
            } else {
                Assert.fail("App is Not activated, Renew invitation element is Not disabled!!");
            }
        }
        else{
            Assert.fail("App is Not activated, Renew invitation element is Not disabled!!");
        }
    }
}
