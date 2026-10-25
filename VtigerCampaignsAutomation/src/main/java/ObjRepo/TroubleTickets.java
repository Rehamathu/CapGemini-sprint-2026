
package ObjRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TroubleTickets {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(linkText = "More")
    private WebElement moreLink;

    @FindBy(linkText = "Trouble Tickets")
    private WebElement troubleTicketsLink;

    @FindBy(xpath = "//img[contains(@alt,'Create Trouble Ticket')]")
    private WebElement createTicketButton;

    public TroubleTickets(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public void openTroubleTickets() {
        wait.until(ExpectedConditions.elementToBeClickable(moreLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(troubleTicketsLink)).click();
    }

    public void clickCreateTicket() {
        wait.until(ExpectedConditions.elementToBeClickable(createTicketButton)).click();
    }
}