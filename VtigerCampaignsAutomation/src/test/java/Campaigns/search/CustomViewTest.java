
package Campaigns.search;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ObjRepo.CampaignsPage;
import baseclass.Baseclass;

public class CustomViewTest extends Baseclass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyCampaignFilterChange() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        // Step 1: Open Campaigns
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        Assert.assertTrue(
                driver.getCurrentUrl().contains("module=Campaigns"),
                "Campaigns page did not open.");

        // Step 2: Locate Filters dropdown
        By filterLocator = By.id("viewname");

        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(filterLocator));

        Select filters = new Select(dropdown);

        System.out.println("Available filters:");

        for (WebElement option : filters.getOptions()) {
            System.out.println(option.getText().trim());
        }

        // Step 3: Select the saved filter
        String targetFilter = "campign name";

        boolean optionExists = filters.getOptions().stream()
                .anyMatch(option ->
                        option.getText().trim().equals(targetFilter));

        Assert.assertTrue(
                optionExists,
                "Filter option not found: " + targetFilter);

        filters.selectByVisibleText(targetFilter);
        Thread.sleep(3000);

        // Step 4: Verify the selected filter
        WebElement updatedDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(filterLocator));

        String selectedFilter = new Select(updatedDropdown)
                .getFirstSelectedOption()
                .getText()
                .trim();

        Assert.assertEquals(
                selectedFilter,
                targetFilter,
                "The selected filter is incorrect.");

        System.out.println("Selected filter: " + selectedFilter);

        // Step 5: Find visible elements containing campaign IDs
        List<WebElement> elements = driver.findElements(
                By.xpath("//*[starts-with(normalize-space(text()), 'CAM')]"));

        int campaignCount = 0;

        System.out.println("Campaign IDs found:");

        for (WebElement element : elements) {

            if (!element.isDisplayed()) {
                continue;
            }

            String campaignNumber = element.getText().trim();

            if (!campaignNumber.matches("CAM\\d+")) {
                continue;
            }

            campaignCount++;
            System.out.println(campaignNumber);
        }

        // Step 6: Verify campaign IDs are present
        Assert.assertTrue(
                campaignCount > 0,
                "No visible campaign IDs were found on the page.");

        System.out.println(
                "Campaign IDs found: " + campaignCount);

        System.out.println(
                "Filter selection and campaign ID visibility verified.");

        Thread.sleep(2000);
    }
}
