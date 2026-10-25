

package Leads;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseclass.Baseclass;

public class LeadsNavigationTest extends Baseclass {

    @Test(groups = {"Functional", "Positive", "Smoke"})
    public void verifyLeadsPageNavigation() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        // Step 1: Click Leads menu
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Leads"))).click();

        Thread.sleep(2000);

        // Step 2: Verify Leads page URL
        Assert.assertTrue(
                driver.getCurrentUrl().contains("module=Leads"),
                "Leads page did not open.");

        // Step 3: Verify alphabetical navigation is displayed
        Assert.assertTrue(
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("alpha_1"))).isDisplayed(),
                "Alphabetical navigation is not displayed.");

        // Step 4: Verify Leads records table is displayed
        Assert.assertTrue(
                driver.findElement(By.xpath(
                        "//table[.//text()[contains(.,'Last Name')]]"))
                        .isDisplayed(),
                "Leads records table is not displayed.");

        System.out.println(
                "LeadsNavigationTest passed: Leads page opened successfully.");

        Thread.sleep(5000);
    }
}
