
package Campaigns.crud;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Test;

import ObjRepo.CampaignsPage;
import baseclass.Baseclass;

public class DeleteCampaignTest extends Baseclass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyCampaignCanBeDeleted() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        // 1. Open the Campaigns page
        campaignsPage.openCampaigns();

        // 2. Locate campaign links dynamically
        By campaignLinksLocator = By.xpath(
                "//tr[starts-with(@id,'row_')]//a"
                + "[contains(@href,'module=Campaigns')"
                + " and contains(@href,'action=DetailView')]"
        );

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        campaignLinksLocator
                )
        );

        // 3. Find the first visible campaign
        List<WebElement> campaignLinks =
                driver.findElements(campaignLinksLocator);

        WebElement selectedCampaign = null;

        for (WebElement link : campaignLinks) {
            if (link.isDisplayed()
                    && !link.getText().trim().isEmpty()) {
                selectedCampaign = link;
                break;
            }
        }

        Assert.assertNotNull(
                selectedCampaign,
                "No campaign is available to delete."
        );

        // 4. Get the campaign name and row dynamically
        String campaignName = selectedCampaign.getText().trim();

        WebElement campaignRow = selectedCampaign.findElement(
                By.xpath(
                        "./ancestor::tr"
                        + "[starts-with(@id,'row_')][1]"
                )
        );

        String rowId = campaignRow.getAttribute("id");

        System.out.println("Selected campaign: " + campaignName);

        // 5. Select the checkbox using the actual Vtiger locator
        WebElement checkbox = campaignRow.findElement(
                By.cssSelector("input[name='selected_id']")
        );

        if (!checkbox.isSelected()) {
            wait.until(
                    ExpectedConditions.elementToBeClickable(checkbox)
            );
            checkbox.click();
        }

        // Step 6: Click Delete
        By deleteButtonLocator = By.xpath(
                "//form[@name='massdelete']//input"
                + "[@type='submit' and "
                + "(contains(@value,'Delete') or @title='Delete')]"
                + " | //input[@value='Delete' or @title='Delete']"
                + " | //button[normalize-space()='Delete']"
        );

        WebElement deleteButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        deleteButtonLocator
                )
        );

        deleteButton.click();

        // Step 7: Handle JavaScript or HTML confirmation
        try {
            Alert alert = new WebDriverWait(
                    driver, Duration.ofSeconds(3)
            ).until(ExpectedConditions.alertIsPresent());

            alert.accept();

        } catch (TimeoutException e) {
            // No JavaScript alert appeared.
            // Inspect and handle any HTML confirmation dialog here.
        }

        // Step 8: Wait for the deletion request to finish
        wait.until(ExpectedConditions.or(
                ExpectedConditions.stalenessOf(campaignRow),
                ExpectedConditions.invisibilityOfElementLocated(
                        By.id(rowId)
                )
        ));

        // Step 9: Verify that the campaign is no longer in the list
        boolean stillDisplayed = driver.findElements(
                By.id(rowId)
        ).stream().anyMatch(WebElement::isDisplayed);

        Assert.assertFalse(
                stillDisplayed,
                "Campaign was not deleted: " + campaignName
        );

        System.out.println(
                "Campaign deleted successfully: " + campaignName
        );

        

        System.out.println(
                "Successfully deleted campaign: " + campaignName
        );
    }
}

