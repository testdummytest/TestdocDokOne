package UiRegressionTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SalutaBaseTest {
    public WebDriver driver ;

    @BeforeMethod
    public void setup() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--verbose");
        options.addArguments("--window-size=1280,800");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://qa.dev.docdok.ch/salutacoach");
        Thread.sleep(1000);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
