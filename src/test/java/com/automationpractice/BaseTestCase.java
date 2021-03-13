package com.automationpractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseTestCase {

    protected static WebDriver driver;
    protected static Logger logger = Logger.getLogger(BaseTestCase.class);

    @BeforeClass
    public static void setupClass() {
        DOMConfigurator.configure("log4j.xml");
        WebDriverManager.chromedriver().setup();
        logger.trace("Initializing driver.");
        driver = new ChromeDriver();
        logger.trace("Driver initialized.");
    }

    @Before
    public void setupTest() {
        logger.trace("Maximizing the current window.");
        driver.manage().window().maximize();
        logger.trace("Deleting all cookies.");
        driver.manage().deleteAllCookies();
        logger.trace("All cookies deleted.");
    }

    @AfterClass
    public static void teardown() {
        if (driver != null) {
            logger.trace("Closing the current window.");
            driver.close();
            logger.trace("Closed the current window.");
            logger.trace("Kill this driver. Closing every associated window.");
            driver.quit();
            logger.trace("Driver killed, closed every associated window.");

        }
    }

}