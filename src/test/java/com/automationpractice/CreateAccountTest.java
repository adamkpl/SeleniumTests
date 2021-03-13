package com.automationpractice;

import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.pages.MainPage;
import com.automationpractice.pageObjects.pages.MyAccount;
import com.automationpractice.pageObjects.testdata.TestData;
import com.automationpractice.pageObjects.utils.TakeScreenshotWrapper;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

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
        TakeScreenshotWrapper.takeScreenshot(driver, "shouldRegisterAccountWithRequiredFieldsFilledOnlyWithValidInputData.png");
        assertTrue(myAccount.isWelcomeMessageDisplayed());
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
        TakeScreenshotWrapper.takeScreenshot(driver, "shouldRegisterAccountWithAllFieldsFilledWithValidInputData.png");
        assertTrue(myAccount.isWelcomeMessageDisplayed());
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
        TakeScreenshotWrapper.takeScreenshot(driver, "shouldNotRegisterAccountForAnExistingAccount.png");
        assertTrue(myAccount.isAuthorizationErrorMessageDisplayed());
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
        TakeScreenshotWrapper.takeScreenshot(driver, "shouldNotRegisterAccountForAnInvalidEmailAddress.png");
        assertTrue(myAccount.isAuthorizationErrorMessageDisplayed());
    }

}