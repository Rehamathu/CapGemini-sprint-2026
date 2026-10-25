
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

public class CampaignTypeTest extends Baseclass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyCampaignTypeOptions() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        // Step 1: Open Campaigns
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        // Step 2: Open Create Campaign form
        campaignsPage.clickCreateCampaign();
        Thread.sleep(2000);

        // Step 3: Locate Campaign Type dropdown
        By typeLocator = By.name("campaigntype");

        WebElement typeDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(typeLocator)
        );

        // Step 4: Define expected dropdown options
        String[] types = {
            "--None--",
            "Conference",
            "Webinar",
            "Trade Show",
            "Public Relations",
            "Partners",
            "Referral Program",
            "Advertisement",
            "Banner Ads",
            "Direct Mail",
            "Email",
            "Telemarketing",
            "Others"
        };

        // Step 5: Select and verify each option
        for (String type : types) {

            typeDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(typeLocator)
            );

            typeDropdown.click();
            Thread.sleep(1000);

            Select select = new Select(
                    driver.findElement(typeLocator)
            );

            select.selectByVisibleText(type);
            Thread.sleep(2000);

            String actualType = new Select(
                    driver.findElement(typeLocator)
            ).getFirstSelectedOption().getText().trim();

            Assert.assertEquals(
                    actualType,
                    type,
                    "Failed to select Campaign Type: " + type
            );

            System.out.println(
                    "Selected and verified Campaign Type: " + actualType
            );
        }

        System.out.println("All Campaign Type options verified.");

        Thread.sleep(2000);
    }
}
