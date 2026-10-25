
package ObjRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Leads {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(linkText = "Leads")
    private WebElement leadsLink;

    @FindBy(xpath = "//img[contains(@alt,'Create Lead')]")
    private WebElement createLeadButton;

    @FindBy(name = "lastname")
    private WebElement lastNameField;

    public Leads(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public void openLeads() {
        wait.until(ExpectedConditions.elementToBeClickable(leadsLink)).click();
    }

    public void clickCreateLead() {
        wait.until(ExpectedConditions.elementToBeClickable(createLeadButton)).click();
    }

    public boolean isLastNameFieldDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOf(lastNameField)).isDisplayed();
    }
}