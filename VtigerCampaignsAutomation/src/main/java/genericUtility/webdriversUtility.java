
package genericUtility;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class webdriversUtility {

    public void waitForPageLoad(WebDriver driver) {
        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(20));
    }

    public void waitForElementVisible(
            WebDriver driver, WebElement element) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementClickable(
            WebDriver driver, WebElement element) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementPresent(
            WebDriver driver, By locator) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void selectByVisibleText(
            WebElement element, String text) {

        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void selectByValue(
            WebElement element, String value) {

        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void selectByIndex(
            WebElement element, int index) {

        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public Alert waitForAlert(WebDriver driver) {
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        waitForAlert(driver).accept();
    }

    public void dismissAlert(WebDriver driver) {
        waitForAlert(driver).dismiss();
    }

    public void switchToFrame(
            WebDriver driver, WebElement frameElement) {

        driver.switchTo().frame(frameElement);
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void scrollToElement(
            WebDriver driver, WebElement element) {

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView(true);", element);
    }
}

