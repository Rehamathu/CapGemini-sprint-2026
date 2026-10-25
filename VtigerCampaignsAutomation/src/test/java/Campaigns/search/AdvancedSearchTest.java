
package Campaigns.search;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ObjRepo.CampaignsPage;
import baseclass.Baseclass;

public class AdvancedSearchTest extends Baseclass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyAdvancedSearchByCampaignName()
            throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        String campaignName = "Diwali Offer 2026";

        // Step 1: Open Campaigns
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        // Step 2: Click Go to Advanced Search
        By advancedSearchLink = By.xpath(
                "//a[contains(normalize-space(.), 'Go to Advanced Search')]"
        );

        WebElement link = wait.until(
                ExpectedConditions.elementToBeClickable(advancedSearchLink)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", link
        );

        link.click();
        Thread.sleep(2000);

        // Step 3: Wait for Advanced Search field dropdown
        WebElement fieldDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("fcol0")
                )
        );

        // Step 4: Select Campaign Name
        new Select(fieldDropdown).selectByVisibleText("Campaign Name");
        Thread.sleep(1000);

        // Step 5: Select equals operator
        WebElement operatorDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("fop0")
                )
        );

        new Select(operatorDropdown).selectByVisibleText("equals");
        Thread.sleep(1000);

        // Step 6: Enter the campaign name
        WebElement valueField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("fval0")
                )
        );

        valueField.clear();
        valueField.sendKeys(campaignName);
        Thread.sleep(1000);

        // Step 7: Click Advanced Search Now
        By searchButtonLocator = By.xpath(
                "//input[@type='button' " +
                "and @onclick=\"callSearch('Advanced');\"]"
        );

        WebElement searchButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        searchButtonLocator
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                searchButton
        );

        Thread.sleep(1000);

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        searchButtonLocator
                )
        ).click();

        Thread.sleep(3000);

        // Step 8: Verify search results
        Assert.assertTrue(
                campaignsPage.isCampaignNameDisplayed(campaignName),
                "Advanced Search did not find campaign: " + campaignName
        );

        System.out.println(
                "Advanced Search passed for: " + campaignName
        );

        Thread.sleep(5000);
    }
}



