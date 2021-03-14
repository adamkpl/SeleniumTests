package com.automationpractice.pageObjects.utils;

import org.apache.commons.io.FileUtils;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class TakeScreenshotWrapper {

    private static final Logger LOGGER = LogManager.getLogger(TakeScreenshotWrapper.class);
    private static final String OUTPUT_DIR = "testOutputs/screenshots/";

    public static void takeScreenshot(WebDriver driver, String fileName) {
        try {
            LOGGER.trace("Saving screenshot: " + OUTPUT_DIR + fileName);
            File scrFile = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(OUTPUT_DIR + fileName));
            LOGGER.trace("Screenshot saved: " + OUTPUT_DIR + fileName);
        } catch (IOException e) {
            LOGGER.error("Screenshot not saved.");
            e.printStackTrace();
        }
    }

}