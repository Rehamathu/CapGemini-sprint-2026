
package Campaigns.smoke;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ObjRepo.CampaignsPage;
import baseclass.Baseclass;

public class CampaignNavigationTest extends Baseclass {

    @Test(groups = {"Functional", "Positive","Smoke"})
    public void verifyNavigationToCampaigns() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        // Step 1: Navigate to Campaigns
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        // Step 2: Wait for the Filters dropdown
        By filtersDropdown = By.id("viewname");

        WebElement filtersElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(filtersDropdown));

        // Step 3: Verify that the Campaigns page is displayed
        Assert.assertTrue(
                filtersElement.isDisplayed(),
                "Navigation failed: Campaigns Filters dropdown is not displayed.");

        System.out.println(
                "CampaignNavigationTest passed: Campaigns page opened successfully.");

        Thread.sleep(2000);
    }
}
