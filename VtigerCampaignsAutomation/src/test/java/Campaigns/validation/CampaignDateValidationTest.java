
package Campaigns.validation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ObjRepo.CampaignsPage;
import baseclass.Baseclass;

public class CampaignDateValidationTest extends Baseclass {

    @Test(groups = {"Functional", "Negative"})
    public void verifyExpectedCloseDateFieldIsDisplayed()
            throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        // Step 1: Open the Campaigns page
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        // Step 2: Open the Create Campaign form
        campaignsPage.clickCreateCampaign();
        Thread.sleep(2000);

        // Step 3: Wait for the Expected Close Date field
        WebElement closeDateField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("closingdate")));

        // Step 4: Verify the field is displayed
        Assert.assertTrue(
                closeDateField.isDisplayed(),
                "Expected Close Date field was not displayed.");

        System.out.println(
                "CampaignDateValidationTest passed: Expected Close Date field is displayed.");

        Thread.sleep(2000);
    }
}
