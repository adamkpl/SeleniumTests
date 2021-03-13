package com.automationpractice.pageObjects.components;

import com.automationpractice.pageObjects.pages.AbstractPageObject;
import com.automationpractice.pageObjects.utils.WaitWrapper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginToAccountForm extends AbstractPageObject {

    @FindBy(id = "email")
    private WebElement loginField;
    @FindBy(id = "passwd")
    private WebElement passwordField;
    @FindBy(id = "SubmitLogin")
    private WebElement signInButton;

    public LoginToAccountForm(WebDriver driver) {
        super(driver);
    }

    Logger logger = Logger.getLogger(LoginToAccountForm.class);

    public LoginToAccountForm withUsername(String username) {
        WaitWrapper.waitForElement(driver, loginField);
        logger.info("Typing username: " + username);
        loginField.sendKeys(username);
        logger.trace("Username typed: " + username);
        return this;
    }

    public LoginToAccountForm withPassword(String password) {
        WaitWrapper.waitForElement(driver, passwordField);
        logger.warn("DEMO-only purposes. In real-life scenario plain-text password logging is NOT acceptable.");
        logger.info("Typing password: " + password);
        passwordField.sendKeys(password);
        logger.trace("Password typed: " + password);
        return this;
    }

    public LoginToAccountForm clickSignInButton() {
        WaitWrapper.waitForElement(driver, signInButton);
        logger.info("Clicking " + signInButton);
        signInButton.click();
        logger.trace("Clicked " + signInButton);
        return this;
    }

}