package com.automationpractice;

import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.pages.MainPage;
import com.automationpractice.pageObjects.pages.MyAccount;
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

/**
 * Create an account
 * Page Object Pattern with "Fluent API"
 * @since 2020-02-07
 * @author Adam K.
 */

public class CreateAccountTest {

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

    private void takeScreenshotMinimum() {
        TakeScreenshotWrapper.takeScreenshot(driver,"RegisterAccountMinimum_Success.png");
    }

    private void takeScreenshotMaximum() {
        TakeScreenshotWrapper.takeScreenshot(driver,"RegisterAccountMaximum_Success.png");
    }

}