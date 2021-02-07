package com.automationpractice;

import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.pages.MainPage;
import com.automationpractice.pageObjects.pages.MyAccount;
import com.automationpractice.pageObjects.testdata.TestData;
import com.automationpractice.pageObjects.utils.TakeScreenshotWrapper;
import com.automationpractice.pageObjects.utils.Url;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Login to an Account
 * Page Object Pattern with "Fluent API"
 * @since 2020-02-18
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
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public static void teardown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void shouldLoginToAccount() {
        // Given
        mainPage
                .navigateToMainPage()
                .selectSignInLink()
                .clickSignInLink();

        // When
        accountSignInPage
                .loginToAccount()
                    .withUsername(TestData.ACCT_EMAIL)
                    .withPassword(TestData.ACCT_PASSWORD_VALID)
                .clickSignInButton();

        // Then
        myAccount
                .getWelcomeMessage();
                takeScreenshotLoginSuccess();
                assertEquals("URL = myAccount", Url.MY_ACCOUNT, driver.getCurrentUrl());

    }

    @Test
    public void shouldFailToLoginToAccount() {
        // Given
        mainPage
                .navigateToMainPage()
                .selectSignInLink()
                .clickSignInLink();

        // When
        accountSignInPage
                .loginToAccount()
                    .withUsername(TestData.ACCT_EMAIL)
                    .withPassword(TestData.ACCT_PASSWORD_INVALID)
                .clickSignInButton();

        // Then
        myAccount
                .getAuthErrorMessage();
                takeScreenshotLoginFail();
                assertNotEquals("URL != myAccount", Url.MY_ACCOUNT, driver.getCurrentUrl());

    }

    private void takeScreenshotLoginSuccess() {
        TakeScreenshotWrapper.takeScreenshot(driver,"LoginSuccess.png");
    }

    private void takeScreenshotLoginFail() {
        TakeScreenshotWrapper.takeScreenshot(driver,"LoginFail.png");
    }

}