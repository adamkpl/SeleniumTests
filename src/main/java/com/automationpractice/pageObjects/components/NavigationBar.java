package com.automationpractice.pageObjects.components;

import com.automationpractice.pageObjects.pages.AbstractPageObject;
import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.utils.WaitWrapper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationBar extends AbstractPageObject {

    @FindBy(partialLinkText = "Sign in")
    private WebElement signInLink;

    @FindBy(css = ".header_user_info > a")
    private WebElement username;

    public NavigationBar(WebDriver driver){
        super(driver);
    }

    Logger logger = Logger.getLogger(NavigationBar.class);

    public AccountSignInPage clickSignInLink(){
        WaitWrapper.waitForElement(driver, signInLink);
        logger.info("Clicking " + signInLink);
        signInLink.click();
        logger.trace("Clicked " + signInLink);
        return new AccountSignInPage(driver);
    }

}
