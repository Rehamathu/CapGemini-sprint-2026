
package Campaigns.validation;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ObjRepo.CampaignsPage;
import baseclass.Baseclass;

public class MandatoryFieldsTest extends Baseclass {

    @Test(groups = {"Functional", "Negative"})
    public void verifyCampaignCannotBeCreatedWithoutMandatoryFields()
            throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        // Step 1: Open Campaigns
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        // Step 2: Open the Create Campaign form
        campaignsPage.clickCreateCampaign();
        Thread.sleep(2000);

        // Step 3: Click Save without entering a Campaign Name
        campaignsPage.clickSave();

        // Step 4: Wait for the validation alert
        Alert alert = wait.until(
                ExpectedConditions.alertIsPresent());

        // Step 5: Read and verify the validation message
        String alertMessage = alert.getText().trim();

        Assert.assertEquals(
                alertMessage,
                "Campaign Name cannot be empty",
                "Unexpected validation message.");

        // Step 6: Accept the alert
        alert.accept();

        System.out.println(
                "MandatoryFieldsTest passed: Empty Campaign Name was rejected.");

        Thread.sleep(2000);
    }
}