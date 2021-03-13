package com.automationpractice.pageObjects.components;

import com.automationpractice.pageObjects.pages.AbstractPageObject;
import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.utils.WaitWrapper;
import net.andreinc.mockneat.abstraction.MockUnitString;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
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

    public RegisterAccountForm(WebDriver driver){
        super(driver);
    }

    public RegisterAccountForm setEmailAddress(String emailAddress){
        WaitWrapper.waitForElement(driver, emailAddressField);

        emailAddressField.isEnabled();
        emailAddressField.clear();
        emailAddressField.sendKeys(emailAddress);
        System.out.println("Email: " + emailAddress);

        return this;
    }

    public RegisterAccountForm setRandomEmailAddress(){
        return setEmailAddress(strings().size(15).type(ALPHA_NUMERIC).get().toLowerCase() + "@"
                + strings().size(5).type(ALPHA_NUMERIC).get().toLowerCase() + new Random().nextInt(99999) + ".pl");
    }

    public RegisterAccountForm clickCreateAccountButton(){
        WaitWrapper.waitForElement(driver, createAccountButton);

        createAccountButton.isEnabled();
        createAccountButton.click();

        return this;
    }

    /* Gender radio buttons make the test flaky due to the fact that they load with a delay.
    * In order to address this issue a workaround has been implemented with retryWaitForElement(). */

    public RegisterAccountForm setGenderMale(){
        if (retryWaitForElement(driver, By.id("id_gender1"), 2, 1)) {
            genderMale.click();
            genderMale.isSelected();
            System.out.println("Gender: Male");
        }

        return this;
    }

    public RegisterAccountForm setGenderFemale(){
        if (retryWaitForElement(driver, By.id("id_gender2"), 2, 1)) {
            genderFemale.click();
            genderFemale.isSelected();
            System.out.println("Gender: Female");
        }

        return this;
    }

    public RegisterAccountForm setRandomGender(){
        if ((new Random().nextInt(new WebElement[] {genderMale, genderFemale}.length) == 0)) {
            setGenderMale();
        } else {
            setGenderFemale();
        }

        return this;
    }

    public RegisterAccountForm setFirstName(String aFirstName){
        WaitWrapper.waitForElement(driver, firstName);

        firstName.isEnabled();
        firstName.clear();
        firstName.sendKeys(aFirstName);
        System.out.println("First name: " + aFirstName);

        return this;
    }

    public RegisterAccountForm setLastName(String aLastName){
        WaitWrapper.waitForElement(driver, lastName);

        lastName.isEnabled();
        lastName.clear();
        lastName.sendKeys(aLastName);
        System.out.println("Last name: " + aLastName);

        return this;
    }

    public RegisterAccountForm setRandomFirstName(){
        /* Could be aligned with results of setRandomGender().
        Male (0) or Female (1). */
        String aFirstName;
        if ((new Random().nextInt(new WebElement[] {genderMale, genderFemale}.length) == 0)) {
            aFirstName = names().firstAndMale().get();
        } else {
            aFirstName = names().firstAndFemale().get();
        }

        return setFirstName(aFirstName);
    }

    public RegisterAccountForm setRandomLastName(){
        /* Could be aligned it with the results of setRandomGender() & setRandomFirstName() ONLY if name
        inflexion is applied. E.g in Polish language the last name would not be Kowalski but Kowalska. */

        return setLastName(names().last().get());
    }

    public RegisterAccountForm setPassword(String aPassword){
        WaitWrapper.waitForElement(driver, password);

        password.isEnabled();
        password.clear();
        password.sendKeys(aPassword);
        System.out.println("Password: " + aPassword);

        return this;
    }

    public RegisterAccountForm setRandomPassword(){
        return setPassword(passwords().type(MEDIUM).get());
    }

    public RegisterAccountForm checkNewsletter(){
        WaitWrapper.waitForElement(driver, newsletter);

        newsletter.isEnabled();
        if (!newsletter.isSelected()) {
            newsletter.click();
            newsletter.isSelected();
        }

        return this;
    }

    public RegisterAccountForm checkSpecialOffers(){
        WaitWrapper.waitForElement(driver, newsletterSpecialOffers);

        newsletterSpecialOffers.isEnabled();
        if (!newsletterSpecialOffers.isSelected()) {
            newsletterSpecialOffers.click();
            newsletterSpecialOffers.isSelected();
        }

        return this;
    }

    public RegisterAccountForm selectDayOfBirth(int dayOfBirth) {
        WaitWrapper.waitFluentlyForElement(driver, days);

        dobDay = new Select(driver.findElement(days));
        List<WebElement> selectDay = driver.findElements(dobDaysList);
        if (selectDay.size() > 0) {
            dobDay.selectByIndex(dayOfBirth);
            System.out.println("Day of birth (position on list): " + dayOfBirth + ", value = "
                    + dobDay.getOptions().get(dayOfBirth).getText());

            return this;
        } else {
            throw new ElementNotSelectableException("Unable to select.");
        }
    }

    public RegisterAccountForm selectRandomDayOfBirth() {
        int randDay = new Random().nextInt((driver.findElements(dobDaysList)).size());
        if(randDay == 0) {
            randDay++;
        }

        return selectDayOfBirth(randDay);
    }

    public RegisterAccountForm selectMonthOfBirth(int monthOfBirth){
        WaitWrapper.waitFluentlyForElement(driver, months);

        dobMonth = new Select(driver.findElement(months));
        List<WebElement> selectMonth = driver.findElements(dobMonthList);
        if (selectMonth.size() > 0) {
            dobMonth.selectByIndex(monthOfBirth);
            System.out.println("Month of birth (position on list): " + monthOfBirth + ", value = "
                    + dobMonth.getOptions().get(monthOfBirth).getText());

            return this;
        } else {
            throw new ElementNotSelectableException("Unable to select.");
        }
    }

    public RegisterAccountForm selectRandomMonthOfBirth(){
        int randMonth = new Random().nextInt((driver.findElements(dobMonthList)).size());
        if(randMonth == 0) {
            randMonth++;
        }

        return selectMonthOfBirth(randMonth);
    }

    public RegisterAccountForm selectYearOfBirth(int yearOfBirth){
        WaitWrapper.waitFluentlyForElement(driver, years);

        dobYear = new Select(driver.findElement(years));
        List<WebElement> selectYear = driver.findElements(dobYearList);
        if (selectYear.size() > 0) {
            dobYear.selectByIndex(yearOfBirth);
            System.out.println("Years of birth (position on list): " + yearOfBirth + ", value = "
                    + dobYear.getOptions().get(yearOfBirth).getText());

            return this;
        } else {
            throw new ElementNotSelectableException("Unable to select.");
        }
    }

    public RegisterAccountForm selectRandomYearOfBirth(){
        int randYear = new Random().nextInt((driver.findElements(dobYearList)).size());
        if(randYear == 0) {
            randYear++;
        }

        return selectYearOfBirth(randYear);
    }

    public RegisterAccountForm setAdditionalInformation(String additionalInfo){
        WaitWrapper.waitForElement(driver, additionalInformationForm);

        additionalInformationForm.isEnabled();
        additionalInformationForm.clear();
        additionalInformationForm.sendKeys(additionalInfo);
        System.out.println("Additional Info: " + additionalInfo);

        return this;
    }

    public RegisterAccountForm setRandomAdditionalInformation(){
        return setAdditionalInformation(words().nouns().get());
    }

    public RegisterAccountForm setAddress(String aAddress){
        WaitWrapper.waitForElement(driver, address);

        address.isEnabled();
        address.clear();
        address.sendKeys(aAddress);
        System.out.println("Address: " + aAddress);

        return this;
    }

    public RegisterAccountForm setRandomAddress(){
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

        return setAddress(randomAddress.accumulate(1, "\n").get());
    }

    public RegisterAccountForm setCompany(String aCompany){
        WaitWrapper.waitForElement(driver, company);

        company.isEnabled();
        company.clear();
        company.sendKeys(aCompany);
        System.out.println("Company: " + aCompany);

        return this;
    }

    public RegisterAccountForm setRandomCompany(){
        return setCompany(words().nouns().get());
    }

    public RegisterAccountForm setCity(String aCity){
        WaitWrapper.waitForElement(driver, city);

        city.isEnabled();
        city.clear();
        city.sendKeys(aCity);
        System.out.println("City: " + aCity);

        return this;
    }

    public RegisterAccountForm setRandomCity(){
        return setCity(cities().capitals().get());
    }

    public RegisterAccountForm selectState(int aState){
        WaitWrapper.waitFluentlyForElement(driver, state);

        stateSelect = new Select(driver.findElement(state));
        List<WebElement> selectState = driver.findElements(stateList);
        if(selectState.size() > 0) {
            stateSelect.selectByIndex(aState);
            System.out.println("State (position on list): " + aState + ", value = "
                    + stateSelect.getOptions().get(aState).getText());

            return this;
        } else {
            throw new ElementNotSelectableException("Unable to select.");
        }
    }

    public RegisterAccountForm selectRandomState(){
        int randState = new Random().nextInt((driver.findElements(stateList)).size());
        if(randState == 0) {
            randState++;
        }

        return selectState(randState);
    }

    public RegisterAccountForm setPostcode(String aPostcode){
        /* In real life the post codes contain alphanumeric characters, but here
        the form accepts only 5-digit format value e.g 00000. */
        WaitWrapper.waitForElement(driver, postcode);

        postcode.isEnabled();
        postcode.clear();
        postcode.sendKeys(aPostcode);
        System.out.println("Postcode: " + aPostcode);

        return this;
    }

    public RegisterAccountForm setRandomPostcode(){
        return setPostcode(ints().range(10000,99999).get().toString());
    }

    public RegisterAccountForm selectCountry(int aCountry){
        /* The United States value is automatically selected by default, and is the only option available. With the
        * current implementation, this method will still work when the list expands in the future. */
        WaitWrapper.waitFluentlyForElement(driver, country);

        countrySelect = new Select(driver.findElement(country));
        List<WebElement> selectCountry = driver.findElements(countryList);
        if(selectCountry.size() > 0) {
            countrySelect.selectByIndex(aCountry);
            System.out.println("Country (position on list): " + aCountry + ", value = "
                    + countrySelect.getOptions().get(aCountry).getText());

            return this;
        } else {
            throw new ElementNotSelectableException("Unable to select.");
        }
    }

    public RegisterAccountForm selectRandomCountry(){
        /* The United States value is automatically selected by default, and is the only option available. With the
         * current implementation, this method will still work when the list expands in the future. */
        int randCountry = new Random().nextInt((driver.findElements(countryList)).size());
        if(randCountry == 0) {
            randCountry++;
        }

        return selectCountry(randCountry);
    }

    public RegisterAccountForm setPhoneNumber(String aPhoneHome){
        /* On many websites, phone numbers are typed with non-integer characters such as + or - with
        additional prefix numbers, but here we simply generate a 9-digit number. */
        WaitWrapper.waitForElement(driver, phoneHome);

        phoneHome.isEnabled();
        phoneHome.clear();
        phoneHome.sendKeys(aPhoneHome);
        System.out.println("Home phone number: " + aPhoneHome);

        return this;
    }

    public RegisterAccountForm setRandomPhoneNumber(){
        /* On many websites, phone numbers are typed with non-integer characters such as + or - with
        additional prefix numbers, but here we simply generate a 9-digit number. */

        return setPhoneNumber(ints().range(100000000,999999999).get().toString());
    }

    public RegisterAccountForm setMobilePhoneNumber(String mobilePhoneNumber){
        /* On many websites, phone numbers are typed with non-integer characters such as + or - with
        additional prefix numbers, but here we simply generate a 9-digit number. */
        WaitWrapper.waitForElement(driver, phoneMobile);

        phoneMobile.isEnabled();
        phoneMobile.clear();
        phoneMobile.sendKeys(mobilePhoneNumber);
        System.out.println("Mobile phone number: " + mobilePhoneNumber);

        return this;
    }

    public RegisterAccountForm setRandomMobilePhoneNumber(){
        /* On many websites, phone numbers are typed with non-integer characters such as + or - with
        additional prefix numbers, but here we simply generate a 9-digit number. */

        return setMobilePhoneNumber(ints().range(100000000,999999999).get().toString());
    }

    public RegisterAccountForm setAddressAlias(String aAddressAlias){
        WaitWrapper.waitForElement(driver, addressAlias);

        addressAlias.isEnabled();
        addressAlias.clear();
        addressAlias.sendKeys(aAddressAlias);

        return this;
    }

    public RegisterAccountForm setRandomAddressAlias(){
        return setAddressAlias(words().nouns().get());
    }

    public RegisterAccountForm clickRegisterButton(){
        WaitWrapper.waitForElement(driver,registerButton);

        registerButton.isEnabled();
        registerButton.click();

        return this;
    }

    public AccountSignInPage registerAccount(){
        return new AccountSignInPage(driver);
    }
}