package PageObjects;

import Entities.Admin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestingUtilPage extends BasePage {

    static String activationUrl;

    public TestingUtilPage(WebDriver driver) {
        super(driver);
    }

    public void openActivationUrlByTestingUtil(Admin admin) {
        openTestingUtilPage();
        loginAsAdminInTestingUtil(admin);
        goToTheEmailsAndClickOnTheLastEmail();
        openUrlInNewTab(getActivationUrlForTheLastEmail(), 1);
        logoutFromUser();
        openUrlInTheSameTab(activationUrl);
    }

    //for sms
    public void openActivationUrlByTestingUtil_Sms(Admin admin) {
        openTestingUtilPage();
        loginAsAdminInTestingUtil(admin);
        //goToTheEmailsAndClickOnTheLastEmail();
        goToTheSmsAndClickOnTheLastSms();

        System.out.println("Working till new func call..");

        openUrlInNewTab(getActivationUrlForTheLastSms(), 1);
        logoutFromUser();
        //openUrlInTheSameTab(activationUrl);


        
        WebElement username = driver.findElement(By.id("username"));
        //fillTextById(userName, "username");
        

    }//end sms

    public void openTestingUtilPage() {
        openUrlInTheSameTab("https://docdokutil.s3.eu-central-1.amazonaws.com/utilityTests.html");
    }

    private void loginAsAdminInTestingUtil(Admin admin) {
        WebElement envQA = driver.findElement(By.id("envQA"));
        click(envQA);
        loginAsUser(admin.getEmail(), admin.getPassword());
    }

    private void goToTheEmailsAndClickOnTheLastEmail() {
        WebElement getEmails = driver.findElement(By.xpath("//*[contains(text(), 'get\n" +
                "\t\t\t\temails')]"));
        click(getEmails);
        WebElement emails = driver.findElement(By.id("emails"));
        Integer emailsSize = emails.findElements(By.tagName("li")).size();
        WebElement lastEmail = emails.findElements(By.tagName("li")).get(emailsSize - 1);
        click(lastEmail);
    }

    //start get sms fun create
    private void goToTheSmsAndClickOnTheLastSms() {

        System.out.println("called new fun..");

        // WebElement getEmails = driver.findElement(By.xpath("//*[contains(text(), 'get\n" +
        //         "\t\t\t\temails')]"));
        // click(getEmails);

        WebElement getSms = driver.findElement(By.xpath("//*[contains(text(),'get SMS')]"));
        click(getSms);

        System.out.println("clicked on sms");

        // WebElement emails = driver.findElement(By.id("emails"));
        // Integer emailsSize = emails.findElements(By.tagName("li")).size();
        // WebElement lastEmail = emails.findElements(By.tagName("li")).get(emailsSize - 1);
        // click(lastEmail);

        WebElement sms = driver.findElement(By.id("emails"));

        System.out.println("element sms find");

        Integer smsSize = sms.findElements(By.tagName("li")).size();

        System.out.println("smsSize find 1");

        WebElement lastSms = sms.findElements(By.tagName("li")).get(smsSize - 1);

        
        System.out.println("smsSize find 2");

        click(lastSms);

        System.out.println("clicked on this");


    }
    //end sms fun


    private String getActivationUrlForTheLastEmail() {
        WebElement emails = driver.findElement(By.id("emails"));
        Integer emailsSize = emails.findElements(By.tagName("li")).size();
        WebElement lastEmail = emails.findElements(By.tagName("li")).get(emailsSize - 1);
        WebElement iframe = lastEmail.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);
        activationUrl = driver.findElement(By.xpath("//a[contains(@href, 'https://qa.dev.docdok.ch/rest/user/api/users/onboarding/?token')]")).getAttribute("href");
        return activationUrl;
    }

    //new created related sms
    private String getActivationUrlForTheLastSms() {
        WebElement sms = driver.findElement(By.xpath("//div//button[text()= \"get SMS\"]"));
        /*Integer smsSize = sms.findElements(By.tagName("li")).size();
        WebElement lastsms = sms.findElements(By.tagName("li")).get(smsSize - 1);
        WebElement iframe = lastsms.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);*/
        activationUrl = "https://qa.dev.docdok.ch/rest/user/api/users/onboarding/?token";
        return activationUrl;
    }//end

}
