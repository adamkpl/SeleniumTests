package com.automationpractice.pageObjects.pages;

import com.automationpractice.pageObjects.utils.WaitWrapper;
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

    public WebElement getWelcomeMessage(){
        WaitWrapper.waitForElement(driver, welcomeMessage);
        System.out.println("Success! Welcome to your account :-)");
        return welcomeMessage;
    }

    public WebElement getAuthErrorMessage(){
        WaitWrapper.waitForElement(driver, authErrorMessage);
        System.out.println("Error! Authentication failed :-(");
        return authErrorMessage;
    }

}