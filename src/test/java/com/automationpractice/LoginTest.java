package com.automationpractice;

import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.pages.MainPage;
import com.automationpractice.pageObjects.pages.MyAccount;
import com.automationpractice.pageObjects.utils.TakeScreenshotWrapper;
import com.automationpractice.pageObjects.utils.Url;
import org.junit.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void teardown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void shouldLoginToAccount() throws IOException {
        // Given
        mainPage
                .navigateToMainPage()
                .selectSignInLink()
                .clickSignInLink();

        // When
        accountSignInPage
                .loginToAccount()
                    .withUsername("automationpractice@yopmail.com")
                    .withPassword("UnknownP@zzw0rd!")
                .clickSignInButton();

        // Then
        myAccount
                .getWelcomeMessage();
                takeScreenshotLoginSuccess();
                assertEquals("URL = myAccount", Url.MY_ACCOUNT, driver.getCurrentUrl());

    }

    @Test
    public void shouldFailToLoginToAccount() throws IOException {
        // Given
        mainPage
                .navigateToMainPage()
                .selectSignInLink()
                .clickSignInLink();

        // When
        accountSignInPage
                .loginToAccount()
                    .withUsername("automationpractice@yopmail.com")
                    .withPassword("ThisPasswordIsInvalid")
                .clickSignInButton();

        // Then
        myAccount
                .getAuthErrorMessage();
                takeScreenshotLoginFail();
                assertNotEquals("URL != myAccount", Url.MY_ACCOUNT, driver.getCurrentUrl());

    }

    private void takeScreenshotLoginSuccess() throws IOException {
        TakeScreenshotWrapper.takeScreenshot(driver,"LoginSuccess.png");
    }

    private void takeScreenshotLoginFail() throws IOException {
        TakeScreenshotWrapper.takeScreenshot(driver,"LoginFail.png");
    }

}