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
import static net.andreinc.mockneat.unit.address.Cities.cities;
import static net.andreinc.mockneat.unit.objects.Probabilities.probabilities;
import static net.andreinc.mockneat.unit.text.Formatter.fmt;
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
        System.out.println("Email: " + emailAddress);

        return this;
    }

    public RegisterAccountForm setRandomEmailAddress(){
        String randomEmailAddress = emails().supplier().get();
        setEmailAddress(randomEmailAddress);

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
            System.out.println("Gender: Male");
        }

        return this;
    }

    public RegisterAccountForm setGenderFemale(){
        if (retryWaitForElement(getDriver(), By.id("id_gender2"), 2, 1)) {
            gender_female.click();
            gender_female.isSelected();
            System.out.println("Gender: Female");
        }

        return this;
    }

    public RegisterAccountForm setRandomGender(){
        Random random = new Random();
        String[] gender = {"gender_male","gender_female"};
        int randomGender = random.nextInt(gender.length);

        if (randomGender == 0) {
            setGenderMale();
        } else {
            setGenderFemale();
        }

        return this;
    }

    public RegisterAccountForm setFirstName(String aFirstName){
        WaitWrapper.waitForElement(getDriver(), firstName);

        firstName.isEnabled();
        firstName.clear();
        firstName.sendKeys(aFirstName);
        System.out.println("First name: " + aFirstName);

        return this;
    }

    public RegisterAccountForm setLastName(String aLastName){
        WaitWrapper.waitForElement(getDriver(), lastName);

        lastName.isEnabled();
        lastName.clear();
        lastName.sendKeys(aLastName);
        System.out.println("Last name: " + aLastName);

        return this;
    }

    public RegisterAccountForm setRandomFirstName(){
        /* Could be aligned with results of setRandomGender().
        Female (0) or Male (1). */
        String aFirstName;
        Random random = new Random();
        int selectGender = random.nextInt(2);

        if (selectGender == 0) {
            aFirstName = names().firstAndFemale().get();
            setFirstName(aFirstName);
        } else {
            aFirstName = names().firstAndMale().get();
            setFirstName(aFirstName);
        }

        return this;
    }

    public RegisterAccountForm setRandomLastName(){
        /* Could be aligned it with the results of setRandomGender() & setRandomFirstName() ONLY if name
        inflexion is applied. E.g in Polish language the last name would not be Kowalski but Kowalska. */
        String aLastName = names().last().get();
        setLastName(aLastName);

        return this;
    }

    public RegisterAccountForm setPassword(String aPassword){
        WaitWrapper.waitForElement(getDriver(), password);

        password.isEnabled();
        password.clear();
        password.sendKeys(aPassword);
        System.out.println("Password: " + aPassword);

        return this;
    }

    public RegisterAccountForm setRandomPassword(){
        String aPassword = passwords().type(MEDIUM).get();
        setPassword(aPassword);

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

    public RegisterAccountForm selectDayOfBirth(int dayOfBirth) { // todo refactor
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

    public RegisterAccountForm selectRandomDayOfBirth() { // todo refactor
        WaitWrapper.waitFluentlyForElement(getDriver(), By.id("days"));

        dobDay = new Select(getDriver().findElement(By.id("days")));
        List<WebElement> selectDay = getDriver().findElements(
                By.xpath("//SELECT[@id='days']/self::SELECT/option[@value>'0']")); // Index 0 (value "-" excluded)
        int maxDays = selectDay.size();
        if (maxDays > 0) {
            Random randDay = new Random();
            int randomDay = randDay.nextInt(maxDays);
            selectDay.get(randomDay).click();
            System.out.println("Day of birth (position on list): " + randomDay + ", value = " + dobDay.getOptions().get(randomDay + 1).getText()); // Increment by 1 to adjust printed text value from index

            return this;
        } else {
            throw new ElementNotSelectableException("Unable to select.");
        }
    }

    public RegisterAccountForm selectMonthOfBirth(int monthOfBirth){ // todo refactor
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

    public RegisterAccountForm selectRandomMonthOfBirth(){ // todo refactor
        WaitWrapper.waitFluentlyForElement(getDriver(), By.id("months"));

        dobMonth = new Select(getDriver().findElement(By.id("months")));
        List<WebElement> selectMonth = getDriver().findElements(
                By.xpath("//SELECT[@id='months']/self::SELECT/option[@value>'0']")); // Index 0 (value "-" excluded)
        int maxMonths = selectMonth.size();
        if (maxMonths > 0) {
            Random randMonth = new Random();
            int randomMonth = randMonth.nextInt(maxMonths);
            selectMonth.get(randomMonth).click();
            System.out.println("Month of birth (position on list): " + randomMonth + ", value = " + dobMonth.getOptions().get(randomMonth + 1).getText()); // Increment by 1 to adjust printed text value from index

            return this;
        } else {
            throw new ElementNotSelectableException("Unable to select.");
        }

    }

    public RegisterAccountForm selectYearOfBirth(int yearOfBirth){ // todo refactor
        WaitWrapper.waitFluentlyForElement(getDriver(), By.id("years"));

        dobYear = new Select(getDriver().findElement(By.id("years")));
        List<WebElement> selectYear = getDriver().findElements(
                By.xpath("//SELECT[@id='years']/self::SELECT/option[@value>'0']"));
        int maxYears = selectYear.size();
        dobYear.selectByIndex(yearOfBirth);

        return this;
    }

    public RegisterAccountForm selectRandomYearOfBirth(){ // todo refactor
        WaitWrapper.waitFluentlyForElement(getDriver(), By.id("years"));

        dobYear = new Select(getDriver().findElement(By.id("years")));
        List<WebElement> selectYear = getDriver().findElements(
                By.xpath("//SELECT[@id='years']/self::SELECT/option[@value>'0']")); // Index 0 (value "-" excluded)
        int maxYears = selectYear.size();
        if (maxYears > 0) {
            Random randYear = new Random();
            int randomYear = randYear.nextInt(maxYears);
            selectYear.get(randomYear).click();
            System.out.println("Year of birth (position on list): " + randomYear + ", value = " + dobYear.getOptions().get(randomYear + 1).getText()); // Increment by 1 to adjust printed text value from index

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
        System.out.println("Additional Info: " + additionalInfo);

        return this;
    }

    public RegisterAccountForm setRandomAdditionalInformation(){
        String randWord = words().nouns().get();
        setAdditionalInformation(randWord);

        return this;
    }

    public RegisterAccountForm setAddress(String aAddress){
        WaitWrapper.waitForElement(getDriver(), address);

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

        setAddress(randomAddress.accumulate(1, "\n").get());

        return this;
    }

    public RegisterAccountForm setCompany(String aCompany){
        WaitWrapper.waitForElement(getDriver(), company);

        company.isEnabled();
        company.clear();
        company.sendKeys(aCompany);
        System.out.println("Company: " + aCompany);

        return this;
    }

    public RegisterAccountForm setRandomCompany(){
        String companyName = words().nouns().get();
        setCompany(companyName);

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
        String aCity = cities().capitals().get();
        setCity(aCity);

        return this;
    }

    public RegisterAccountForm selectState(int aState){ // todo refactor
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

    public RegisterAccountForm selectRandomState(){ // todo refactor
        WaitWrapper.waitFluentlyForElement(getDriver(), By.id("id_state"));

        state = new Select(getDriver().findElement(By.id("id_state")));
        List<WebElement> selectState = getDriver().findElements(
                By.xpath("//SELECT[@id='id_state']/self::SELECT/option[@value>'0']")); // Index 0 (value "-" excluded)
        int maxStates = selectState.size();
        if(maxStates > 0) {
            Random randState = new Random();
            int randomState = randState.nextInt(maxStates);
            selectState.get(randomState).click();

            System.out.println("State (position on list): " + randomState + ", value = " + state.getOptions().get(randomState + 1).getText()); // Increment by 1 to adjust printed text value from index
            return this;
        } else {
            throw new ElementNotSelectableException("Unable to select.");
        }

    }

    public RegisterAccountForm setPostcode(CharSequence aPostcode){
        /* In real life the post codes contain alphanumeric characters, but here
        the form accepts only 5-digit format value e.g 00000. */
        WaitWrapper.waitForElement(getDriver(), postcode);

        postcode.isEnabled();
        postcode.clear();
        postcode.sendKeys(aPostcode);
        System.out.println("Postcode: " + aPostcode);

        return this;
    }

    public RegisterAccountForm setRandomPostcode(){
        String aPostcode = ints().range(10000,99999).get().toString();
        setPostcode(aPostcode);

        return this;
    }

    public RegisterAccountForm selectCountry(int aCountry){ // todo refactor
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

    public RegisterAccountForm selectRandomCountry(){ // todo refactor
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
        System.out.println("Home phone number: " + aPhoneHome);

        return this;
    }

    public RegisterAccountForm setRandomPhoneNumber(){
        /* On many websites, phone numbers are typed with non-integer characters such as + or - with
        additional prefix numbers, but here we simply generate a 9-digit number. */
        String aPhoneNumber = ints().range(100000000,999999999).get().toString();
        setPhoneNumber(aPhoneNumber);

        return this;
    }

    public RegisterAccountForm setMobilePhoneNumber(CharSequence mobilePhoneNumber){
        /* On many websites, phone numbers are typed with non-integer characters such as + or - with
        additional prefix numbers, but here we simply generate a 9-digit number. */
        WaitWrapper.waitForElement(getDriver(), phone_mobile);

        phone_mobile.isEnabled();
        phone_mobile.clear();
        phone_mobile.sendKeys(mobilePhoneNumber);
        System.out.println("Mobile phone number: " + mobilePhoneNumber);

        return this;
    }

    public RegisterAccountForm setRandomMobilePhoneNumber(){
        /* On many websites, phone numbers are typed with non-integer characters such as + or - with
        additional prefix numbers, but here we simply generate a 9-digit number. */
        String aPhoneMobileNumber = ints().range(100000000,999999999).get().toString();
        setMobilePhoneNumber(aPhoneMobileNumber);

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
        String randWord = words().nouns().get();
        setAddressAlias(randWord);

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