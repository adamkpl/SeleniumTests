package com.automationpractice.pageObjects.pages;

import com.automationpractice.pageObjects.utils.WaitWrapper;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends AbstractPageObject {

    @FindBy(css = ".info-account")
    private WebElement welcomeMessage;

    @FindBy(css = ".alert")
    private WebElement authErrorMessage;

    public MyAccount(WebDriver driver) {
        super(driver);
    }

    Logger logger = LogManager.getLogger(MyAccount.class);

    public Boolean isWelcomeMessageDisplayed() {
        WaitWrapper.waitForElement(driver, welcomeMessage);
        logger.info("My Account welcome page opened.");
        //System.out.println("Login successful.");
        return welcomeMessage.isDisplayed();
    }

    public Boolean isAuthorizationErrorMessageDisplayed() {
        WaitWrapper.waitForElement(driver, authErrorMessage);
        logger.warn("Error! Authentication failed.");
        //System.out.println("Login failed.");
        return authErrorMessage.isDisplayed();
    }

    public String getMyAccountUrl() {
        logger.trace("Retrieving current URL: " + driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

}