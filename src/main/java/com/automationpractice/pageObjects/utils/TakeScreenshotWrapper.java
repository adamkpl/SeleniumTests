package com.automationpractice.pageObjects.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class TakeScreenshotWrapper {

    public static void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        File scrFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("testOutputs/screenshots/" + fileName));
    }

}