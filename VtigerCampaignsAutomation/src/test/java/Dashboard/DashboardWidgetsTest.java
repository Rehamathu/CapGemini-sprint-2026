
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

public class DashboardWidgetsTest extends Baseclass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyLeadsBySourceDashboard()
            throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        // Open Dashboard
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Dashboard"))).click();

        Thread.sleep(3000);

        // Select Leads By Source
        WebElement dashboardDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("dashboard_combo")));

        Select dashboardSelect = new Select(dashboardDropdown);

        dashboardSelect.selectByVisibleText("Leads By Source");

        Thread.sleep(5000);

        // Verify selected report
        WebElement updatedDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("dashboard_combo")));

        Assert.assertEquals(
                new Select(updatedDropdown)
                        .getFirstSelectedOption()
                        .getText().trim(),
                "Leads By Source",
                "Leads By Source was not selected.");

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

        // Verify report heading
        WebElement reportHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//*[normalize-space(text())='Leads By Source']")));

        Assert.assertTrue(
                reportHeading.isDisplayed(),
                "Leads By Source report heading is not displayed.");

        // Return to top
        js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(1000);

        System.out.println(
                "DashboardWidgetsTest passed: Leads By Source selected, "
                + "report heading verified, and page scrolled.");

    }
}
