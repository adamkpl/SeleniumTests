package com.automationpractice.pageObjects.pages;

import com.automationpractice.pageObjects.utils.WaitWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends AbstractPageObject {

    @FindBy(xpath = "//*[contains(text(),'Welcome to your account')]")
    private WebElement welcomeMessage;

    @FindBy(xpath = "//div[@class='alert alert-danger'][contains(.,'Authentication failed.')]")
    private WebElement authErrorMessage;

    public MyAccount(WebDriver driver) {
        super(driver);
    }

    public WebElement getWelcomeMessage(){
        WaitWrapper.waitForElement(getDriver(), welcomeMessage);
        System.out.println("Success! Welcome to your account :-)");
        return welcomeMessage;
    }

    public WebElement getAuthErrorMessage(){
        WaitWrapper.waitForElement(getDriver(), authErrorMessage);
        System.out.println("Error! Authentication failed :-(");
        return authErrorMessage;
    }

}