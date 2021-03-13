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
 * Create an account
 * Page Object Pattern with "Fluent API"
 * @since 2020-02-07
 * @author Adam K.
 */

public class CreateAccountTest extends BaseTestCase {

    MainPage mainPage = new MainPage(driver);
    AccountSignInPage accountSignInPage = new AccountSignInPage(driver);
    MyAccount myAccount = new MyAccount(driver);

    @Test
    public void shouldRegisterAccountWithRequiredFieldsFilledOnlyWithValidInputData() {
        // Given
        mainPage
                .navigateToMainPage()
                .selectSignInLink()
                .clickSignInLink();

        // When
        accountSignInPage
                .createAnAccount()
                .setRandomEmailAddress()
                .clickCreateAccountButton()
                    .setRandomFirstName()
                    .setRandomLastName()
                    .setRandomPassword()
                    .setRandomAddress()
                    .setRandomCity()
                    .selectRandomState()
                    .setRandomPostcode()
                    .selectRandomCountry()
                    .setRandomMobilePhoneNumber()
                    .setRandomAddressAlias()
                    .clickRegisterButton();

        // Then
        myAccount
                .getWelcomeMessage();
                takeScreenshotMinimum();
                assertEquals("URL = myAccount", Url.MY_ACCOUNT, driver.getCurrentUrl());

    }

    @Test
    public void shouldRegisterAccountWithAllFieldsFilledWithValidInputData() {
        // Given
        mainPage
                .navigateToMainPage()
                .selectSignInLink()
                .clickSignInLink();

        // When
        accountSignInPage
                .createAnAccount()
                .setRandomEmailAddress()
                .clickCreateAccountButton()
                    .setRandomGender()
                    .setRandomFirstName()
                    .setRandomLastName()
                    .setRandomPassword()
                    .selectRandomDayOfBirth()
                    .selectRandomMonthOfBirth()
                    .selectRandomYearOfBirth()
                    .checkNewsletter()
                    .checkSpecialOffers()
                    .setRandomCompany()
                    .setRandomAddress()
                    .setRandomCity()
                    .selectRandomState()
                    .setRandomPostcode()
                    .selectRandomCountry()
                    .setRandomAdditionalInformation()
                    .setRandomPhoneNumber()
                    .setRandomMobilePhoneNumber()
                    .setRandomAddressAlias()
                    .clickRegisterButton();

        // Then
        myAccount
                .getWelcomeMessage();
                takeScreenshotMaximum();
                assertEquals("URL = myAccount", Url.MY_ACCOUNT, driver.getCurrentUrl());

    }

    @Test
    public void shouldNotRegisterAccountForAnExistingAccount() {
        // Given
        mainPage
                .navigateToMainPage()
                .selectSignInLink()
                .clickSignInLink();

        // When
        accountSignInPage
                .createAnAccount()
                .setEmailAddress(TestData.ACCT_EMAIL)
                .clickCreateAccountButton();

        // Then
        myAccount
                .getAuthErrorMessage();
                takeScreenshotCreateAccountFailAnExistingAccount();
                assertNotEquals("URL != myAccount", Url.MY_ACCOUNT, driver.getCurrentUrl());

    }


    @Test
    public void shouldNotRegisterAccountForAnInvalidEmailAddress() {
        // Given
        mainPage
                .navigateToMainPage()
                .selectSignInLink()
                .clickSignInLink();

        // When
        accountSignInPage
                .createAnAccount()
                .setEmailAddress(TestData.ACCT_EMAIL_INVALID)
                .clickCreateAccountButton();

        // Then
        myAccount
                .getAuthErrorMessage();
                takeScreenshotCreateAccountFailInvalidEmailAddress();
                assertNotEquals("URL != myAccount", Url.MY_ACCOUNT, driver.getCurrentUrl());

    }

    private void takeScreenshotCreateAccountFailInvalidEmailAddress() {
        TakeScreenshotWrapper.takeScreenshot(driver,"CreateAccountFailInvalidEmailAddress.png");
    }

    private void takeScreenshotCreateAccountFailAnExistingAccount() {
        TakeScreenshotWrapper.takeScreenshot(driver,"CreateAccountFailAnExistingAccount.png");
    }

    private void takeScreenshotMinimum() {
        TakeScreenshotWrapper.takeScreenshot(driver,"RegisterAccountMinimum_Success.png");
    }

    private void takeScreenshotMaximum() {
        TakeScreenshotWrapper.takeScreenshot(driver,"RegisterAccountMaximum_Success.png");
    }

}