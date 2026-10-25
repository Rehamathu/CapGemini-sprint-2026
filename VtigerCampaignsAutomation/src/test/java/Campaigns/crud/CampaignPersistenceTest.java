package Campaigns.crud;

import org.testng.Assert;
import org.testng.annotations.Test;

import ObjRepo.CampaignsPage;
import baseclass.Baseclass;

public class CampaignPersistenceTest extends Baseclass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyCampaignListRemainsAvailableAfterNavigation()
            throws InterruptedException {

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        // Open Campaigns page
        campaignsPage.openCampaigns();

        Thread.sleep(2000);

        // Refresh the Campaigns page
        driver.navigate().refresh();

        Thread.sleep(2000);

        // Verify the URL contains Campaigns
        Assert.assertTrue(
            driver.getCurrentUrl().contains("Campaigns"),
            "Campaigns list was not available after refreshing."
        );

        // Verify Campaign Name text is present in the page source
        Assert.assertTrue(
            driver.getPageSource().contains("Campaign Name"),
            "Campaign list content did not persist after refreshing."
        );

        System.out.println(
            "Campaigns list remained available after refreshing."
        );
    }
}