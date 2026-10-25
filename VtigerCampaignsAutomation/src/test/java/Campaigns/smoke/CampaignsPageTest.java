
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

public class CampaignsPageTest extends Baseclass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyCampaignsPageNavigation() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        // Step 1: Open Campaigns
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        // Step 2: Wait for the Filters dropdown
        WebElement filtersDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("viewname")));

        // Step 3: Verify the Campaigns page
        Assert.assertTrue(
                filtersDropdown.isDisplayed(),
                "Campaigns page did not open successfully.");

        System.out.println(
                "CampaignsPageTest passed: Campaigns list page opened.");

        Thread.sleep(2000);
    }

    @Test(groups = {"Functional", "Positive"})
    public void verifyCreateCampaignFormOpens() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        // Step 1: Open Campaigns
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        // Step 2: Click Create Campaign
        campaignsPage.clickCreateCampaign();
        Thread.sleep(2000);

        // Step 3: Wait for the Campaign Name field
        WebElement campaignNameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("campaignname")));

        // Step 4: Verify the Create Campaign form
        Assert.assertTrue(
                campaignNameField.isDisplayed(),
                "Create Campaign form did not open.");

        System.out.println(
                "CampaignsPageTest passed: Create Campaign form opened.");

        Thread.sleep(2000);
    }
}
