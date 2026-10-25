package TroubleTickets;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseclass.Baseclass;

public class TroubleTicketsNavigationTest extends Baseclass {

    @Test(groups = {"Functional", "Positive" ,"Smoke"})
    public void verifyTroubleTicketsNavigation() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        // Step 1: Open Trouble Tickets
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Trouble Tickets"))).click();

        Thread.sleep(2000);

        // Step 2: Verify Trouble Tickets page opened
        Assert.assertTrue(
                driver.getCurrentUrl().contains("module=HelpDesk"),
                "Trouble Tickets page did not open.");

        // Step 3: Verify alphabetical navigation
        Assert.assertTrue(
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.id("alpha_1"))).isDisplayed(),
                "Alphabetical navigation is not displayed.");

        // Step 4: Verify ticket list is displayed
        Assert.assertTrue(
                driver.findElement(By.xpath(
                        "//table//a[contains(@href,'HelpDesk') "
                        + "and string-length(normalize-space()) > 0]"))
                        .isDisplayed(),
                "Trouble Tickets list is not displayed.");

        System.out.println(
                "TroubleTicketsNavigationTest passed.");

        Thread.sleep(2000);
    }
}