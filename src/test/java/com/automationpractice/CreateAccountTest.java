package com.automationpractice;

import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.pages.MainPage;
import com.automationpractice.pageObjects.pages.MyAccount;
import com.automationpractice.pageObjects.utils.TakeScreenshotWrapper;
import com.automationpractice.pageObjects.utils.Url;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Create an account
 * Page Object Pattern with "Fluent API"
 * @since 2020-02-07
 * @author Adam K.
 */

public class CreateAccountTest extends BaseTestCase {

    MainPage mainPage = new MainPage(getDriver());
    AccountSignInPage accountSignInPage = new AccountSignInPage(getDriver());
    MyAccount myAccount = new MyAccount(getDriver());

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
                assertEquals("URL = myAccount", Url.MY_ACCOUNT, getDriver().getCurrentUrl());

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
                assertEquals("URL = myAccount", Url.MY_ACCOUNT, getDriver().getCurrentUrl());

    }

    private void takeScreenshotMinimum() {
        TakeScreenshotWrapper.takeScreenshot(getDriver(),"RegisterAccountMinimum_Success.png");
    }

    private void takeScreenshotMaximum() {
        TakeScreenshotWrapper.takeScreenshot(getDriver(),"RegisterAccountMaximum_Success.png");
    }

}