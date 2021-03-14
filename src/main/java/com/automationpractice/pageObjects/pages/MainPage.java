package com.automationpractice.pageObjects.pages;

import com.automationpractice.pageObjects.components.NavigationBar;
import com.automationpractice.pageObjects.utils.Url;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;

public class MainPage extends AbstractPageObject {

    private NavigationBar navigationBar;

    public MainPage(WebDriver driver) {
        super(driver);
        this.navigationBar = new NavigationBar(driver);
    }

    Logger logger = LogManager.getLogger(MainPage.class);

    public MainPage navigateToMainPage() {
        logger.info("Opening page: " + Url.HTTP_AUTOMATIONPRACTICE_COM);
        driver.get(Url.HTTP_AUTOMATIONPRACTICE_COM);
        logger.trace("Opened page: " + Url.HTTP_AUTOMATIONPRACTICE_COM);
        return this;
    }

    public NavigationBar selectSignInLink() {
        return navigationBar;
    }

}
