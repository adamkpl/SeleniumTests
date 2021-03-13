package com.automationpractice;

import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.pages.MainPage;
import com.automationpractice.pageObjects.pages.MyAccount;
import com.automationpractice.pageObjects.testdata.TestData;
import com.automationpractice.pageObjects.utils.TakeScreenshotWrapper;
import com.automationpractice.pageObjects.utils.Url;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Login to an Account
 * Page Object Pattern with "Fluent API"
 * @since 2020-02-18
 * @author Adam K.
 */

public class LoginTest extends BaseTestCase {

    MainPage mainPage = new MainPage(driver);
    AccountSignInPage accountSignInPage = new AccountSignInPage(driver);
    MyAccount myAccount = new MyAccount(driver);

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
                TakeScreenshotWrapper.takeScreenshot(driver, "LoginSuccess.png");
                assertEquals("URL = myAccount", Url.MY_ACCOUNT, driver.getCurrentUrl());
    }

    @Test
    public void shouldFailToLoginToAnExistingAccountWithInvalidPassword() {
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
                TakeScreenshotWrapper.takeScreenshot(driver, "LoginFailInvalidPwd.png");
                assertNotEquals("URL != myAccount", Url.MY_ACCOUNT, driver.getCurrentUrl());
    }

}