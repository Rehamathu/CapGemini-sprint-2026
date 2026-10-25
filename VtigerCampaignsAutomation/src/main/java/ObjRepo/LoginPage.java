
package ObjRepo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "user_name")
    private WebElement usernameTextField;

    @FindBy(name = "user_password")
    private WebElement passwordTextField;

    @FindBy(id = "submitButton")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enterUsername(String username) {
        wait.until(
            ExpectedConditions.visibilityOf(usernameTextField)
        ).sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(
            ExpectedConditions.visibilityOf(passwordTextField)
        ).sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(
            ExpectedConditions.elementToBeClickable(loginButton)
        ).click();
    }
    public void loginToApplication(String username, String password) {

        enterUsername(username);
        enterPassword(password);
        clickLoginButton();

        // Verify that the Home page has loaded
        wait.until(
            ExpectedConditions.or(
                ExpectedConditions.urlContains("module=Home"),
                ExpectedConditions.presenceOfElementLocated(
                    By.linkText("More")
                )
            )
        );

        System.out.println(
            "Login successful. Current URL: "
            + driver.getCurrentUrl()
        );
    }
}
