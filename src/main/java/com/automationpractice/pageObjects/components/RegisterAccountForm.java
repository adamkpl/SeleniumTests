package com.automationpractice.pageObjects.components;

import com.automationpractice.pageObjects.pages.AbstractPageObject;
import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.utils.WaitWrapper;

import static com.automationpractice.pageObjects.utils.WaitWrapper.retryWaitForElement;
import static net.andreinc.mockneat.types.enums.PassStrengthType.MEDIUM;
import static net.andreinc.mockneat.unit.address.Cities.cities;
import static net.andreinc.mockneat.unit.text.Words.words;
import static net.andreinc.mockneat.unit.user.Emails.emails;
import static net.andreinc.mockneat.unit.user.Names.names;
import static net.andreinc.mockneat.unit.user.Passwords.passwords;
import static net.andreinc.mockneat.unit.types.Ints.ints;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

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
    private WebElement gender_male;
    @FindBy(id = "id_gender2")
    private WebElement gender_female;
    @FindBy(id = "days")
    private Select dobDay; // local var
    @FindBy(id = "months")
    private Select dobMonth; // local var
    @FindBy(id = "years")
    private Select dobYear; // local var

    // YOUR ADDRESS
    // required
    @FindBy(id = "company")
    private WebElement company;
    @FindBy(id = "address1")
    private WebElement address;
    @FindBy(id = "city")
    private WebElement city;
    @FindBy(id = "id_state")
    private Select state; // local var
    @FindBy(id = "postcode")
    private WebElement postcode;
    @FindBy(id = "id_country")
    private Select country; // local var
    @FindBy(id = "other")
    private WebElement additionalInformationForm;
    @FindBy(id = "phone")
    private WebElement phoneHome;
    @FindBy(id = "phone_mobile")
    private WebElement phone_mobile;
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
        WaitWrapper.waitForElement(getDriver(), emailAddressField);

        emailAddressField.isEnabled();
        emailAddressField.clear();
        emailAddressField.sendKeys(emailAddress);

        return this;
    }

    public RegisterAccountForm setRandomEmailAddress(){
        WaitWrapper.waitForElement(getDriver(), emailAddressField);

        emailAddressField.isEnabled();
        String randomEmailAddress = emails().supplier().get();
        emailAddressField.clear();
        emailAddressField.sendKeys(randomEmailAddress);
        System.out.println("Random email: " + randomEmailAddress);

        return this;
    }

    public RegisterAccountForm clickCreateAccountButton(){
        WaitWrapper.waitForElement(getDriver(), createAccountButton);

        createAccountButton.isEnabled();
        createAccountButton.click();

        return this;
    }

    /* Gender radio buttons make the test flaky due to the fact that they load with a delay.
    * In order to address this issue a temporary workaround has been implemented with retryWaitForElement(). */

    public RegisterAccountForm setGenderMale(){
        if (retryWaitForElement(getDriver(), By.id("id_gender1"), 2, 1)) {
            gender_male.click();
            gender_male.isSelected();
        }

        return this;
    }

    public RegisterAccountForm setGenderFemale(){
        if (retryWaitForElement(getDriver(), By.id("id_gender2"), 2, 1)) {
            gender_female.click();
            gender_female.isSelected();
        }

        return this;
    }

    public RegisterAccountForm setRandomGender(){
        Random random = new Random();
        String[] gender = {"gender_male","gender_female"};
        int randomGender = random.nextInt(gender.length);

        if (randomGender == 0) {
            setGenderMale();
            System.out.println("Gender: Male");
        } else {
            setGenderFemale();
            System.out.println("Gender: Female");
        }

        return this;
    }

    public RegisterAccountForm setFirstName(String aFirstName){
        WaitWrapper.waitForElement(getDriver(), firstName);

        firstName.isEnabled();
        firstName.clear();
        firstName.sendKeys(aFirstName);

        return this;
    }

    public RegisterAccountForm setLastName(String aLastName){
        WaitWrapper.waitForElement(getDriver(), lastName);

        lastName.isEnabled();
        lastName.clear();
        lastName.sendKeys(aLastName);

        return this;
    }

    public RegisterAccountForm setRandomFirstName(){
        /* Could be aligned with results of setRandomGender().
        Female (0) or Male (1). */
        WaitWrapper.waitForElement(getDriver(), firstName);

        firstName.isEnabled();
        firstName.clear();
        String aFirstName;
        Random random = new Random();
        int selectGender = random.nextInt(2);

        if (selectGender == 0) {
            aFirstName = names().firstAndFemale().get();
            firstName.sendKeys(aFirstName);
            System.out.println("First name Female: " + aFirstName);
        } else {
            aFirstName = names().firstAndMale().get();
            firstName.sendKeys(aFirstName);
            System.out.println("First name Male: " + aFirstName);
        }

        return this;
    }

    public RegisterAccountForm setRandomLastName(){
        /* Could be aligned it with the results of setRandomGender() & setRandomFirstName() ONLY if name
        inflexion is applied. E.g in Polish language the last name would not be Kowalski but Kowalska. */
        WaitWrapper.waitForElement(getDriver(), lastName);

        lastName.isEnabled();
        String aLastName = names().last().get();
        lastName.clear();
        lastName.sendKeys(aLastName);
        System.out.println("Last name: " + aLastName);

        return this;
    }

    public RegisterAccountForm setPassword(String aPassword){
        WaitWrapper.waitForElement(getDriver(), password);

        password.isEnabled();
        password.clear();
        password.sendKeys(aPassword);

        return this;
    }

    public RegisterAccountForm setRandomPassword(){
        WaitWrapper.waitForElement(getDriver(), password);

        password.isEnabled();
        String aPassword = passwords().type(MEDIUM).get();
        password.clear();
        password.sendKeys(aPassword);
        System.out.println("Password: " + aPassword);

        return this;
    }

    public RegisterAccountForm checkNewsletter(){
        WaitWrapper.waitForElement(getDriver(), newsletter);

        newsletter.isEnabled();
        if (!newsletter.isSelected()) {
            newsletter.click();
        }

        return this;
    }

    public RegisterAccountForm checkSpecialOffers(){
        WaitWrapper.waitForElement(getDriver(), newsletterSpecialOffers);

        newsletterSpecialOffers.isEnabled();
        if (!newsletterSpecialOffers.isSelected()) {
            newsletterSpecialOffers.click();
        }

        return this;
    }

    public RegisterAccountForm selectDayOfBirth(int dayOfBirth) {
        WaitWrapper.waitFluentlyForElement(getDriver(), By.id("days"));

        dobDay = new Select(getDriver().findElement(By.id("days")));
        List<WebElement> selectDay = getDriver().findElements(
                By.xpath("//SELECT[@id='days']/self::SELECT/option[@value>'0']"));
        int maxDays = selectDay.size();
        if (maxDays > 0) {
            dobDay.selectByIndex(dayOfBirth);

            return this;
        } else {
            throw new ElementNotSelectableException("Unable to select.");
        }
    }

    public RegisterAccountForm selectRandomDayOfBirth() {
        WaitWrapper.waitFluentlyForElement(getDriver(), By.id("days"));

        dobDay = new Select(getDriver().findElement(By.id("days")));
        List<WebElement> selectDay = getDriver().findElements(
                By.xpath("//SELECT[@id='days']/self::SELECT/option[@value>'0']"));
        int maxDays = selectDay.size();
        if (maxDays > 0) {
            Random randDay = new Random();
            int randomDay = randDay.nextInt(maxDays);
            selectDay.get(randomDay).click();
            System.out.println("Day of birth: " + randomDay);

            return this;
        } else {
            throw new ElementNotSelectableException("Unable to select.");
        }
    }

    public RegisterAccountForm selectMonthOfBirth(int monthOfBirth){
        WaitWrapper.waitFluentlyForElement(getDriver(), By.id("months"));

        dobMonth = new Select(getDriver().findElement(By.id("months")));
        List<WebElement> selectMonth = getDriver().findElements(
                By.xpath("//SELECT[@id='months']/self::SELECT/option[@value>'0']"));
        int maxMonths = selectMonth.size();
        if (maxMonths > 0) {
            dobMonth.selectByIndex(monthOfBirth);

            return this;
        } else {
            throw new ElementNotSelectableException("Unable to select.");
        }

    }

    public RegisterAccountForm selectRandomMonthOfBirth(){
        WaitWrapper.waitFluentlyForElement(getDriver(), By.id("months"));

        dobMonth = new Select(getDriver().findElement(By.id("months")));
        List<WebElement> selectMonth = getDriver().findElements(
                By.xpath("//SELECT[@id='months']/self::SELECT/option[@value>'0']"));
        int maxMonths = selectMonth.size();
        if (maxMonths > 0) {
            Random randMonth = new Random();
            int randomMonth = randMonth.nextInt(maxMonths);
            selectMonth.get(randomMonth).click();
            System.out.println("Month of birth: " + randomMonth);

            return this;
        } else {
            throw new ElementNotSelectableException("Unable to select.");
        }

    }

    public RegisterAccountForm selectYearOfBirth(int yearOfBirth){
        WaitWrapper.waitFluentlyForElement(getDriver(), By.id("years"));

        dobYear = new Select(getDriver().findElement(By.id("years")));
        List<WebElement> selectYear = getDriver().findElements(
                By.xpath("//SELECT[@id='years']/self::SELECT/option[@value>'0']"));
        int maxYears = selectYear.size();
        dobYear.selectByIndex(yearOfBirth);

        return this;
    }

    public RegisterAccountForm selectRandomYearOfBirth(){
        WaitWrapper.waitFluentlyForElement(getDriver(), By.id("years"));

        dobYear = new Select(getDriver().findElement(By.id("years")));
        List<WebElement> selectYear = getDriver().findElements(
                By.xpath("//SELECT[@id='years']/self::SELECT/option[@value>'0']"));
        int maxYears = selectYear.size();
        if (maxYears > 0) {
            Random randYear = new Random();
            int randomYear = randYear.nextInt(maxYears);
            selectYear.get(randomYear).click();
            System.out.println("Year of birth: " + randomYear);

            return this;
        } else {
            throw new ElementNotSelectableException("Unable to select.");
        }

    }

    public RegisterAccountForm setAdditionalInformation(String additionalInfo){
        WaitWrapper.waitForElement(getDriver(), additionalInformationForm);

        additionalInformationForm.isEnabled();
        additionalInformationForm.clear();
        additionalInformationForm.sendKeys(additionalInfo);

        return this;
    }

    public RegisterAccountForm setRandomAdditionalInformation(){
        WaitWrapper.waitForElement(getDriver(), additionalInformationForm);

        additionalInformationForm.isEnabled();
        String randWord = words().nouns().get();
        additionalInformationForm.clear();
        additionalInformationForm.sendKeys(randWord);

        return this;
    }

    public RegisterAccountForm setAddress(String aAddress){
        WaitWrapper.waitForElement(getDriver(), address);

        address.isEnabled();
        address.clear();
        address.sendKeys(aAddress);

        return this;
    }

    public RegisterAccountForm setRandomAddress(){
        WaitWrapper.waitForElement(getDriver(), address);

        address.isEnabled();
        String randWord = words().nouns().get();
        Integer apartmentNumber = ints().range(1,999).get();
        String fullAddress = randWord + " " + apartmentNumber;
        address.clear();
        address.sendKeys(fullAddress);

        return this;
    }

    public RegisterAccountForm setCompany(String aCompany){
        WaitWrapper.waitForElement(getDriver(), company);

        company.isEnabled();
        company.clear();
        company.sendKeys(aCompany);

        return this;
    }

    public RegisterAccountForm setRandomCompany(){
        WaitWrapper.waitForElement(getDriver(), company);

        company.isEnabled();
        String companyName = words().nouns().get();
        company.clear();
        company.sendKeys(companyName);

        return this;
    }

    public RegisterAccountForm setCity(String aCity){
        WaitWrapper.waitForElement(getDriver(), city);

        city.isEnabled();
        city.clear();
        city.sendKeys(aCity);

        System.out.println("City: " + aCity);

        return this;
    }

    public RegisterAccountForm setRandomCity(){
        WaitWrapper.waitForElement(getDriver(), city);

        city.isEnabled();
        String aCity = cities().capitals().get();
        city.clear();
        city.sendKeys(aCity);

        return this;
    }

    public RegisterAccountForm selectState(int aState){
        WaitWrapper.waitFluentlyForElement(getDriver(), By.id("id_state"));

        state = new Select(getDriver().findElement(By.id("id_state")));
        List<WebElement> selectState = getDriver().findElements(
                By.xpath("//SELECT[@id='id_state']/self::SELECT/option[@value>'0']"));
        int maxStates = selectState.size();
        if(maxStates > 0) {
            state.selectByIndex(aState);

            return this;
        } else {
            throw new ElementNotSelectableException("Unable to select.");
        }

    }

    public RegisterAccountForm selectRandomState(){
        WaitWrapper.waitFluentlyForElement(getDriver(), By.id("id_state"));

        state = new Select(getDriver().findElement(By.id("id_state")));
        List<WebElement> selectState = getDriver().findElements(
                By.xpath("//SELECT[@id='id_state']/self::SELECT/option[@value>'0']"));
        int maxStates = selectState.size();
        if(maxStates > 0) {
            Random randState = new Random();
            int randomState = randState.nextInt(maxStates);
            selectState.get(randomState).click();
            System.out.println("State: " + randomState);

            return this;
        } else {
            throw new ElementNotSelectableException("Unable to select.");
        }

    }

    public RegisterAccountForm setPostcode(CharSequence aPostCode){
        WaitWrapper.waitForElement(getDriver(), postcode);

        postcode.isEnabled();
        postcode.clear();
        postcode.sendKeys(aPostCode);

        return this;
    }

    public RegisterAccountForm setRandomPostcode(){
        /* In real life the post codes contain alphanumeric characters, but here
        the form accepts only 5-digit format value e.g 00000. */
        WaitWrapper.waitForElement(getDriver(), postcode);

        Integer aPostcode = ints().range(10000,99999).get();
        postcode.clear();
        postcode.sendKeys(aPostcode.toString());
        System.out.println("Postcode: " + aPostcode);

        return this;
    }

    public RegisterAccountForm selectCountry(int aCountry){
        // The United States value is automatically selected by default
        WaitWrapper.waitFluentlyForElement(getDriver(), By.id("id_country"));

        country = new Select(getDriver().findElement(By.id("id_country")));
        List<WebElement> selectCountry = getDriver().findElements(
                By.xpath("//SELECT[@id='id_country']/self::SELECT/option[@value>'0']"));
        int maxCountry = selectCountry.size();
        if(maxCountry > 0) {
            country.selectByIndex(aCountry);

            return this;
        } else {
            throw new ElementNotSelectableException("Unable to select.");
        }

    }

    public RegisterAccountForm selectRandomCountry(){
        // The United States value is automatically selected by default
        WaitWrapper.waitFluentlyForElement(getDriver(), By.id("id_country"));

        country = new Select(getDriver().findElement(By.id("id_country")));
        List<WebElement> selectCountry = getDriver().findElements(
                By.xpath("//SELECT[@id='id_country']/self::SELECT/option[@value>'0']"));
        int maxCountry = selectCountry.size();
        if(maxCountry > 0) {
            Random randCountry = new Random();
            int randomCountry = randCountry.nextInt(maxCountry);
            selectCountry.get(randomCountry).click();
            System.out.println("Country: " + randomCountry);

            return this;
        } else {
            throw new ElementNotSelectableException("Unable to select.");
        }

    }

    public RegisterAccountForm setPhoneNumber(CharSequence aPhoneHome){
        /* On many websites, phone numbers are typed with non-integer characters such as + or - with
        additional prefix numbers, but here we simply generate a 9-digit number. */
        WaitWrapper.waitForElement(getDriver(), phoneHome);

        phoneHome.isEnabled();
        phoneHome.clear();
        phoneHome.sendKeys(aPhoneHome);

        return this;
    }

    public RegisterAccountForm setRandomPhoneNumber(){
        /* On many websites, phone numbers are typed with non-integer characters such as + or - with
        additional prefix numbers, but here we simply generate a 9-digit number. */
        WaitWrapper.waitForElement(getDriver(), phone_mobile);

        phoneHome.isEnabled();
        Integer aPhoneNumber = ints().range(100000000,999999999).get();
        phoneHome.clear();
        phoneHome.sendKeys(aPhoneNumber.toString());
        System.out.println("Home phone number: " + aPhoneNumber);

        return this;
    }

    public RegisterAccountForm setMobilePhoneNumber(CharSequence mobilePhoneNumber){
        /* On many websites, phone numbers are typed with non-integer characters such as + or - with
        additional prefix numbers, but here we simply generate a 9-digit number. */
        WaitWrapper.waitForElement(getDriver(), phone_mobile);

        phone_mobile.isEnabled();
        phone_mobile.clear();
        phone_mobile.sendKeys(mobilePhoneNumber);

        return this;
    }

    public RegisterAccountForm setRandomMobilePhoneNumber(){
        /* On many websites, phone numbers are typed with non-integer characters such as + or - with
        additional prefix numbers, but here we simply generate a 9-digit number. */
        WaitWrapper.waitForElement(getDriver(), phone_mobile);

        phone_mobile.isEnabled();
        Integer aPhoneMobileNumber = ints().range(100000000,999999999).get();
        phone_mobile.clear();
        phone_mobile.sendKeys(aPhoneMobileNumber.toString());
        System.out.println("Mobile phone number: " + aPhoneMobileNumber);

        return this;
    }

    public RegisterAccountForm setAddressAlias(String aAddressAlias){
        WaitWrapper.waitForElement(getDriver(), addressAlias);

        addressAlias.isEnabled();
        addressAlias.clear();
        addressAlias.sendKeys(aAddressAlias);

        return this;
    }

    public RegisterAccountForm setRandomAddressAlias(){
        WaitWrapper.waitForElement(getDriver(), addressAlias);

        addressAlias.isEnabled();
        String randWord = words().nouns().get();
        addressAlias.clear();
        addressAlias.sendKeys(randWord);

        return this;
    }

    public RegisterAccountForm clickRegisterButton(){
        WaitWrapper.waitForElement(getDriver(),registerButton);

        registerButton.isEnabled();
        registerButton.click();

        return this;
    }

    public AccountSignInPage registerAccount(){
        return new AccountSignInPage(getDriver());
    }
}