
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

public class ExpectedResponseDropdownTest extends Baseclass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyExpectedResponseOptions() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        // Step 1: Open Campaigns
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        // Step 2: Open Create Campaign form
        campaignsPage.clickCreateCampaign();
        Thread.sleep(2000);

        // Step 3: Locate Expected Response dropdown
        By responseLocator = By.name("expectedresponse");

        WebElement responseDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(responseLocator)
        );

        // Step 4: Define expected dropdown options
        String[] responses = {
            "--None--",
            "Excellent",
            "Good",
            "Average",
            "Poor"
        };

        // Step 5: Select and verify each option
        for (String response : responses) {

            responseDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(responseLocator)
            );

            responseDropdown.click();
            Thread.sleep(1000);

            Select select = new Select(
                    driver.findElement(responseLocator)
            );

            select.selectByVisibleText(response);
            Thread.sleep(2000);

            String actualResponse = new Select(
                    driver.findElement(responseLocator)
            ).getFirstSelectedOption().getText().trim();

            Assert.assertEquals(
                    actualResponse,
                    response,
                    "Failed to select Expected Response: " + response
            );

            System.out.println(
                    "Selected and verified Expected Response: "
                    + actualResponse
            );
        }

        System.out.println("All Expected Response options verified.");

        Thread.sleep(2000);
    }
}
