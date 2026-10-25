
package Campaigns.crud;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import ObjRepo.CampaignsPage;
import baseclass.Baseclass;

public class CreateCampaignTest extends Baseclass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyCreateCampaignWithValidData() throws InterruptedException {

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        // Open Campaigns
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        // Open Create Campaign form
        campaignsPage.clickCreateCampaign();
        Thread.sleep(2000);

        // Enter valid campaign details
        String campaignName = "AutomationCampaign01";

        campaignsPage.enterCampaignName(campaignName);
        campaignsPage.selectCampaignStatus("Active");
        campaignsPage.selectCampaignType("Conference");
        campaignsPage.enterExpectedCloseDate("2026-12-31");

        Thread.sleep(1000);

        // Save campaign
        campaignsPage.clickSave();
        Thread.sleep(3000);

        // Navigate back to Campaigns list and search for the created campaign
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        campaignsPage.enterAdvancedSearchText(campaignName);
        campaignsPage.selectSearchField("Campaign Name");
        campaignsPage.clickSearchNow();
        Thread.sleep(2000);

        // Verify the campaign record is actually displayed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        boolean campaignFound = !driver.findElements(
            By.xpath("//table//a[normalize-space()='" + campaignName + "']")
        ).isEmpty();

        Assert.assertTrue(
            campaignFound,
            "Campaign was not found in the Campaigns list after saving: " + campaignName
        );

        System.out.println("Campaign created and found in list: " + campaignName);

        Thread.sleep(2000);
    }
}