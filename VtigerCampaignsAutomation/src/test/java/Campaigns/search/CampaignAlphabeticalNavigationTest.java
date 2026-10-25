
package Campaigns.search;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ObjRepo.CampaignsPage;
import baseclass.Baseclass;

public class CampaignAlphabeticalNavigationTest extends Baseclass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyAlphabeticalCampaignNavigation()
            throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        CampaignsPage campaignsPage = new CampaignsPage(driver);

        // Step 1: Open Campaigns page
        campaignsPage.openCampaigns();
        Thread.sleep(2000);

        // Step 2: Verify alphabetical navigation is displayed
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("alpha_1")));

        // Step 3: Click every letter from A to Z
        for (int i = 1; i <= 26; i++) {

            String letter = String.valueOf((char) ('A' + i - 1));
            By letterLocator = By.id("alpha_" + i);

            WebElement alphabetLink = wait.until(
                    ExpectedConditions.elementToBeClickable(letterLocator));

            alphabetLink.click();
            Thread.sleep(1500);

            // Re-find the element after the page updates
            WebElement selectedLetter = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            letterLocator));

            String actualClass =
                    selectedLetter.getAttribute("class");

            System.out.println(
                    "Clicked " + letter
                    + " | Actual class: " + actualClass
                    + " | URL: " + driver.getCurrentUrl());

            Assert.assertTrue(
                    actualClass != null
                    && actualClass.toLowerCase()
                            .contains("searchalphselected"),
                    "Letter " + letter
                    + " was not selected successfully.");
        }

        System.out.println(
                "CampaignAlphabeticalNavigationTest passed: "
                + "A to Z navigation verified.");

        Thread.sleep(2000);
    }
}
