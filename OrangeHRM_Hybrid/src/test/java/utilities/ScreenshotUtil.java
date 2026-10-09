package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String takeScreenshot(
            WebDriver driver,
            String screenshotName) throws IOException {

        // Create screenshots folder
        String folderPath = "screenshots";

        File folder = new File(folderPath);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Date and time
        String timestamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss")
                        .format(new Date());

        // Take screenshot
        TakesScreenshot ts =
                (TakesScreenshot) driver;

        File source =
                ts.getScreenshotAs(OutputType.FILE);

        // Screenshot destination
        String destination =
                folderPath + File.separator
                + screenshotName + "_"
                + timestamp + ".png";

        File target =
                new File(destination);

        FileUtils.copyFile(source, target);

        System.out.println(
                "Screenshot saved at: "
                + target.getAbsolutePath());

        return target.getAbsolutePath();
    }
}