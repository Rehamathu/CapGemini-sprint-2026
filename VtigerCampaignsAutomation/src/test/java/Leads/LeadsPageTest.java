
package Leads;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseclass.Baseclass;

public class LeadsPageTest extends Baseclass {

    @Test(groups = {"Functional", "Positive"})
    public void verifyLeadsSearchControls() throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        // Step 1: Open Leads page
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Leads"))).click();

        Thread.sleep(2000);

        // Step 2: Verify search text field
        WebElement searchField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("search_text")));

        Assert.assertTrue(
                searchField.isDisplayed(),
                "Leads search field is not displayed.");

        // Step 3: Verify search-field dropdown
        WebElement searchDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//select[option[normalize-space()='Lead No']]")));

        Select select = new Select(searchDropdown);

        List<WebElement> options = select.getOptions();

        System.out.println("Available Leads search fields:");

        for (WebElement option : options) {
            System.out.println(option.getText().trim());
        }

        // Step 4: Verify expected search options
        String[] expectedOptions = {
            "Lead No",
            "Last Name",
            "First Name",
            "Company",
            "Phone",
            "Website",
            "Email",
            "Assigned To"
        };

        for (String expected : expectedOptions) {
            boolean found = options.stream().anyMatch(option ->
                    option.getText().trim().equalsIgnoreCase(expected));

            Assert.assertTrue(
                    found,
                    "Search option not found: " + expected);
        }

        // Step 5: Select Last Name
        select.selectByVisibleText("Last Name");

        Assert.assertEquals(
                select.getFirstSelectedOption().getText().trim(),
                "Last Name",
                "Last Name option was not selected.");

        System.out.println(
                "LeadsPageTest passed: Search controls and options verified.");

        Thread.sleep(3000);
    }
}
