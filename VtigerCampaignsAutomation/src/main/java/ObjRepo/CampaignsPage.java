
package ObjRepo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CampaignsPage {

    WebDriver driver;
    WebDriverWait wait;

    // Campaign navigation
    @FindBy(linkText = "More")
    private WebElement moreLink;

    @FindBy(linkText = "Campaigns")
    private WebElement campaignsLink;

    // Campaign list page
    @FindBy(xpath = "//img[contains(@alt,'Create Campaign')]")
    private WebElement createCampaignButton;

    // Campaign form fields
    @FindBy(name = "campaignname")
    private WebElement campaignNameTextField;

    @FindBy(name = "campaignstatus")
    private WebElement campaignStatusDropdown;

    @FindBy(name = "campaigntype")
    private WebElement campaignTypeDropdown;

    @FindBy(name = "closingdate")
    private WebElement expectedCloseDateTextField;

    @FindBy(xpath = "//input[@title='Save [Alt+S]']")
    private WebElement saveButton;

    // Quick search
    @FindBy(name = "query_string")
    private WebElement quickSearchTextField;

    @FindBy(xpath = "//input[@type='image' and @title='Find']")
    private WebElement quickSearchFindButton;

    // Search panel
    @FindBy(name = "search_text")
    private WebElement advancedSearchTextField;

    @FindBy(xpath = "//select[option[normalize-space()='Campaign Name']]")
    private WebElement searchFieldDropdown;

    @FindBy(xpath = "//input[@type='button' and contains(@value,'Search Now')]")
    private WebElement searchNowButton;

    // Constructor
    public CampaignsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Navigation methods
    public void clickMore() {
        wait.until(ExpectedConditions.elementToBeClickable(moreLink)).click();
    }

    public void openCampaigns() {
        wait.until(ExpectedConditions.elementToBeClickable(moreLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(campaignsLink)).click();
    }

    public void clickCreateCampaign() {
        wait.until(ExpectedConditions.elementToBeClickable(createCampaignButton)).click();
    }

    // Campaign form methods
    public void enterCampaignName(String campaignName) {
        wait.until(ExpectedConditions.visibilityOf(campaignNameTextField))
                .sendKeys(campaignName);
    }

    public void selectCampaignStatus(String status) {
        new Select(campaignStatusDropdown).selectByVisibleText(status);
    }

    public void selectCampaignType(String type) {
        new Select(campaignTypeDropdown).selectByVisibleText(type);
    }

    public void enterExpectedCloseDate(String date) {
        expectedCloseDateTextField.sendKeys(date);
    }

    public void clickSave() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void clearCampaignName() {
        campaignNameTextField.clear();
    }

    public String getCampaignName() {
        return campaignNameTextField.getAttribute("value");
    }

    // Quick search methods
    public void enterQuickSearch(String campaignName) {
        WebElement search = wait.until(
                ExpectedConditions.visibilityOf(quickSearchTextField));
        search.clear();
        search.sendKeys(campaignName);
    }

    public void clickQuickSearchFind() {
        wait.until(ExpectedConditions.elementToBeClickable(quickSearchFindButton))
                .click();
    }

    // Search panel methods
    public void enterAdvancedSearchText(String campaignName) {
        WebElement search = wait.until(
                ExpectedConditions.visibilityOf(advancedSearchTextField));
        search.clear();
        search.sendKeys(campaignName);
    }

    public void selectSearchField(String fieldName) {
        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOf(searchFieldDropdown));
        new Select(dropdown).selectByVisibleText(fieldName);
    }

    public void clickSearchNow() {
        wait.until(ExpectedConditions.elementToBeClickable(searchNowButton)).click();
    }

    public boolean isCampaignNameDisplayed(String campaignName) {
        return !driver.findElements(
                By.xpath("//table//a[normalize-space()='" + campaignName + "']")
        ).isEmpty();
    }
}
