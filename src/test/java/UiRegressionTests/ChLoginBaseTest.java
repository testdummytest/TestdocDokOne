package UiRegressionTests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChLoginBaseTest {
    public WebDriver driver;
    public AndroidDriver androidDriver;

    @BeforeMethod
    public void setup() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--verbose");
        options.addArguments("--window-size=1280,800");
        options.addArguments("webdriver.chrome.whitelistedIps= ");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://qa.dev.docdok.ch");
        Thread.sleep(1000);
    }

    // @AfterMethod
    // public void tearDown() {
    // driver.quit();

    // }
    @AfterMethod
    public void tearDown(ITestResult result) {
        String methodName = result.getName().toString().trim();
        // Take screenshot
        if (result.getStatus() == ITestResult.FAILURE)
            takeScreenshot(methodName);

        // Quit the WebDriver
        if (driver != null) {
            driver.quit();
        }

    }

    private void takeScreenshot(String methodNametestName) {
        // Take screenshot and save it to a file
        if (driver instanceof TakesScreenshot) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                // Create a directory to store screenshots if it doesn't exist
                String directoryPath = System.getProperty("user.dir") + "/Screenshot/";
                Path directory = Paths.get(directoryPath);
                if (!Files.exists(directory)) {
                    Files.createDirectories(directory);
                }

                // Define the screenshot file name with timestamp
                String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
                String screenshotFileName = methodNametestName + timestamp + ".png";

                // Save the screenshot to the specified path
                Path screenshotPath = Paths.get(directoryPath + screenshotFileName);
                Files.copy(screenshotFile.toPath(), screenshotPath);
                System.out.println("Screenshot saved: " + screenshotPath.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Driver does not support taking screenshots");
        }
    }

    @AfterMethod
    public void countTestCasesForUi() throws IOException {
        File rootDirectory = new File("src/test/java/UiRegressionTests/WebTests");
        int testCount = countTestAnnotations(rootDirectory);
        System.out.println("Number of @Test annotations in UiRegressionTests/WebTests : " + testCount);
    }

    @AfterMethod
    public void countTestCasesForUiMobileTests() throws IOException {
        File rootDirectory = new File("src/test/java/UiRegressionTests/MobileTests");
        int testCount = countTestAnnotations(rootDirectory);
        System.out.println("Number of @Test annotations in UiRegressionTests/MobileTests : " + testCount);
    }

    @AfterMethod
    public void countTestCasesForUiHybridTests() throws IOException {
        File rootDirectory = new File("src/test/java/UiRegressionTests/HybridTests");
        int testCount = countTestAnnotations(rootDirectory);
        System.out.println("Number of @Test annotations in UiRegressionTests/HybridTests : " + testCount);
    }

    int countTestAnnotations(File directory) throws IOException {
        int count = 0;
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    count += countTestAnnotations(file); // Recursively count annotations in subdirectories
                } else if (file.getName().endsWith(".java")) {
                    count += Files.lines(file.toPath())
                            .filter(line -> line.contains("@Test"))
                            .count();
                }
            }
        }
        return count;
    }
    
}
