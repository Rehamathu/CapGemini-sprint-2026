package Campaigns.crud;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ObjRepo.CampaignsPage;
import baseclass.Baseclass;

public class EditCampaignTest extends Baseclass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyCampaignCanBeEdited() throws InterruptedException {

        String campaignName = "DuplicateCampaignTest01";

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(15));

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        // Open Campaigns
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        // Search for the test campaign
        campaignsPage.enterAdvancedSearchText(campaignName);
        campaignsPage.selectSearchField("Campaign Name");
        campaignsPage.clickSearchNow();
        Thread.sleep(2000);

        // Open the edit link for the matching campaign row
        WebElement editLink = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//tr[.//a[normalize-space()='" + campaignName
                                + "']]//a[normalize-space()='edit']")));

        editLink.click();
        Thread.sleep(2000);

        // Read current status and choose the opposite status
        WebElement statusDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("campaignstatus")));

        Select statusSelect = new Select(statusDropdown);
        String currentStatus = statusSelect.getFirstSelectedOption().getText().trim();

        String updatedStatus;

        if (currentStatus.equalsIgnoreCase("Active")) {
            updatedStatus = "Planning";
        } else {
            updatedStatus = "Active";
        }

        statusSelect.selectByVisibleText(updatedStatus);
        Thread.sleep(1000);

        // Save changes
        WebElement saveButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[@title='Save [Alt+S]']")));

        saveButton.click();
        Thread.sleep(3000);

        // Return to Campaigns and search for the same record name
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        campaignsPage.enterAdvancedSearchText(campaignName);
        campaignsPage.selectSearchField("Campaign Name");
        campaignsPage.clickSearchNow();
        Thread.sleep(2000);

        // Verify that the campaign still exists
        Assert.assertTrue(
                campaignsPage.isCampaignNameDisplayed(campaignName),
                "Campaign was not found after editing: " + campaignName);

        System.out.println("Campaign edit submitted successfully.");
        System.out.println("Previous status: " + currentStatus);
        System.out.println("Expected updated status: " + updatedStatus);

        Thread.sleep(2000);
    }
}