
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

public class AssignedToDropdownTest extends Baseclass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyAssignedToDropdownOptions() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        // Step 1: Open Campaigns
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        // Step 2: Open Create Campaign form
        campaignsPage.clickCreateCampaign();
        Thread.sleep(2000);

        // Step 3: Locate Assigned To dropdown
        By assignedToLocator = By.name("assigned_user_id");

        WebElement assignedToDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(assignedToLocator)
        );

        // Step 4: Define the expected options
        Select initialSelect = new Select(assignedToDropdown);

        String[] options = new String[initialSelect.getOptions().size()];

        for (int i = 0; i < initialSelect.getOptions().size(); i++) {
            options[i] = initialSelect.getOptions().get(i).getText().trim();
        }

        Assert.assertTrue(
                options.length > 0,
                "Assigned To dropdown contains no options."
        );

        // Step 5: Select and verify each option
        for (String option : options) {

            WebElement dropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(assignedToLocator)
            );

            dropdown.click();
            Thread.sleep(1000);

            Select select = new Select(
                    driver.findElement(assignedToLocator)
            );

            select.selectByVisibleText(option);
            Thread.sleep(2000);

            String actualOption = new Select(
                    driver.findElement(assignedToLocator)
            ).getFirstSelectedOption().getText().trim();

            Assert.assertEquals(
                    actualOption,
                    option,
                    "Failed to select Assigned To option: " + option
            );

            System.out.println(
                    "Selected and verified Assigned To: " + actualOption
            );
        }

        System.out.println("All Assigned To options verified.");

        Thread.sleep(2000);
    }
}
