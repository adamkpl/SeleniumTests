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
public class CreateAccountNegativeTest extends BaseTestCase {

    AccountSignInPage accountSignInPage = new AccountSignInPage(driver);
    MyAccount myAccount = new MyAccount(driver);

    // Parameters
    private String username;

    // Constructor is initialized with one set of parameters every time
    public CreateAccountNegativeTest(String username) {
        this.username = username;
    }

    // @Parameters annotation marks this method as parameters provider
    @Parameterized.Parameters(name = "{index}: username: {0}")
    public static Iterable<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {TestData.ACCT_EMAIL}
                , {TestData.ACCT_EMAIL_UPPER_CASE}
                , {TestData.ACCT_EMAIL_INVALID}
                , {TestData.ACCT_EMAIL_INVALID_EMPTY}
        });
    }

    @Test
    public void shouldNotRegisterAccount() {
        // Given
        accountSignInPage
                .navigateToAccountSignInPage()

        // When
                .createAnAccount()
                    .setEmailAddress(username)
                .clickCreateAccountButton();

        // Then
        TakeScreenshotWrapper.takeScreenshot(driver, "shouldNotRegisterAccount_" + username + "_.png");
        assertTrue(myAccount.isAuthorizationErrorMessageDisplayed());
    }

}