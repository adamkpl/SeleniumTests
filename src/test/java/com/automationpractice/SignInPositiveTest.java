package com.automationpractice;

import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.pages.MyAccount;
import com.automationpractice.pageObjects.testdata.TestData;
import com.automationpractice.pageObjects.utils.TakeScreenshotWrapper;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SignInPositiveTest extends BaseTestCase {

    AccountSignInPage accountSignInPage = new AccountSignInPage(driver);
    MyAccount myAccount = new MyAccount(driver);


    @Test
    public void shouldSignInToAccount() {
        // Given
        accountSignInPage
                .navigateToAccountSignInPage()

        // When
                .loginToAccount()
                    .withUsername(TestData.ACCT_EMAIL)
                    .withPassword(TestData.ACCT_PASSWORD_VALID)
                .clickSignInButton();

        // Then
        TakeScreenshotWrapper.takeScreenshot(driver, "shouldSignInToAccount.png");
        assertTrue(myAccount.isWelcomeMessageDisplayed());
    }

}
