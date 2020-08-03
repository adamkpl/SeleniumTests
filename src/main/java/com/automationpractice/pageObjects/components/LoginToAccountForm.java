package com.automationpractice.pageObjects.components;

import com.automationpractice.pageObjects.pages.AbstractPageObject;
import com.automationpractice.pageObjects.utils.WaitWrapper;

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

    public LoginToAccountForm withUsername(String username){
        WaitWrapper.waitForElement(getDriver(), loginField);
        loginField.sendKeys(username);
        return this;
    }

    public LoginToAccountForm withPassword(String password){
        WaitWrapper.waitForElement(getDriver(), passwordField);
        passwordField.sendKeys(password);
        return this;
    }

    public LoginToAccountForm clickSignInButton(){
        WaitWrapper.waitForElement(getDriver(), signInButton);
        signInButton.click();
        return this;
    }

}