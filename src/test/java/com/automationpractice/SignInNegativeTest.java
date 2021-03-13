package com.automationpractice;

import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.pages.MyAccount;
import com.automationpractice.pageObjects.testdata.TestData;
import com.automationpractice.pageObjects.utils.TakeScreenshotWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class SignInNegativeTest extends BaseTestCase {

    AccountSignInPage accountSignInPage = new AccountSignInPage(driver);
    MyAccount myAccount = new MyAccount(driver);

    // Parameters
    private String login;
    private String password;

    // Constructor is initialized with one set of parameters every time
    public SignInNegativeTest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    // @Parameters annotation marks this method as parameters provider
    @Parameterized.Parameters(name = "{index}: email: {0}, password: {1}")
    public static Iterable<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {TestData.ACCT_EMAIL_INVALID, TestData.ACCT_PASSWORD_INVALID}
                , {TestData.ACCT_EMAIL_INVALID, TestData.ACCT_PASSWORD_INVALID_LESS_THAN_MINIMUM_REQUIRED}
                , {TestData.ACCT_EMAIL, TestData.ACCT_PASSWORD_INVALID_EMPTY}
                , {TestData.ACCT_EMAIL_INVALID_EMPTY, TestData.ACCT_PASSWORD_VALID}
                , {TestData.ACCT_EMAIL_INVALID_EMPTY, TestData.ACCT_PASSWORD_INVALID_EMPTY}
                , {TestData.ACCT_EMAIL_UPPER_CASE, TestData.ACCT_PASSWORD_VALID_UPPER_CASE}
        });
    }

    @Test
    public void shouldNotSignInToAccount() {
        // Given
        accountSignInPage
                .navigateToAccountSignInPage()

        // When
                .loginToAccount()
                    .withUsername(login)
                    .withPassword(password)
                .clickSignInButton();

        // Then
        TakeScreenshotWrapper.takeScreenshot(driver, "shouldNotSignInToAccount_" + login + "_" + password + ".png");
        assertTrue(myAccount.isAuthorizationErrorMessageDisplayed());
    }

}
