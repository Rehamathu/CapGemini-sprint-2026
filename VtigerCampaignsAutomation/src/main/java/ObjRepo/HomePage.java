package ObjRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    @FindBy(linkText = "More")
    private WebElement moreLink;

    @FindBy(linkText = "Campaigns")
    private WebElement campaignsLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickMore() {
        moreLink.click();
    }

    public void clickCampaigns() {
        campaignsLink.click();
    }

    public void navigateToCampaigns() {
        clickMore();
        clickCampaigns();
    }
}



