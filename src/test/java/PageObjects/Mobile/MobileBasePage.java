package PageObjects.Mobile;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MobileBasePage {
    AndroidDriver androidDriver ;

    public MobileBasePage(AndroidDriver driver) {
        super();
        this.androidDriver = driver;
    }

    public void  fillTextByXpath(String text, String el) {
        WebElement element = androidDriver.findElement(AppiumBy.xpath(el));
        click(element);
        element.clear();
        element.sendKeys(text);
        waitFewSeconds(2000);
    }

    public void  fillTextByClassName(String text, String el) {
        WebElement element = androidDriver.findElement(AppiumBy.className(el));
        click(element);
        element.clear();
        element.sendKeys(text);
        waitFewSeconds(2000);
    }

    public void  fillTextById(String text, String el) {
        WebElement element = androidDriver.findElement(AppiumBy.id(el));
        click(element);
        element.clear();
        element.sendKeys(text);
        waitFewSeconds(2000);
    }

    public void  fillTextByAccessibilityId(String text, String el) {
        WebElement element = androidDriver.findElement(AppiumBy.accessibilityId(el));
        click(element);
        element.clear();
        element.sendKeys(text);
        waitFewSeconds(2000);
    }

    public void  fillTextByClassNames(String text, String el, int index) {
        WebElement element = androidDriver.findElements(AppiumBy.className(el)).get(index);
        click(element);
        element.clear();
        element.sendKeys(text);
        waitFewSeconds(2000);
    }

    public void click(WebElement el) {
        Actions actions = new Actions(androidDriver);
        actions.moveToElement(el);
        actions.perform();
        waitFewSeconds(1000);
        el.click();
        waitFewSeconds(3000);
    }

    public void waitFewSeconds(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void hideKeyboard() {
        androidDriver.hideKeyboard();
        waitFewSeconds(1000);
    }

    public void waitUntilVisibleByClassName(String element) {
        WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(element)));
    }

    public void waitUntilVisibleByXpath(String element) {
        WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
    }

    public void waitUntilVisibleByAccessibilityId(String element) {
        WebDriverWait wait = new WebDriverWait(androidDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId(element)));
    }

    public void scrollToTheBottomOfThePage() {
        TouchAction action = new TouchAction(androidDriver);
        action.press(PointOption.point(522,2027)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(522,1062)).release().perform();
    }

    public void clickByTouchAction(int x, int y) {
        TouchAction action = new TouchAction(androidDriver);
        action.press(PointOption.point(x,y)).release();
        action.perform();
    }

}
