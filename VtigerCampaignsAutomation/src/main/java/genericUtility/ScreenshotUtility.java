
package genericUtility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility {

    public String captureScreenshot(
            WebDriver driver, String screenshotName)
            throws IOException {

        if (driver == null) {
            throw new IllegalArgumentException(
                    "WebDriver must not be null.");
        }

        if (!(driver instanceof TakesScreenshot)) {
            throw new IllegalArgumentException(
                    "WebDriver does not support screenshots.");
        }

        Path screenshotDirectory = Paths.get("screenshots");
        Files.createDirectories(screenshotDirectory);

        String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));

        String safeName = screenshotName.replaceAll(
                "[^a-zA-Z0-9._-]", "_");

        Path destination = screenshotDirectory.resolve(
                safeName + "_" + timestamp + ".png");

        File source = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        Files.copy(
                source.toPath(),
                destination,
                StandardCopyOption.REPLACE_EXISTING);

        return destination.toAbsolutePath().toString();
    }
}
