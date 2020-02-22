package com.automationpractice.pageObjects.components;

import com.automationpractice.pageObjects.pages.AbstractPageObject;
import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.utils.WaitWrapper;

import static net.andreinc.mockneat.types.enums.PassStrengthType.MEDIUM;
import static net.andreinc.mockneat.unit.address.Cities.cities;
import static net.andreinc.mockneat.unit.text.Words.words;
import static net.andreinc.mockneat.unit.user.Emails.emails;
import static net.andreinc.mockneat.unit.user.Names.names;
import static net.andreinc.mockneat.unit.user.Passwords.passwords;
import static net.andreinc.mockneat.unit.types.Ints.ints;

import org.openqa.selenium.By;
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
    @FindBy(id = "phone_mobile")
    private WebElement phone_mobile;
    @FindBy(id = "alias")
    private WebElement addressAlias;

    // OTHER
    @FindBy(name = "SubmitCreate")
    private WebElement createAnAccountButton;
    @FindBy(id = "submitAccount")
    private WebElement registerButton;

    // ERRORS
    //todo Add more errors to the list for future tests
    @FindBy(id = "create_account_error")
    private WebElement createAnAccountError;

    public RegisterAccountForm(WebDriver driver){
        super(driver);
    }

    public RegisterAccountForm selectCreateNewAccountEmailAddressField(){
        WaitWrapper.waitForElement(getDriver(), 10, emailAddressField);
        return this;
    }

    public RegisterAccountForm setNewAccountEmailAddress(String emailAddress){
        emailAddressField.clear();
        emailAddressField.sendKeys(emailAddress);

        return this;
    }

    public RegisterAccountForm setRandomEmailAddress(){
        emailAddressField.clear();
        String randomEmailAddress = emails().supplier().get();
        emailAddressField.sendKeys(randomEmailAddress);

        System.out.println("Random email: " + randomEmailAddress);

        return this;
    }

    public RegisterAccountForm clickCreateNewAccountButton(){
        //todo Add exception for error "An account using this email address has already been registered."
        WaitWrapper.waitForElement(getDriver(),10, createAnAccountButton);

        createAnAccountButton.click();

        return this;
    }

    public RegisterAccountForm setGenderMale(String gender){
        WaitWrapper.waitForElement(getDriver(), 10, gender_male);

        gender_male.click();
        gender_male.isSelected();

        return this;
    }

    public RegisterAccountForm setGenderFemale(String gender){
        WaitWrapper.waitForElement(getDriver(), 10, gender_female);

        gender_female.click();
        gender_female.isSelected();

        return this;
    }

    public RegisterAccountForm setRandomGender(){
        //todo Add exception for org.openqa.selenium.TimeoutException
        WaitWrapper.waitForElement(getDriver(), 10, gender_male);
        WaitWrapper.waitForElement(getDriver(), 10, gender_female);

        Random random = new Random();
        String[] gender = {"gender_male","gender_female"};
        int randomGender = random.nextInt(gender.length);

        if (randomGender == 0) {
            gender_male.click();
            gender_male.isSelected();
            System.out.println("Gender: Male");
        } else {
            gender_female.click();
            gender_female.isSelected();
            System.out.println("Gender: Female");
        }

        return this;
    }

    public RegisterAccountForm setFirstName(String aFirstName){
        WaitWrapper.waitForElement(getDriver(), 10, firstName);

        firstName.clear();
        firstName.sendKeys(aFirstName);

        return this;
    }

    public RegisterAccountForm setLastName(String aLastName){
        WaitWrapper.waitForElement(getDriver(), 10, lastName);

        lastName.clear();
        lastName.sendKeys(aLastName);

        return this;
    }

    public RegisterAccountForm setRandomFirstName(){
        /* Could be aligned with results of setRandomGender().
        Female (0) or Male (1). */

        WaitWrapper.waitForElement(getDriver(), 10, firstName);

        Random random = new Random();
        int selectGender = random.nextInt(2);

        if (selectGender == 0) {
            firstName.clear();
            String aFirstName = names().firstAndFemale().get();
            firstName.sendKeys(aFirstName);
            System.out.println("First name Female: " + aFirstName);
        } else {
            firstName.clear();
            String aFirstName = names().firstAndMale().get();
            firstName.sendKeys(aFirstName);
            System.out.println("First name Male: " + aFirstName);
        }

        return this;
    }

    public RegisterAccountForm setRandomLastName(){
        /* Could be aligned it with the results of setRandomGender() & setRandomFirstName() ONLY if name
        inflexion is applied. E.g in Polish language the last name would not be Kowalski but Kowalska. */

        WaitWrapper.waitForElement(getDriver(), 10, lastName);

        lastName.clear();
        String aLastName = names().last().get();
        lastName.sendKeys(aLastName);

        System.out.println("Last name: " + aLastName);

        return this;
    }

    public RegisterAccountForm setPassword(String aPassword){
        WaitWrapper.waitForElement(getDriver(), 10, password);

        password.clear();
        password.sendKeys(aPassword);

        return this;
    }

    public RegisterAccountForm setRandomPassword(){
        WaitWrapper.waitForElement(getDriver(), 10, password);

        password.clear();
        String aPassword = passwords().type(MEDIUM).get();
        password.sendKeys(aPassword);

        System.out.println("Password: " + aPassword);

        return this;
    }

    public RegisterAccountForm selectDayOfBirth(int dayOfBirth){
        dobDay = new Select(getDriver().findElement(By.id("days")));
        dobDay.isMultiple();
        dobDay.selectByIndex(dayOfBirth);

        return this;
    }

    public RegisterAccountForm selectRandomDayOfBirth(){
        dobDay = new Select(getDriver().findElement(By.id("days")));
        dobDay.isMultiple();

        List<WebElement> selectDay = getDriver().findElements(
                By.xpath("//SELECT[@id='days']/self::SELECT/option[@value>'0']"));
        int maxDays = selectDay.size();

        Random randDay = new Random();
        int randomDay = randDay.nextInt(maxDays);
        selectDay.get(randomDay).click();

        System.out.println("Day of birth: " + randomDay);

        return this;
    }

    public RegisterAccountForm selectMonthOfBirth(int monthOfBirth){
        dobMonth = new Select(getDriver().findElement(By.id("months")));
        dobMonth.isMultiple();
        dobMonth.selectByIndex(monthOfBirth);

        return this;
    }

    public RegisterAccountForm selectRandomMonthOfBirth(){
        dobMonth = new Select(getDriver().findElement(By.id("months")));
        dobMonth.isMultiple();

        List<WebElement> selectMonth = getDriver().findElements(
                By.xpath("//SELECT[@id='months']/self::SELECT/option[@value>'0']"));
        int maxMonths = selectMonth.size();

        Random randMonth = new Random();
        int randomMonth = randMonth.nextInt(maxMonths);
        selectMonth.get(randomMonth).click();

        System.out.println("Month of birth: " + randomMonth);

        return this;
    }

    public RegisterAccountForm selectYearOfBirth(int yearOfBirth){
        dobYear = new Select(getDriver().findElement(By.id("years")));
        dobYear.isMultiple();
        dobYear.selectByIndex(yearOfBirth);

        return this;
    }

    public RegisterAccountForm selectRandomYearOfBirth(){
        dobYear = new Select(getDriver().findElement(By.id("years")));
        dobYear.isMultiple();

        List<WebElement> selectYear = getDriver().findElements(
                By.xpath("//SELECT[@id='years']/self::SELECT/option[@value>'0']"));
        int maxYears = selectYear.size();

        Random randYear = new Random();
        int randomYear = randYear.nextInt(maxYears);
        selectYear.get(randomYear).click();

        System.out.println("Year of birth: " + randomYear);
        return this;
    }

    public RegisterAccountForm setAddress(String aAddress){
        WaitWrapper.waitForElement(getDriver(), 10, address);
        address.clear();
        address.sendKeys(aAddress);
        return this;
    }

    public RegisterAccountForm setRandomAddress(){
        WaitWrapper.waitForElement(getDriver(), 10, address);

        String randWord = words().nouns().get();
        Integer apartmentNumber = ints().range(1,999).get();
        String fullAddress = randWord + " " + apartmentNumber;

        address.clear();
        address.sendKeys(fullAddress);

        return this;
    }

    public RegisterAccountForm setCity(String aCity){
        WaitWrapper.waitForElement(getDriver(), 10, city);

        city.clear();
        city.sendKeys(aCity);

        System.out.println("City: " + aCity);

        return this;
    }

    public RegisterAccountForm setRandomUSACity(){
        WaitWrapper.waitForElement(getDriver(), 10, city);

        String aCity = cities().us().get();

        city.clear();
        city.sendKeys(aCity);

        return this;
    }

    public RegisterAccountForm selectState(int aState){
        state = new Select(getDriver().findElement(By.id("id_state")));
        state.isMultiple();
        state.selectByIndex(aState);

        return this;
    }

    public RegisterAccountForm selectRandomState(){
        state = new Select(getDriver().findElement(By.id("id_state")));
        state.isMultiple();

        List<WebElement> selectState = getDriver().findElements(
                By.xpath("//SELECT[@id='id_state']/self::SELECT/option[@value>'0']"));
        int maxStates = selectState.size();

        Random randState = new Random();
        int randomState = randState.nextInt(maxStates);
        selectState.get(randomState).click();

        System.out.println("State: " + randomState);

        return this;
    }

    public RegisterAccountForm setPostcode(CharSequence aPostCode){
        WaitWrapper.waitForElement(getDriver(), 10, postcode);
        postcode.clear();
        postcode.sendKeys(aPostCode);
        return this;
    }

    public RegisterAccountForm setRandomPostcode(){
        /* In real life the post codes contain alphanumeric characters, but here
        the form accepts only 5-digit format value e.g 00000. */

        WaitWrapper.waitForElement(getDriver(), 10, postcode);

        Integer aPostcode = ints().range(10000,99999).get();

        postcode.clear();
        postcode.sendKeys(aPostcode.toString());

        System.out.println("Postcode: " + aPostcode);

        return this;
    }

    public RegisterAccountForm selectCountry(int aCountry){
        // The United States value is automatically selected by default
        country = new Select(getDriver().findElement(By.id("id_country")));
        country.isMultiple();
        country.selectByIndex(aCountry);
        return this;
    }

    public RegisterAccountForm selectRandomCountry(){
        // The United States value is automatically selected by default
        country = new Select(getDriver().findElement(By.id("id_country")));
        country.isMultiple();

        List<WebElement> selectCountry = getDriver().findElements(
                By.xpath("//SELECT[@id='id_country']/self::SELECT/option[@value>'0']"));
        int maxCountry = selectCountry.size();

        Random randCountry = new Random();
        int randomCountry = randCountry.nextInt(maxCountry);
        selectCountry.get(randomCountry).click();

        System.out.println("Country: " + randomCountry);

        return this;
    }

    public RegisterAccountForm setMobilePhoneNumber(CharSequence mobilePhoneNumber){
        WaitWrapper.waitForElement(getDriver(), 10, phone_mobile);

        phone_mobile.clear();
        phone_mobile.sendKeys(mobilePhoneNumber);

        return this;
    }

    public RegisterAccountForm setRandomMobilePhoneNumber(){
        /* On many websites, phone numbers are typed with non-integer characters such as + or - with
        additional prefix numbers, but here we simply generate a 9-digit number. */

        WaitWrapper.waitForElement(getDriver(), 10, phone_mobile);

        Integer aPhoneMobileNumber = ints().range(100000000,999999999).get();

        phone_mobile.clear();
        phone_mobile.sendKeys(aPhoneMobileNumber.toString());

        System.out.println("Mobile phone number: " + aPhoneMobileNumber);

        return this;
    }

    public RegisterAccountForm setAddressAlias(String aAddressAlias){
        WaitWrapper.waitForElement(getDriver(), 10, addressAlias);

        addressAlias.clear();
        addressAlias.sendKeys(aAddressAlias);

        return this;
    }

    public RegisterAccountForm setRandomAddressAlias(){
        WaitWrapper.waitForElement(getDriver(), 10, addressAlias);

        String randWord = words().nouns().get();

        addressAlias.clear();
        addressAlias.sendKeys(randWord);

        return this;
    }

    public RegisterAccountForm clickRegisterButton(){
        WaitWrapper.waitForElement(getDriver(),10, registerButton);

        registerButton.isEnabled();
        registerButton.click();

        return this;
    }

    public AccountSignInPage registerAccount(){
        return new AccountSignInPage(getDriver());
    }
}