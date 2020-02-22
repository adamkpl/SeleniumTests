package com.automationpractice;

import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.pages.MainPage;
import com.automationpractice.pageObjects.pages.MyAccount;
import com.automationpractice.pageObjects.utils.TakeScreenshotWrapper;
import org.junit.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;

/**
 * Login to an Account
 * Page Object Pattern with "Fluent API"
 * @since Beta 0.1 2020-02-18
 * @author Adam K.
 */

public class LoginTest {

    private static WebDriver driver;

    MainPage mainPage = new MainPage(driver);
    AccountSignInPage accountSignInPage = new AccountSignInPage(driver);
    MyAccount myAccount = new MyAccount(driver);

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Before
    public void setupTest() {
        //Given
        navigateToAccountSignInPage();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void canLoginToAccount() throws IOException {

        //When
        accountSignInPage.
                loginToAccount().
                    withUsername("automationpractice@yopmail.com").
                    withPassword("UnknownP@zzw0rd!").
                clickSignInButton();

        //Then
        myAccount.
                getWelcomeMessage();
                takeScreenshot();

    }

    private void navigateToAccountSignInPage(){
        mainPage.
                navigateToMainPage().
                selectSignInLink().
                clickSignInLink();
    }

    private void takeScreenshot() throws IOException {
        TakeScreenshotWrapper.takeScreenshot(driver,"welcomeBackToYourAccount.png");
    }

}