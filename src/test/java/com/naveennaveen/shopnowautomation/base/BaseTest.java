package com.naveennaveen.shopnowautomation.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

/**
 * BaseTest class is responsible for setting up and tearing down the WebDriver instance before and after each test method execution.
 * It initializes the WebDriver based on the specified browser type, maximizes the browser window, sets implicit wait, and navigates to the specified base URL.
 */
public class BaseTest {
    static WebDriver driver;
    String baseUrl = "https://www.flipkart.com/";

    /**
     * Method to set up WebDriver instance before each test method execution.
     * It initializes the WebDriver based on the specified browser type, maximizes the browser window, sets implicit wait, and navigates to the specified base URL.
     * @param browserType The type of browser to be used for testing, specified via TestNG parameter.
     */
    @BeforeMethod
    @Parameters("browserType")
    public void setUp(String browserType) {
        switch (browserType.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name: " + browserType);
        }

        driver = DriverManager.getDriver();
        if (driver == null) {
            switch (browserType.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
            }
            DriverManager.setDriver(driver);
        }

        // Maximize browser window
        DriverManager.getDriver().manage().window().maximize();

        // Set implicit wait
        DriverManager.getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        // Navigate to the Flipkart URL
        DriverManager.getDriver().get(baseUrl);

    }

    /**
     * Method to tear down WebDriver instance after each test method execution.
     * It quits the WebDriver instance and removes it from the DriverManager.
     */
    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}