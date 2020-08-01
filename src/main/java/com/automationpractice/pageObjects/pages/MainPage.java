package com.automationpractice.pageObjects.pages;

import com.automationpractice.pageObjects.components.NavigationBar;
import com.automationpractice.pageObjects.utils.Url;
import org.openqa.selenium.WebDriver;

public class MainPage extends AbstractPageObject {

    private NavigationBar navigationBar;

    public MainPage(WebDriver driver){
        super(driver);
        this.navigationBar = new NavigationBar(driver);
    }

    public MainPage navigateToMainPage(){
        getDriver().get(Url.HTTP_AUTOMATIONPRACTICE_COM);
        return this;
    }

    public NavigationBar selectSignInLink(){
        return navigationBar;
    }

}
