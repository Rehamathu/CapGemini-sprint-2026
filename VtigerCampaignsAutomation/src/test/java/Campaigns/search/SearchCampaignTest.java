package Campaigns.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import ObjRepo.CampaignsPage;
import baseclass.Baseclass;

public class SearchCampaignTest extends Baseclass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyCampaignSearchByName() throws InterruptedException {

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        String campaignName = "capgemini";

        // Open Campaigns
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        // Enter campaign name in Search for
        WebElement searchInput = driver.findElement(By.name("search_text"));
        searchInput.clear();
        searchInput.sendKeys(campaignName);

        Thread.sleep(1500);

        // Select Campaign Name from the In dropdown
        WebElement searchFieldDropdown = driver.findElement(
            By.xpath("//select[option[normalize-space()='Campaign Name']]")
        );

        searchFieldDropdown.click();
        Thread.sleep(2000);

        Select select = new Select(searchFieldDropdown);
        select.selectByVisibleText("Campaign Name");

        Thread.sleep(1500);

        // Click Search Now
        WebElement searchNowButton = driver.findElement(
            By.xpath("//input[@type='button' and contains(@value,'Search Now')]")
        );
        searchNowButton.click();

        Thread.sleep(3000);

        // Verify at least one matching campaign appears
        boolean campaignFound = !driver.findElements(
            By.xpath("//table//a[normalize-space()='" + campaignName + "']")
        ).isEmpty();

        Assert.assertTrue(
            campaignFound,
            "Campaign was not found: " + campaignName
        );

        System.out.println("Campaign search by name passed: " + campaignName);
        System.out.println("Current URL: " + driver.getCurrentUrl());

        Thread.sleep(2000);
    }
}