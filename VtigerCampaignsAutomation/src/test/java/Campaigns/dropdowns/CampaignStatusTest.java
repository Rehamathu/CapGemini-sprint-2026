
package Campaigns.dropdowns;

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

public class CampaignStatusTest extends Baseclass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyCampaignStatusOptions() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        // Step 1: Open Campaigns
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        // Step 2: Open Create Campaign
        campaignsPage.clickCreateCampaign();
        Thread.sleep(2000);

        By statusLocator = By.name("campaignstatus");

        String[] statuses = {
            "--None--",
            "Planning",
            "Active",
            "Inactive",
            "Completed",
            "Cancelled"
        };

        for (String status : statuses) {

            // Step 3: Wait for and click the dropdown field
            WebElement dropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(statusLocator)
            );

            dropdown.click();
            Thread.sleep(1000);

            // Step 4: Select the required option
            Select select = new Select(
                    driver.findElement(statusLocator)
            );

            select.selectByVisibleText(status);

            // Pause to observe the selected value
            Thread.sleep(2000);

            // Step 5: Verify the selected value
            String actualStatus = new Select(
                    driver.findElement(statusLocator)
            ).getFirstSelectedOption().getText().trim();

            Assert.assertEquals(
                    actualStatus,
                    status,
                    "Failed to select Campaign Status: " + status
            );

            System.out.println("Selected and verified: " + actualStatus);
        }

        System.out.println("All Campaign Status options verified.");

        Thread.sleep(2000);
    }
}
