
package baseclass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import genericUtility.PropertiesUtility;
import genericUtility.ScreenshotUtility;
import ObjRepo.LoginPage;

public class Baseclass {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("") String xmlBrowser)
            throws IOException {

        PropertiesUtility property = new PropertiesUtility();

        String browser = xmlBrowser;

        if (browser == null || browser.trim().isEmpty()) {
            browser = property.readDatafromPropertyFile("browser");
        }

        String url = property.readDatafromPropertyFile("url");
        String username = property.readDatafromPropertyFile("username");
        String password = property.readDatafromPropertyFile("password");

        // Initialize browser
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();

        } else {
            throw new RuntimeException(
                    "Unsupported browser: " + browser);
        }

        System.out.println("Browser: " + browser);

        driver.manage().window().maximize();

        // Open application
        driver.get(url);

        // Login to application
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginToApplication(username, password);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        try {
            // Capture screenshots only for Negative tests
            if (driver != null && isNegativeTest(result)) {
                captureNegativeScreenshot(result);
            }

        } catch (Exception e) {
            System.err.println(
                    "Screenshot capture failed: " + e.getMessage());

        } finally {
            // Always close browser
            if (driver != null) {
                try {
                    driver.quit();

                } catch (Exception e) {
                    System.err.println(
                            "Browser cleanup failed: " + e.getMessage());

                } finally {
                    driver = null;
                }
            }
        }
    }

    // Check whether the test belongs to the Negative group
    private boolean isNegativeTest(ITestResult result) {

        for (String group : result.getMethod().getGroups()) {
            if ("Negative".equalsIgnoreCase(group)) {
                return true;
            }
        }

        return false;
    }

    // Capture screenshot for passed or failed Negative tests
    private void captureNegativeScreenshot(ITestResult result)
            throws IOException {

        ScreenshotUtility screenshotUtility =
                new ScreenshotUtility();

        String testName =
                result.getTestClass().getRealClass().getSimpleName()
                + "_"
                + result.getMethod().getMethodName()
                + "_"
                + getResultName(result);

        String screenshotPath =
                screenshotUtility.captureScreenshot(driver, testName);

        Reporter.log(
                "Negative test screenshot saved: " + screenshotPath,
                true);
    }

    // Include test result in the screenshot filename
    private String getResultName(ITestResult result) {

        if (result.getStatus() == ITestResult.SUCCESS) {
            return "PASS";

        } else if (result.getStatus() == ITestResult.FAILURE) {
            return "FAIL";

        } else if (result.getStatus() == ITestResult.SKIP) {
            return "SKIP";
        }

        return "UNKNOWN";
    }
}
