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

    public Boolean isWelcomeMessageDisplayed(){
        WaitWrapper.waitForElement(driver, welcomeMessage);
        //System.out.println("Login successful.");
        return welcomeMessage.isDisplayed();
    }

    public Boolean isAuthorizationErrorMessageDisplayed(){
        WaitWrapper.waitForElement(driver, authErrorMessage);
        //System.out.println("Login failed.");
        return authErrorMessage.isDisplayed();
    }

    public String getMyAccountUrl() {
        return driver.getCurrentUrl();
    }

}