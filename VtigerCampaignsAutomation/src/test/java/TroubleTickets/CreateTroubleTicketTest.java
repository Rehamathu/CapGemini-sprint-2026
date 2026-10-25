
package TroubleTickets;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseclass.Baseclass;

public class CreateTroubleTicketTest extends Baseclass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyCreateTroubleTicket() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        String ticketTitle =
                "Selenium Ticket " + System.currentTimeMillis();

        // Step 1: Baseclass launches the application and logs in

        // Step 2: Navigate to Trouble Tickets
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Trouble Tickets"))).click();

        Thread.sleep(2000);

        // Step 3: Verify Trouble Tickets list page
        Assert.assertTrue(
                driver.getCurrentUrl().contains("module=HelpDesk")
                        && driver.getCurrentUrl().contains("action=index"),
                "Trouble Tickets list page did not open.");

        System.out.println("Trouble Tickets page opened.");

        // Step 4: Click the green plus icon
        WebElement createButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//img[contains(@src,'btnL3Add.gif')]")));

        createButton.click();

        Thread.sleep(2000);

        // Step 5: Verify Create Trouble Ticket form
        Assert.assertTrue(
                driver.getCurrentUrl().contains("action=EditView"),
                "Create Trouble Ticket form did not open.");

        Assert.assertTrue(
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[normalize-space(text())="
                                + "'Ticket Information']"))).isDisplayed(),
                "Ticket Information section is not displayed.");

        // Step 6: Enter Ticket Title
        WebElement titleField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//textarea")));

        titleField.sendKeys(ticketTitle);

        // Step 7: Select Priority
        new Select(wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("ticketpriorities"))))
                .selectByVisibleText("Low");

        // Step 8: Select Severity
        new Select(wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("ticketseverities"))))
                .selectByVisibleText("Minor");

        // Step 9: Select Category
        new Select(wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("ticketcategories"))))
                .selectByVisibleText("Big Problem");

        // Step 10: Click Save
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@title='Save [Alt+S]']"))).click();

        Thread.sleep(3000);

        // Step 11: Verify saved ticket by its unique title
        boolean ticketFound = !driver.findElements(
                By.xpath("//a[normalize-space()='" + ticketTitle + "']")
        ).isEmpty();

        Assert.assertTrue(
                ticketFound,
                "Ticket was not found after saving: " + ticketTitle);

        System.out.println(
                "CreateTroubleTicketTest passed. Ticket created: "
                        + ticketTitle);

        Thread.sleep(2000);
    }
}
