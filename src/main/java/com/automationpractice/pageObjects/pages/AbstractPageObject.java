package com.automationpractice.pageObjects.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPageObject {

    private WebDriver driver;

    //Constructor. We initialize page elements via PageFactory
    public AbstractPageObject(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public WebDriver getDriver(){
        return driver;
    }

}