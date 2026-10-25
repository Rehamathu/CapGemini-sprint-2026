
package Campaigns.crud;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ObjRepo.CampaignsPage;
import baseclass.Baseclass;

public class ViewCampaignTest extends Baseclass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyCampaignsListIsDisplayed() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        // Step 1: Open the Campaigns module
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        // Step 2: Verify that the Campaigns page opened
        Assert.assertTrue(
                driver.getCurrentUrl().contains("Campaigns"),
                "Campaigns list page did not open."
        );

        // Step 3: Wait for the Campaign Name heading to become visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[normalize-space()='Campaign Name']")
        ));

        Assert.assertTrue(
                driver.getPageSource().contains("Campaign Name"),
                "Campaign Name column was not displayed."
        );

        // Step 4: Verify that the campaign list table is present
        boolean campaignTableDisplayed =
                !driver.findElements(By.xpath("//table[.//a[normalize-space()='edit']]"))
                       .isEmpty();

        Assert.assertTrue(
                campaignTableDisplayed,
                "Campaign list table was not found, or it contains no matching edit links."
        );

        Thread.sleep(2000);

        System.out.println("Campaigns list page opened successfully.");
        System.out.println("Campaign Name heading is present.");
        System.out.println("Campaign list table with an edit link was found.");
    }
}
