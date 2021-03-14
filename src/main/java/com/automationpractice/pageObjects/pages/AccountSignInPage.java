package com.automationpractice.pageObjects.pages;

import com.automationpractice.pageObjects.components.LoginToAccountForm;
import com.automationpractice.pageObjects.components.RegisterAccountForm;
import com.automationpractice.pageObjects.utils.Url;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

public class AccountSignInPage extends AbstractPageObject {

    private RegisterAccountForm registerAccountForm;
    private LoginToAccountForm loginToAccountForm;

    public AccountSignInPage(WebDriver driver) {
        super(driver);
        this.registerAccountForm = new RegisterAccountForm(driver);
        this.loginToAccountForm = new LoginToAccountForm(driver);
    }

    Logger logger = LogManager.getLogger(AccountSignInPage.class);

    public RegisterAccountForm createAnAccount() {
        return registerAccountForm;
    }

    public LoginToAccountForm loginToAccount() {
        return loginToAccountForm;
    }

    public AccountSignInPage navigateToAccountSignInPage() {
        logger.info("Opening page: " + Url.MY_ACCOUNT);
        driver.get(Url.MY_ACCOUNT);
        logger.info("Opened page: " + Url.MY_ACCOUNT);
        return this;
    }

}