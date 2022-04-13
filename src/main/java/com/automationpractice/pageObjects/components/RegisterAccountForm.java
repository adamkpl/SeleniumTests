package com.automationpractice.pageObjects.components;

import com.automationpractice.pageObjects.pages.AbstractPageObject;
import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.utils.WaitWrapper;
import net.andreinc.mockneat.abstraction.MockUnitString;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

import static com.automationpractice.pageObjects.utils.WaitWrapper.retryWaitForElement;
import static net.andreinc.mockneat.types.enums.PassStrengthType.MEDIUM;
import static net.andreinc.mockneat.types.enums.StringFormatType.CAPITALIZED;
import static net.andreinc.mockneat.types.enums.StringType.ALPHA_NUMERIC;
import static net.andreinc.mockneat.unit.address.Cities.cities;
import static net.andreinc.mockneat.unit.objects.Probabilities.probabilities;
import static net.andreinc.mockneat.unit.text.Formatter.fmt;
import static net.andreinc.mockneat.unit.text.Strings.strings;
import static net.andreinc.mockneat.unit.text.Words.words;
import static net.andreinc.mockneat.unit.types.Ints.ints;
import static net.andreinc.mockneat.unit.user.Emails.emails;
import static net.andreinc.mockneat.unit.user.Names.names;
import static net.andreinc.mockneat.unit.user.Passwords.passwords;

public class RegisterAccountForm extends AbstractPageObject {

    // YOUR PERSONAL INFORMATION
    // required
    @FindBy(id = "email_create")
    private WebElement emailAddressField;
    @FindBy(id = "customer_firstname")
    private WebElement firstName;
    @FindBy(id = "customer_lastname")
    private WebElement lastName;
    @FindBy(id = "passwd")
    private WebElement password;
    // optional
    @FindBy(id = "id_gender1")
    private WebElement genderMale;
    @FindBy(id = "id_gender2")
    private WebElement genderFemale;
    private By days = By.id("days"); // if xpath then Exception: Element should have been "select" but was "option"
    private By dobDaysList = By.xpath("//SELECT[@id='days']/self::SELECT/option[@value>'0']"); // '-' option excluded
    private Select dobDay; // initialized as local var
    private By months = By.id("months");
    private By dobMonthList = By.xpath("//SELECT[@id='months']/self::SELECT/option[@value>'0']"); // '-' option excluded
    private Select dobMonth; // initialized as local var
    private By years = By.id("years");
    private By dobYearList = By.xpath("//SELECT[@id='years']/self::SELECT/option[@value>'0']"); // '-' option excluded
    private Select dobYear; // initialized as local var

    // YOUR ADDRESS
    // required
    @FindBy(id = "company")
    private WebElement company;
    @FindBy(id = "address1")
    private WebElement address;
    @FindBy(id = "city")
    private WebElement city;
    private By state = By.id("id_state"); // if xpath then Exception: Element should have been "select" but was "option"
    private By stateList = By.xpath("//SELECT[@id='id_state']/self::SELECT/option[@value>'0']"); // '-' option excluded
    private Select stateSelect; // initialized as local var
    @FindBy(id = "postcode")
    private WebElement postcode;
    private By country = By.id("id_country"); // if xpath then Exception: Element should have been "select" but was "option"
    private By countryList = By.xpath("//SELECT[@id='id_country']/self::SELECT/option[@value>'0']"); // '-' option excluded
    private Select countrySelect; // initialized as local var
    @FindBy(id = "other")
    private WebElement additionalInformationForm;
    @FindBy(id = "phone")
    private WebElement phoneHome;
    @FindBy(id = "phone_mobile")
    private WebElement phoneMobile;
    @FindBy(id = "alias")
    private WebElement addressAlias;

    // OTHER
    @FindBy(id = "uniform-newsletter")
    private WebElement newsletter;
    @FindBy(id = "uniform-optin")
    private WebElement newsletterSpecialOffers;
    @FindBy(name = "SubmitCreate")
    private WebElement createAccountButton;
    @FindBy(id = "submitAccount")
    private WebElement registerButton;

    // ERRORS
    @FindBy(id = "create_account_error")
    private WebElement createAccountError;

    public RegisterAccountForm(WebDriver driver) {
        super(driver);
    }

    Logger logger = Logger.getLogger(RegisterAccountForm.class);

    public RegisterAccountForm setEmailAddress(String emailAddress) {
        WaitWrapper.waitForElement(driver, emailAddressField);
        logger.info("Checking if " + emailAddressField + " is enabled.");
        emailAddressField.isEnabled();
        logger.info("Element " + emailAddressField + " is enabled.");
        logger.info("Clearing field " + emailAddressField);
        emailAddressField.clear();
        logger.info("Field " + emailAddressField + " cleared");
        logger.info("Typing: " + emailAddress);
        emailAddressField.sendKeys(emailAddress);
        logger.trace("Typed: " + emailAddress);
        //System.out.println("Email: " + emailAddress);
        return this;
    }

    public RegisterAccountForm setRandomEmailAddress() {
        logger.info("Generating random email address.");
        String randomEmail = strings().size(15).type(ALPHA_NUMERIC).get().toLowerCase() + "@"
                + strings().size(5).type(ALPHA_NUMERIC).get().toLowerCase() + new Random().nextInt(99999) + ".pl";
        logger.trace("Generated random email address: " + randomEmail);
        return setEmailAddress(randomEmail);
    }

    public RegisterAccountForm clickCreateAccountButton() {
        WaitWrapper.waitForElement(driver, createAccountButton);
        logger.info("Checking if " + createAccountButton + " is enabled.");
        createAccountButton.isEnabled();
        logger.info("Element " + createAccountButton + " is enabled.");
        logger.info("Clicking " + createAccountButton);
        createAccountButton.click();
        logger.trace("Clicked " + createAccountButton);
        return this;
    }

    /* Gender radio buttons make the test flaky due to the fact that they load with a delay.
     * In order to address this issue a workaround has been implemented with retryWaitForElement(). */

    public RegisterAccountForm setGenderMale() {
        if (retryWaitForElement(driver, By.id("id_gender1"), 2, 1)) {
            logger.info("Clicking " + genderMale);
            genderMale.click();
            logger.info("Clicked " + genderMale);
            logger.info("Checking if " + genderMale + " is selected");
            genderMale.isSelected();
            logger.trace("Selected " + genderMale);
            //System.out.println("Gender: Male");
        }
        return this;
    }

    public RegisterAccountForm setGenderFemale() {
        if (retryWaitForElement(driver, By.id("id_gender2"), 2, 1)) {
            logger.info("Clicking " + genderFemale);
            genderFemale.click();
            logger.info("Clicked " + genderFemale);
            logger.trace("Checking if " + genderFemale + " is selected");
            genderFemale.isSelected();
            logger.info("Selected " + genderFemale);
            //System.out.println("Gender: Female");
        }
        return this;
    }

    public RegisterAccountForm setRandomGender() {
        if ((new Random().nextInt(new WebElement[]{genderMale, genderFemale}.length) == 0)) {
            setGenderMale();
        } else {
            setGenderFemale();
        }
        return this;
    }

    public RegisterAccountForm setFirstName(String aFirstName) {
        WaitWrapper.waitForElement(driver, firstName);
        logger.info("Checking if " + firstName + " is enabled.");
        firstName.isEnabled();
        logger.info("Element " + firstName + " is enabled.");
        logger.info("Clearing field " + firstName);
        firstName.clear();
        logger.info("Field " + firstName + " cleared");
        logger.info("Typing: " + aFirstName);
        firstName.sendKeys(aFirstName);
        logger.trace("Typed: " + aFirstName);
        //System.out.println("First name: " + aFirstName);
        return this;
    }

    public RegisterAccountForm setLastName(String aLastName) {
        WaitWrapper.waitForElement(driver, lastName);
        logger.info("Checking if " + lastName + " is enabled.");
        lastName.isEnabled();
        logger.info("Element " + lastName + " is enabled.");
        logger.info("Clearing field " + lastName);
        lastName.clear();
        logger.info("Field " + lastName + " cleared");
        logger.info("Typing: " + aLastName);
        lastName.sendKeys(aLastName);
        logger.trace("Typed: " + aLastName);
        //System.out.println("Last name: " + aLastName);
        return this;
    }

    public RegisterAccountForm setRandomFirstName() {
        /* Could be aligned with results of setRandomGender().
        Male (0) or Female (1). */
        String aFirstName;
        if ((new Random().nextInt(new WebElement[]{genderMale, genderFemale}.length) == 0)) {
            logger.info("Generating random first male name.");
            aFirstName = names().firstAndMale().get();
            logger.info("Generated random first male name: " + aFirstName);
        } else {
            logger.info("Generating random first female name.");
            aFirstName = names().firstAndFemale().get();
            logger.info("Generated random first female name: " + aFirstName);
        }
        return setFirstName(aFirstName);
    }

    public RegisterAccountForm setRandomLastName() {
        /* Could be aligned it with the results of setRandomGender() & setRandomFirstName() ONLY if name
        inflexion is applied. E.g in Polish language the last name would not be Kowalski but Kowalska. */
        logger.info("Generating last name.");
        String lastName = names().last().get();
        logger.info("Generated last name: " + lastName);
        return setLastName(lastName);
    }

    public RegisterAccountForm setPassword(String aPassword) {
        WaitWrapper.waitForElement(driver, password);
        logger.info("Checking if " + password + " is enabled.");
        password.isEnabled();
        logger.info("Element " + password + " is enabled.");
        logger.info("Clearing field " + password);
        password.clear();
        logger.info("Field " + password + " cleared");
        logger.warn("DEMO-only purposes. In real-life scenario plain-text password logging is NOT acceptable.");
        logger.info("Typing: " + aPassword);
        password.sendKeys(aPassword);
        logger.trace("Typed: " + aPassword);
        //System.out.println("Password: " + aPassword);
        return this;
    }

    public RegisterAccountForm setRandomPassword() {
        logger.info("Generating random password.");
        String randomPassword = passwords().type(MEDIUM).get();
        logger.trace("Generated random password: " + randomPassword);
        return setPassword(randomPassword);
    }

    public RegisterAccountForm checkNewsletter() {
        WaitWrapper.waitForElement(driver, newsletter);
        logger.info("Checking if " + newsletter + " is enabled.");
        newsletter.isEnabled();
        logger.info("Element " + newsletter + " is enabled.");
        logger.info("Checking if " + newsletter + " is selected.");
        if (!newsletter.isSelected()) {
            logger.info("Element " + newsletter + " is not selected.");
            logger.info("Clicking " + newsletter);
            newsletter.click();
            logger.info("Clicked " + newsletter);
            logger.info("Checking if " + newsletter + " is selected.");
            newsletter.isSelected();
            logger.info("Element " + newsletter + " is selected.");
        }
        return this;
    }

    public RegisterAccountForm checkSpecialOffers() {
        WaitWrapper.waitForElement(driver, newsletterSpecialOffers);
        logger.info("Checking if " + newsletterSpecialOffers + " is enabled.");
        newsletterSpecialOffers.isEnabled();
        logger.info("Element " + newsletterSpecialOffers + " is enabled.");
        logger.info("Checking if " + newsletterSpecialOffers + " is selected.");
        if (!newsletterSpecialOffers.isSelected()) {
            logger.info("Element " + newsletterSpecialOffers + " is not selected.");
            logger.info("Clicking " + newsletterSpecialOffers);
            newsletterSpecialOffers.click();
            logger.info("Clicked " + newsletterSpecialOffers);
            logger.info("Checking if " + newsletterSpecialOffers + " is selected.");
            newsletterSpecialOffers.isSelected();
            logger.info("Element " + newsletterSpecialOffers + " is selected.");
        }
        return this;
    }

    public RegisterAccountForm selectDayOfBirth(int dayOfBirth) {
        WaitWrapper.waitFluentlyForElement(driver, days);
        dobDay = new Select(driver.findElement(days));
        logger.info("Found a dropdown list element: " + days);
        logger.info("Looking for a dropdown list & create a List<WebElement>: " + dobDaysList);
        List<WebElement> selectDay = driver.findElements(dobDaysList);
        logger.info("Found for a dropdown list & created a List<WebElement>.");
        if (selectDay.size() > 0) {
            logger.info("Selecting day from a dropdown list: " + dayOfBirth);
            dobDay.selectByIndex(dayOfBirth);
            logger.info("Selected Day of birth (position on a dropdown list): " + dayOfBirth + ", value = "
                    + dobDay.getOptions().get(dayOfBirth).getText());
            /*System.out.println("Day of birth (position on list): " + dayOfBirth + ", value = "
                    + dobDay.getOptions().get(dayOfBirth).getText());*/
            return this;
        } else {
            logger.error("Could not select an option from a Day of birth dropdown menu.");
            throw new ElementNotInteractableException("Unable to select.");
        }
    }

    public RegisterAccountForm selectRandomDayOfBirth() {
        logger.info("Calculating size of a dropdown list of day of birth");
        int dayOfBirthListSize = driver.findElements(dobDaysList).size();
        logger.info("Calculated size of a dropdown list of day of birth: " + dayOfBirthListSize);
        logger.info("Generating random day of birth.");
        int randDay = new Random().nextInt(dayOfBirthListSize);
        if (randDay == 0) {
            randDay++;
        }
        logger.trace("Generated random day of birth: " + randDay);
        return selectDayOfBirth(randDay);
    }

    public RegisterAccountForm selectMonthOfBirth(int monthOfBirth) {
        WaitWrapper.waitFluentlyForElement(driver, months);
        dobMonth = new Select(driver.findElement(months));
        logger.info("Found a dropdown list element: " + months);
        logger.info("Looking for a dropdown list & create a List<WebElement>: " + dobMonthList);
        List<WebElement> selectMonth = driver.findElements(dobMonthList);
        if (selectMonth.size() > 0) {
            logger.info("Selecting month from a dropdown list: " + monthOfBirth);
            dobMonth.selectByIndex(monthOfBirth);
            logger.info("Selected month of birth (position on a dropdown list): " + monthOfBirth + ", value = "
                    + dobDay.getOptions().get(monthOfBirth).getText());
            /*System.out.println("Month of birth (position on list): " + monthOfBirth + ", value = "
                    + dobMonth.getOptions().get(monthOfBirth).getText());*/
            return this;
        } else {
            logger.error("Could not select an option from a month of birth dropdown menu.");
            throw new ElementNotInteractableException("Unable to select.");
        }
    }

    public RegisterAccountForm selectRandomMonthOfBirth() {
        logger.info("Calculating size of a dropdown list of day of month");
        int monthOfBirthListSize = driver.findElements(dobMonthList).size();
        logger.info("Calculated size of a dropdown list of month of birth: " + monthOfBirthListSize);
        logger.info("Generating random month of birth.");
        int randMonth = new Random().nextInt(monthOfBirthListSize);
        if (randMonth == 0) {
            randMonth++;
        }
        logger.trace("Generated random month of birth: " + randMonth);
        return selectMonthOfBirth(randMonth);
    }

    public RegisterAccountForm selectYearOfBirth(int yearOfBirth) {
        WaitWrapper.waitFluentlyForElement(driver, years);
        dobYear = new Select(driver.findElement(years));
        logger.info("Found a dropdown list element: " + years);
        logger.info("Looking for a dropdown list & create a List<WebElement>: " + dobYearList);
        List<WebElement> selectYear = driver.findElements(dobYearList);
        logger.info("Found for a dropdown list & created a List<WebElement>");
        if (selectYear.size() > 0) {
            logger.info("Selecting day from a dropdown list: " + yearOfBirth);
            dobYear.selectByIndex(yearOfBirth);
            logger.info("Selected year of birth (position on list): " + yearOfBirth + ", value = "
                    + dobYear.getOptions().get(yearOfBirth).getText());
/*            System.out.println("Years of birth (position on list): " + yearOfBirth + ", value = "
                    + dobYear.getOptions().get(yearOfBirth).getText());*/
            return this;
        } else {
            logger.error("Could not select an option from a year of birth dropdown menu.");
            throw new ElementNotInteractableException("Unable to select.");
        }
    }

    public RegisterAccountForm selectRandomYearOfBirth() {
        logger.info("Calculating size of a dropdown list of year of birth");
        int yearOfBirthListSize = driver.findElements(dobYearList).size();
        logger.info("Calculated size of a dropdown list of year of birth: " + yearOfBirthListSize);
        logger.info("Generating random year of birth.");
        int randYear = new Random().nextInt(yearOfBirthListSize);
        if (randYear == 0) {
            randYear++;
        }
        logger.trace("Generated random year of birth: " + randYear);
        return selectYearOfBirth(randYear);
    }

    public RegisterAccountForm setAdditionalInformation(String additionalInfo) {
        WaitWrapper.waitForElement(driver, additionalInformationForm);
        logger.info("Checking if " + additionalInformationForm + " is enabled.");
        additionalInformationForm.isEnabled();
        logger.info("Element " + additionalInformationForm + " is enabled.");
        logger.info("Clearing field " + additionalInformationForm);
        additionalInformationForm.clear();
        logger.info("Field " + additionalInformationForm + " cleared");
        logger.info("Typing: " + additionalInformationForm);
        additionalInformationForm.sendKeys(additionalInfo);
        logger.trace("Typed: " + additionalInfo);
        //System.out.println("Additional Info: " + additionalInfo);
        return this;
    }

    public RegisterAccountForm setRandomAdditionalInformation() {
        logger.info("Generating random additional information.");
        String randomAdditionalInformation = words().nouns().get();
        logger.trace("Generated random additional information: " + randomAdditionalInformation);
        return setAdditionalInformation(randomAdditionalInformation);
    }

    public RegisterAccountForm setAddress(String aAddress) {
        WaitWrapper.waitForElement(driver, address);
        logger.info("Checking if " + address + " is enabled.");
        address.isEnabled();
        logger.info("Element " + address + " is enabled.");
        logger.info("Clearing field " + address);
        address.clear();
        logger.info("Field " + address + " cleared");
        logger.info("Typing: " + aAddress);
        address.sendKeys(aAddress);
        logger.trace("Typed: " + aAddress);
        //System.out.println("Address: " + aAddress);
        return this;
    }

    public RegisterAccountForm setRandomAddress() {
        logger.info("Generating random address.");
        MockUnitString randomAddress = fmt("#{adj}#{noun} #{suffix} #{nr}")
                .param("adj", probabilities(String.class)
                        .add(0.25, words()
                                .adjectives()
                                .format(CAPITALIZED)
                                .append(" ")
                        )
                        .add(0.75, "")
                        .mapToString()
                )
                .param("noun", words().nouns().format(CAPITALIZED))
                .param("suffix", probabilities(String.class)
                        .add(0.25, "Lane")
                        .add(0.25, "Blvd.")
                        .add(0.50, "Street")
                        .mapToString())
                .param("nr", ints().range(1, 600));
        String randomFinalAddress = randomAddress.accumulate(1, "\n").get();
        logger.trace("Generated random address: " + randomFinalAddress);
        return setAddress(randomFinalAddress);
    }

    public RegisterAccountForm setCompany(String aCompany) {
        WaitWrapper.waitForElement(driver, company);
        logger.info("Checking if " + company + " is enabled.");
        company.isEnabled();
        logger.info("Element " + company + " is enabled.");
        logger.info("Clearing field " + company);
        company.clear();
        logger.info("Field " + company + " cleared");
        logger.info("Typing: " + aCompany);
        company.sendKeys(aCompany);
        logger.trace("Typed: " + aCompany);
        //System.out.println("Company: " + aCompany);
        return this;
    }

    public RegisterAccountForm setRandomCompany() {
        logger.info("Generating company name.");
        String company = words().nouns().get();
        logger.info("Generated company name: " + company);
        return setCompany(company);
    }

    public RegisterAccountForm setCity(String aCity) {
        WaitWrapper.waitForElement(driver, city);
        logger.info("Checking if " + city + " is enabled.");
        city.isEnabled();
        logger.info("Element " + city + " is enabled.");
        logger.info("Clearing field " + city);
        city.clear();
        logger.info("Field " + city + " cleared");
        logger.info("Typing: " + aCity);
        city.sendKeys(aCity);
        logger.trace("Typed: " + aCity);
        //System.out.println("City: " + aCity);
        return this;
    }

    public RegisterAccountForm setRandomCity() {
        logger.info("Generating random city.");
        String randomCity = cities().capitals().get();
        logger.trace("Generated random city: " + randomCity);
        return setCity(randomCity);
    }

    public RegisterAccountForm selectState(int aState) {
        WaitWrapper.waitFluentlyForElement(driver, state);
        stateSelect = new Select(driver.findElement(state));
        logger.info("Found a dropdown list element: " + state);
        logger.info("Looking for a dropdown list & create a List<WebElement>: " + stateList);
        List<WebElement> selectState = driver.findElements(stateList);
        logger.info("Found for a dropdown list & created a List<WebElement>");
        if (selectState.size() > 0) {
            logger.info("Selecting state from a dropdown list: " + aState);
            stateSelect.selectByIndex(aState);
            logger.info("Selected state (position on list): " + aState + ", value = "
                    + stateSelect.getOptions().get(aState).getText());
            /*System.out.println("State (position on list): " + aState + ", value = "
                    + stateSelect.getOptions().get(aState).getText());*/
            return this;
        } else {
            logger.error("Could not select an option from a state of birth dropdown menu.");
            throw new ElementNotInteractableException("Unable to select.");
        }
    }

    public RegisterAccountForm selectRandomState() {
        logger.info("Calculating size of a dropdown list of states");
        int statesListSize = driver.findElements(stateList).size();
        logger.info("Calculated size of a dropdown list of states: " + statesListSize);
        logger.info("Generating random state.");
        int randState = new Random().nextInt(statesListSize);
        if (randState == 0) {
            randState++;
        }
        logger.trace("Generated random month of birth: " + randState);
        return selectState(randState);
    }

    public RegisterAccountForm setPostcode(String aPostcode) {
        /* In real life the post codes contain alphanumeric characters, but here
        the form accepts only 5-digit format value e.g 00000. */
        WaitWrapper.waitForElement(driver, postcode);
        logger.info("Checking if " + postcode + " is enabled.");
        postcode.isEnabled();
        logger.info("Element " + postcode + " is enabled.");
        logger.info("Clearing field " + postcode);
        postcode.clear();
        logger.info("Field " + postcode + " cleared");
        logger.info("Typing: " + postcode);
        postcode.sendKeys(aPostcode);
        logger.trace("Typed: " + postcode);
        //System.out.println("Postcode: " + aPostcode);
        return this;
    }

    public RegisterAccountForm setRandomPostcode() {
        logger.info("Generating random postcode.");
        String randomPostcode = ints().range(10000, 99999).get().toString();
        logger.trace("Generated random postcode: " + randomPostcode);
        return setPostcode(randomPostcode);
    }

    public RegisterAccountForm selectCountry(int aCountry) {
        /* The United States value is automatically selected by default, and is the only option available. With the
         * current implementation, this method will still work when the list expands in the future. */
        WaitWrapper.waitFluentlyForElement(driver, country);
        countrySelect = new Select(driver.findElement(country));
        logger.info("Found a dropdown list element: " + country);
        logger.info("Looking for a dropdown list & create a List<WebElement>: " + countryList);
        List<WebElement> selectCountry = driver.findElements(countryList);
        logger.info("Found for a dropdown list & created a List<WebElement>");
        if (selectCountry.size() > 0) {
            logger.info("Selecting country from a dropdown list: " + aCountry);
            countrySelect.selectByIndex(aCountry);
            logger.info("Selected country (position on list): " + aCountry + ", value = "
                    + countrySelect.getOptions().get(aCountry).getText());
            /*System.out.println("Country (position on list): " + aCountry + ", value = "
                    + countrySelect.getOptions().get(aCountry).getText());*/
            return this;
        } else {
            logger.error("Could not select an option from a country dropdown menu.");
            throw new ElementNotInteractableException("Unable to select.");
        }
    }

    public RegisterAccountForm selectRandomCountry() {
        /* The United States value is automatically selected by default, and is the only option available. With the
         * current implementation, this method will still work when the list expands in the future. */
        logger.info("Calculating size of a dropdown list of countries");
        int countryListSize = driver.findElements(countryList).size();
        logger.info("Calculated size of a dropdown list of countries: " + countryListSize);
        logger.info("Generating random country.");
        int randCountry = new Random().nextInt(countryListSize);
        if (randCountry == 0) {
            randCountry++;
        }
        logger.trace("Generated random country: " + randCountry);
        return selectCountry(randCountry);
    }

    public RegisterAccountForm setPhoneNumber(String aPhoneHome) {
        /* On many websites, phone numbers are Typed: with non-integer characters such as + or - with
        additional prefix numbers, but here we simply generate a 9-digit number. */
        WaitWrapper.waitForElement(driver, phoneHome);
        logger.info("Checking if " + phoneHome + " is enabled.");
        phoneHome.isEnabled();
        logger.info("Element " + phoneHome + " is enabled.");
        logger.info("Clearing field " + phoneHome);
        phoneHome.clear();
        logger.info("Field " + phoneHome + " cleared");
        logger.info("Typing: " + aPhoneHome);
        phoneHome.sendKeys(aPhoneHome);
        logger.trace("Typed: " + aPhoneHome);
        //System.out.println("Home phone number: " + aPhoneHome);
        return this;
    }

    public RegisterAccountForm setRandomPhoneNumber() {
        /* On many websites, phone numbers are Typed: with non-integer characters such as + or - with
        additional prefix numbers, but here we simply generate a 9-digit number. */
        logger.info("Generating random phone number.");
        String randomPhoneNumber = ints().range(100000000, 999999999).get().toString();
        logger.trace("Generated random phone number: " + randomPhoneNumber);
        return setPhoneNumber(randomPhoneNumber);
    }

    public RegisterAccountForm setMobilePhoneNumber(String mobilePhoneNumber) {
        /* On many websites, phone numbers are Typed: with non-integer characters such as + or - with
        additional prefix numbers, but here we simply generate a 9-digit number. */
        WaitWrapper.waitForElement(driver, phoneMobile);
        logger.info("Checking if " + phoneMobile + " is enabled.");
        phoneMobile.isEnabled();
        logger.info("Element " + phoneMobile + " is enabled.");
        logger.info("Clearing field " + phoneMobile);
        phoneMobile.clear();
        logger.info("Field " + phoneMobile + " cleared");
        logger.info("Typing: " + mobilePhoneNumber);
        phoneMobile.sendKeys(mobilePhoneNumber);
        logger.trace("Typed: " + mobilePhoneNumber);
        //System.out.println("Mobile phone number: " + mobilePhoneNumber);
        return this;
    }

    public RegisterAccountForm setRandomMobilePhoneNumber() {
        /* On many websites, phone numbers are Typed: with non-integer characters such as + or - with
        additional prefix numbers, but here we simply generate a 9-digit number. */
        logger.info("Generating random mobile phone number.");
        String randomMobilePhoneNumber = ints().range(100000000, 999999999).get().toString();
        logger.trace("Generated random mobile phone number: " + randomMobilePhoneNumber);
        return setMobilePhoneNumber(randomMobilePhoneNumber);
    }

    public RegisterAccountForm setAddressAlias(String aAddressAlias) {
        WaitWrapper.waitForElement(driver, addressAlias);
        logger.info("Checking if " + addressAlias + " is enabled.");
        addressAlias.isEnabled();
        logger.info("Element " + addressAlias + " is enabled.");
        logger.info("Clearing field " + addressAlias);
        addressAlias.clear();
        logger.info("Field " + addressAlias + " cleared");
        logger.info("Typing: " + aAddressAlias);
        addressAlias.sendKeys(aAddressAlias);
        logger.trace("Typed: " + aAddressAlias);
        return this;
    }

    public RegisterAccountForm setRandomAddressAlias() {
        logger.info("Generating random address alias.");
        String randomAddressAlias = words().nouns().get();
        logger.trace("Generated random address alias: " + randomAddressAlias);
        return setAddressAlias(randomAddressAlias);
    }

    public RegisterAccountForm clickRegisterButton() {
        WaitWrapper.waitForElement(driver, registerButton);
        logger.info("Checking if " + registerButton + " is enabled.");
        registerButton.isEnabled();
        logger.info("Element " + registerButton + " is enabled.");
        logger.info("Clicking " + registerButton);
        registerButton.click();
        logger.trace("Clicked " + registerButton);
        return this;
    }

}