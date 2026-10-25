
package Dashboard;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseclass.Baseclass;

public class DashboardNavigationTest extends Baseclass {

    @Test(groups = {"Functional", "Positive","Smoke"})
    public void verifyDashboardNavigation() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        // Open dashboard
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Dashboard"))).click();

        Thread.sleep(3000);

        Assert.assertTrue(
                driver.getCurrentUrl().contains("module=Dashboard"),
                "Dashboard page did not open.");

        // Verify Dashboard dropdown
        WebElement dashboardDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("dashboard_combo")));

        Assert.assertTrue(
                dashboardDropdown.isDisplayed(),
                "Dashboard dropdown is not displayed.");

        Select dashboardSelect = new Select(dashboardDropdown);

        Assert.assertEquals(
                dashboardSelect.getFirstSelectedOption()
                        .getText().trim(),
                "Dashboard Home",
                "Dashboard Home is not selected.");

        // Scroll through the entire Dashboard page
        JavascriptExecutor js = (JavascriptExecutor) driver;

        long pageHeight = (long) js.executeScript(
                "return document.body.scrollHeight");

        for (long position = 0; position < pageHeight; position += 500) {
            js.executeScript("window.scrollBy(0, 500);");
            Thread.sleep(500);
        }

        js.executeScript(
                "window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);

        // Return to top
        js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(1000);

        System.out.println(
                "DashboardNavigationTest passed: Dashboard opened, "
                + "Dashboard Home verified, and page scrolled.");

    }
}
