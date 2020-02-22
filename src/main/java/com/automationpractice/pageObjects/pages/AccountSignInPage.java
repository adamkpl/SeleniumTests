package com.automationpractice.pageObjects.pages;

import com.automationpractice.pageObjects.components.LoginToAccountForm;
import com.automationpractice.pageObjects.components.RegisterAccountForm;
import org.openqa.selenium.WebDriver;

public class AccountSignInPage extends AbstractPageObject {

    private RegisterAccountForm registerAccountForm;
    private LoginToAccountForm loginToAccountForm;

    public AccountSignInPage(WebDriver driver){
        super(driver);
        this.registerAccountForm = new RegisterAccountForm(driver);
        this.loginToAccountForm = new LoginToAccountForm(driver);
    }

    public RegisterAccountForm createAnAccount(){
        return registerAccountForm;
    }

    public LoginToAccountForm loginToAccount(){
        return loginToAccountForm;
    }

}