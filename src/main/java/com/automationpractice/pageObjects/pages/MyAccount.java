package com.automationpractice.pageObjects.pages;

import com.automationpractice.pageObjects.utils.WaitWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends AbstractPageObject {

    @FindBy(xpath = "//*[contains(text(),'Welcome to your account')]")
    private WebElement welcomeMessage;

    public MyAccount(WebDriver driver) {
        super(driver);
    }

    public WebElement getWelcomeMessage(){
        WaitWrapper.waitForElement(getDriver(),30,welcomeMessage);
        System.out.println("Success! Welcome to your account :-)");
        return welcomeMessage;
    }
}