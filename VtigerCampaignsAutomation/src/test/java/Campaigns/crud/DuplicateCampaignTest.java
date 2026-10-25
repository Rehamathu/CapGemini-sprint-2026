package Campaigns.crud;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import ObjRepo.CampaignsPage;
import baseclass.Baseclass;

public class DuplicateCampaignTest extends Baseclass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyDuplicateCampaignNamesCanBeCreated()
            throws InterruptedException {

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        String campaignName = "DuplicateCampaignTest01";

        // Open Campaigns
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        // Create the first campaign
        campaignsPage.clickCreateCampaign();
        Thread.sleep(1500);

        campaignsPage.enterCampaignName(campaignName);
        campaignsPage.selectCampaignStatus("Active");
        campaignsPage.selectCampaignType("Conference");
        campaignsPage.enterExpectedCloseDate("2026-12-31");
        Thread.sleep(1000);

        campaignsPage.clickSave();
        Thread.sleep(3000);

        // Return to Campaigns and create another campaign with the same name
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        campaignsPage.clickCreateCampaign();
        Thread.sleep(1500);

        campaignsPage.enterCampaignName(campaignName);
        campaignsPage.selectCampaignStatus("Planning");
        campaignsPage.selectCampaignType("Advertisement");
        campaignsPage.enterExpectedCloseDate("2026-12-31");
        Thread.sleep(1000);

        campaignsPage.clickSave();
        Thread.sleep(3000);

        // Search for the duplicate campaign name
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        campaignsPage.enterAdvancedSearchText(campaignName);
        campaignsPage.selectSearchField("Campaign Name");
        campaignsPage.clickSearchNow();
        Thread.sleep(3000);

        // Count matching campaign records
        int matchingCampaigns = driver.findElements(
            By.xpath("//table//a[normalize-space()='" + campaignName + "']")
        ).size();

        Assert.assertTrue(
            matchingCampaigns >= 2,
            "Expected at least two campaigns named " + campaignName
                + ", but found " + matchingCampaigns
        );

        System.out.println(
            "Duplicate campaign name test passed. Matching records: "
                + matchingCampaigns
        );

        Thread.sleep(2000);
    }
}