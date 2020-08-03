package com.automationpractice.pageObjects.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

/**
 * A set of WaitWrappers.
 * The goal of these methods is to add stability to the test being performed. This will make the current driver process
 * to wait for a web element to appear on the page before executing further statements.
 *
 * @see <a href="https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/FluentWait.html">FluentWait @ www.selenium.dev</a>
 */

public class WaitWrapper {

    public static void waitForElement(WebDriver driver, long timeoutInSeconds, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElement(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitFluentlyForElement(WebDriver driver, By by) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });

    }

    public static void waitFluentlyForElement(WebDriver driver, By by, int withTimeoutInSeconds, int pollingEveryInSeconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(withTimeoutInSeconds))
                .pollingEvery(Duration.ofSeconds(pollingEveryInSeconds))
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });

    }

}