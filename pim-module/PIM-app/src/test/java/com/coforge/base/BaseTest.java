package com.coforge.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(com.coforge.listener.ExtentTestNGListener.class)
public class BaseTest {

    public static WebDriver driver;
    protected Properties prob;

    @BeforeMethod
    public void setup() throws IOException {
        String projectpath = System.getProperty("user.dir");

        prob = new Properties();
        FileInputStream fis = new FileInputStream(
                projectpath + "\\src\\test\\resources\\config.properties");
        prob.load(fis);

        String browser = prob.getProperty("browser");
        String url = prob.getProperty("url");

        if (browser != null && browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        if (url != null) {
            driver.get(url);
        }

        fis.close();
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}