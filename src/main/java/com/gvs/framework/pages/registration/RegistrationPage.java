package com.gvs.framework.pages.registration;

import com.gvs.framework.config.ScenarioContext;
import com.gvs.framework.pages.BasePage;
import com.gvs.framework.pages.enums.FieldErrorType;

import com.gvs.framework.util.driver.DriverFactory;
import com.gvs.framework.util.misc.CharityNumber;
//import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Component
@Scope("cucumber-glue")
public class RegistrationPage extends BasePage {
    private static final int RANDOM_LINES_COUNT = 5;
    private static final int FILE_LINES_COUNT = 849999;
    private static final String COMPANIES_HOUSE_NUMBERS_FILE = "data/companyHouseNumbers.txt";
//    private static final Logger log = Logger.getLogger(RegistrationPage.class);
    private static final String CHARITY_COUNTRY = "ENGLAND";

    @FindBy(how = How.ID, using = "button__check-details")
    protected WebElement checkDetailsButton;
    @FindBy(how = How.ID, using = "button-back")
    public WebElement backButton;

    //@FindBy(how = How.ID, using = "your-name")
    protected By yourNameFieldLink = By.id("your-name");
    //@FindBy(how = How.ID, using = "your-address")
    protected By yourAddressFieldLink = By.id("your-address");
    //@FindBy(how = How.ID, using = "your-contact-details")
    protected By yourContactDetailsLink = By.id("your-contact-details");

    private static final String MANUAL_ADDRESS_TEXT = "Enter the address manually";

    @FindBy(how = How.ID, using = "sole-trader-company-details")
    protected WebElement soleTraderCompanyDetailsLink;
    //protected By yourNameFieldLink = By.id(
    @FindBy(how = How.ID, using = "sole-trader-your-name")
    protected WebElement soleTraderNameLink;
    //protected By yourNameFieldLink = By.id(
    @FindBy(how = How.ID, using = "sole-trader-your-contact-details")
    protected WebElement soleTraderContactDetailsLink;
    //protected By yourNameFieldLink = By.id(
    //@FindBy(how = How.ID, using = "non-uk-organisation-details")
    //protected WebElement nonUkOrganisationDetailsLink;
    protected By nonUkOrganisationDetailsLink = By.id("non-uk-organisation-details");

    @FindBy(how = How.ID, using = "charityType-1")
    private WebElement englandWalesRadio;
    @FindBy(how = How.ID, using = "charityType-2")
    private WebElement scotlandRadio;
    @FindBy(how = How.ID, using = "charityType-3")
    private WebElement niRadio;
    @FindBy(how = How.ID, using = "charityNumberEngWales")
    private WebElement charityNumEng;
    @FindBy(how = How.ID, using = "charityNumberScot")
    private WebElement charityNumScot;
    @FindBy(how = How.ID, using = "charityNumberNI")
    private WebElement charityNumNI;
    @FindBy(how = How.ID, using = "charityName")
    private WebElement charityNameInput;

    @FindBy(how = How.ID, using = "firstName")
    protected WebElement firstNameField;
    @FindBy(how = How.ID, using = "lastName")
    protected WebElement lastNameField;
    @FindBy(how = How.ID, using = "telephoneNumber")
    protected WebElement phoneNumberField;

    //@FindBy(how = How.ID, using = "company-details")
    //protected WebElement companyDetailsLink;
    protected By companyDetailsLink = By.id("company-details");
    protected By charityLink = By.id("charity-details");
    protected By individualYourAddressLink = By.id("your-address");

    @FindBy(how = How.ID, using = "companyName")
    protected WebElement companyNameField;
    @FindBy(how = How.ID, using = "companyNumber")
    protected WebElement companyNumberField;
    @FindBy(how = How.ID, using = "companyTelephoneNumber")
    protected WebElement companyPhoneNumberField;
    @FindBy(how = How.ID, using = "companyEmailAddress")
    protected WebElement companyEmailAddressField;

    @FindBy(how = How.ID, using = "subBuildingName")
    protected WebElement subBuildingNameField;
    @FindBy(how = How.ID, using = "buildingNumber")
    protected WebElement buildingNumberField;
    @FindBy(how = How.ID, using = "buildingName")
    protected WebElement buildingNameField;
    @FindBy(how = How.ID, using = "street")
    protected WebElement streetField;
    @FindBy(how = How.ID, using = "town")
    protected WebElement townField;
    @FindBy(how = How.ID, using = "county")
    protected WebElement countyField;

    protected static final By COUNTRY_DROPDOWN = By.id("country");
    //@FindBy(how = How.ID, using = "country")
    //protected Select countryDropdown;
    @FindBy(how = How.ID, using = "postcode")
    protected WebElement postcodeField;

    protected static final By ADDRESS_DROPDOWN = By.id("address");
    //@FindBy(how = How.ID, using = "address")
    //protected Select addressDropdown;

    @FindBy(how = How.ID, using = "sole-trader-your-security-word")
    protected WebElement soleTraderSecurityWordLink;
    @FindBy(how = How.ID, using = "your-security-word")
    protected WebElement securityWordLink;
    @FindBy(how = How.ID, using = "securityWord")
    protected WebElement securityWordField;
    @FindBy(how = How.ID, using = "securityHint")
    protected WebElement securityHintField;

    @FindBy(how = How.ID, using = "recaptcha-anchor")
    protected WebElement recaptchaCheckbox;
    @FindBy(how = How.XPATH, using = "//*[@id='main-content']/div[1]/div/h1")
    public WebElement taskListPageTitle;
    @FindBy(how = How.ID, using = "link-sign-out")
    public WebElement signOut;

    protected static final By CAPTCHA_CHECKBOX_CONTAINER = By.id("idm-captcha-container");

    /**
     * Enters the full name block, by navigating to it first (if required). Generic + Account Registration.
     */
    public void enterName(String firstName, String lastName) {
        if (driverUtils.elementExists(yourNameFieldLink)) {
            driverUtils.clickPageTransitionElement(yourNameFieldLink);
        }
        firstNameField.sendKeys(firstName);
        lastNameField.click();
        lastNameField.sendKeys(lastName);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

    /**
     * Enters the full contact details block, by navigating to it first (if required). Generic + Account Registration.
     */
    public void enterContactDetails(String phoneNumber) {
        if (driverUtils.elementExists(yourContactDetailsLink)) {
            driverUtils.clickPageTransitionElement(yourContactDetailsLink);
        }
        phoneNumberField.sendKeys(phoneNumber);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

    /**
     * Enters the full address block, by navigating to it first (if required). Generic + Account Registration.
     */
    public void enterAddress(String postcode) {
        enterPostCodeAndSubmit(postcode);

        Select addressDropdown = new Select(driver.findElement(ADDRESS_DROPDOWN));
        addressDropdown.selectByIndex(1);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

    /**
     * Enters JUST the postcode, without the additional address confirmation. Available for negative testing.
     */
    public void enterPostCodeAndSubmit(String postcode) {
        if (driverUtils.elementExists(yourAddressFieldLink)) {
            driverUtils.clickPageTransitionElement(yourAddressFieldLink);
        }
        postcodeField.sendKeys(postcode);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

    public void RelatedOrgInputCharityNumber(String country, String charityNumber) {
        switch (country.toUpperCase()) {
            case "ENGLAND":
                driverUtils.clickPageTransitionElement(englandWalesRadio);
                //englandWalesRadio.click();
                driverUtils.clickPageTransitionElement(charityNumEng);
                charityNumEng.clear();
                charityNumEng.sendKeys(charityNumber);
                break;
            case "SCOTLAND":
                scotlandRadio.click();
                charityNumScot.clear();
                charityNumScot.sendKeys(charityNumber);
                break;
            case "NORTHERN_IRELAND":
                niRadio.click();
                charityNumNI.clear();
                charityNumNI.sendKeys(charityNumber);
                break;
            default:
                throw new IllegalArgumentException("Unknown country: " + country);
        }
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

    public void inputAddress(String postcode) {
        postcodeField.sendKeys(postcode);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
        Select addressDropdown = new Select(driver.findElement(ADDRESS_DROPDOWN));
        addressDropdown.selectByIndex(1);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

    public void inputCharityContact(String telephone, String email) {
        companyPhoneNumberField.sendKeys(telephone);
        companyEmailAddressField.sendKeys(email);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

    /**
     * This will provide a random numeric string containing n characters used for country specific charities
     */
    public static String getAlphaNumericString(CharityNumber.Country country) {
        int n;
        if (country.getPrefix().length() == 0)
            n = 7;
        else
            n = 6;

        String AlphaNumericString = "0123456789";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            sb.append(AlphaNumericString
                    .charAt(index));
        }
        sb.insert(0, country.getPrefix());

        return sb.toString();

    }

    public void enterRandomCharityDetailsManuallyWithRetry(String phoneNumber, String companyName, String subBuildingName, String buildingNumber, String buildingName,
                                                           String street, String townOrCity, String postcode, String country, String contactNumber, String companyEmail) throws IOException {

        enterAddressManually(subBuildingName, buildingNumber, buildingName, street, townOrCity, null, postcode, country);

        companyPhoneNumberField.click();
        companyPhoneNumberField.sendKeys(phoneNumber);
        companyEmailAddressField.click();
        companyEmailAddressField.sendKeys(companyEmail);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);

    }

    public void enterRandomCharityDetailsWithRetry(String phoneNumber, String companyName, String subBuildingName, String buildingNumber, String buildingName,
                                                   String street, String townOrCity, String postcode, String country, String contactNumber, String companyEmail) throws IOException {

        if (driverUtils.elementExists(charityLink)) {
            driverUtils.clickPageTransitionElement(charityLink);
        }
        CharityNumber.Country Charitycountry = CharityNumber.Country.byName(CHARITY_COUNTRY);
        RelatedOrgInputCharityNumber(CHARITY_COUNTRY, getAlphaNumericString(Charitycountry));
        charityNameInput.clear();
        charityNameInput.sendKeys(companyName);

        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);

        enterAddressManually(subBuildingName, buildingNumber, buildingName, street, townOrCity, null, postcode, country);

        companyPhoneNumberField.click();
        companyPhoneNumberField.sendKeys(phoneNumber);
        companyEmailAddressField.click();
        companyEmailAddressField.sendKeys(companyEmail);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);

    }



    public void enterSoleTraderName(String firstName, String lastName) {
        soleTraderNameLink.click();
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

    public void enterSoleTraderContactDetails(String phoneNumber) {
        soleTraderContactDetailsLink.click();
        phoneNumberField.sendKeys(phoneNumber);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

    public void enterSoleTraderCompanyDetails(String companyName,
                                              String postcode,
                                              String companyPhoneNumber,
                                              String companyEmailAddress) {
        soleTraderCompanyDetailsLink.click();
        enterCompanyname(companyName);
        enterAddress(postcode);
        driverUtils.clickByValue("*", "yes");
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
        driverUtils.clearFieldAndSendKeys(companyPhoneNumberField, companyPhoneNumber);
        driverUtils.clearFieldAndSendKeys(companyEmailAddressField, companyEmailAddress);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

    /**
     * Enters the full NonUKOrg Company Details block by navigating to it first. Generic Registration.
     */
    public void enterNonUKCompanyDetails(String companyName,
                                         String subBuildingName,
                                         String buildingNumber,
                                         String buildingName,
                                         String street,
                                         String town,
                                         String postcode,
                                         String country,
                                         String companyPhoneNumber,
                                         String companyEmailAddress) {
        enterNonUKCompanyNameAndAddress(companyName, subBuildingName, buildingNumber, buildingName, street, town, postcode, country);
        companyPhoneNumberField.sendKeys(companyPhoneNumber);
        companyEmailAddressField.sendKeys(companyEmailAddress);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

    //568
    public void enterSoleTraderCompanyDetailsManualAddress(String companyName, String subBuildingName, String buildingNumber, String buildingName,
                                                           String street, String town,
                                                           String postcode, String companyPhoneNumber, String companyEmailAddress) {
        soleTraderCompanyDetailsLink.click();
        enterCompanyname(companyName);
        enterSoleTraderAddressManually(subBuildingName, buildingNumber, buildingName, street, town, null, postcode);
        driverUtils.clickByValue("*", "yes");
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
        driverUtils.clearFieldAndSendKeys(companyPhoneNumberField, companyPhoneNumber);
        driverUtils.clearFieldAndSendKeys(companyEmailAddressField, companyEmailAddress);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);

    }

    //570
    public void enterIndividualDetails(String companyName,
                                       String subBuildingName,
                                       String buildingNumber,
                                       String buildingName,
                                       String street,
                                       String town,
                                       String postcode,
                                       String country,
                                       String companyPhoneNumber,
                                       String companyEmailAddress) {
        enterIndividualCompanyNameAndAddress(companyName, subBuildingName, buildingNumber, buildingName, street, town, postcode, country);
    }
    //570

    /**
     * Entering TestData in Manual address page of Individual business type
     */
    public void enterIndividualCompanyNameAndAddress(String companyName,
                                                     String subBuildingName,
                                                     String buildingNumber,
                                                     String buildingName,
                                                     String street,
                                                     String town,
                                                     String postcode,
                                                     String country) {
        if (driverUtils.elementExists(individualYourAddressLink)) {
            driverUtils.clickPageTransitionElement(individualYourAddressLink);
        }
        enterAddressManually(subBuildingName, buildingNumber, buildingName, street, town, null, postcode, country);
    }

    //568
    /*
    Below Method Enter all relevant details of in Manual address page and verify in "Check your Information" page
     */
    public void enterSoleTraderAddressManually(String subBuildingName,
                                               String buildingNumber,
                                               String buildingName,
                                               String street,
                                               String town,
                                               String county,
                                               String postcode) {
        if (driverUtils.elementExists(yourAddressFieldLink)) {
            driverUtils.clickPageTransitionElement(yourAddressFieldLink);
        }
        if (driverUtils.isStringVisible(MANUAL_ADDRESS_TEXT)) {
            driverUtils.clickByText(MANUAL_ADDRESS_TEXT);
        }
        subBuildingNameField.sendKeys(subBuildingName);
        buildingNumberField.sendKeys(buildingNumber);
        buildingNameField.sendKeys(buildingName);
        streetField.sendKeys(street);
        townField.sendKeys(town);
        if (county != null) {
            countyField.sendKeys(county);
        }
        postcodeField.sendKeys(postcode);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

    /**
     * Enters the company name and address of a NonUKOrg Company Details block by navigating to it first.
     * Available for negative testing.
     */
    public void enterNonUKCompanyNameAndAddress(String companyName,
                                                String subBuildingName,
                                                String buildingNumber,
                                                String buildingName,
                                                String street,
                                                String town,
                                                String postcode,
                                                String country) {
        if (driverUtils.elementExists(nonUkOrganisationDetailsLink)) {
            driverUtils.clickPageTransitionElement(nonUkOrganisationDetailsLink);
        }
        enterCompanyname(companyName);

        enterAddressManually(subBuildingName, buildingNumber, buildingName, street, town, null, postcode, country);
    }

    /**
     * County value of null means it won't be filled in.
     */
    public void enterAddressManually(String subBuildingName,
                                     String buildingNumber,
                                     String buildingName,
                                     String street,
                                     String town,
                                     String county,
                                     String postcode,
                                     String country) {
        if (driverUtils.elementExists(yourAddressFieldLink)) {
            driverUtils.clickPageTransitionElement(yourAddressFieldLink);
        }
        if (driverUtils.isStringVisible(MANUAL_ADDRESS_TEXT)) {
            driverUtils.clickByText(MANUAL_ADDRESS_TEXT);
        }
        subBuildingNameField.click();
        subBuildingNameField.sendKeys(subBuildingName);

        buildingNameField.click();
        buildingNumberField.sendKeys(buildingNumber);

        buildingNameField.click();
        buildingNameField.sendKeys(buildingName);

        streetField.click();
        streetField.sendKeys(street);

        townField.click();
        townField.sendKeys(town);
        if (county != null) {
            countyField.click();
            countyField.sendKeys(county);
        }
        postcodeField.click();
        postcodeField.sendKeys(postcode);

        WebElement WebElementCountryDropDown;

        /** UK registration have no country drop-down: Sole Trader & Charity */
        if (driverUtils.elementExists(COUNTRY_DROPDOWN)) {
            WebElementCountryDropDown = driver.findElement(COUNTRY_DROPDOWN);

            Select countryDropdown = new Select(WebElementCountryDropDown);
            if (country != null) {
                WebElementCountryDropDown.click();
                countryDropdown.selectByVisibleText(country);
            } else {
                countryDropdown.selectByIndex(1);
            }
        }

        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

    public void enterCompanyname(String companyName) {
        companyNameField.sendKeys(companyName);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }


    public void enterSoleTraderSecurityWord(String securityWord, String securityHint) {
        driverUtils.clickPageTransitionElement(soleTraderSecurityWordLink);
        enterSecurityWordDetails(securityWord, securityHint);
    }

    /**
     * Enters the full security word block by navigating to it first. Generic Registration.
     */
    public void enterSecurityWord(String securityWord, String securityHint) {
        driverUtils.clickPageTransitionElement(securityWordLink);
        enterSecurityWordDetails(securityWord, securityHint);
    }

    /**
     * Private method to fill in the security word details, as it is generic to all account types. Generic Registration.
     */
    private void enterSecurityWordDetails(String securityWord, String securityHint) {
        securityWordField.sendKeys(securityWord);
        securityHintField.sendKeys(securityHint);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

    /**
     * Submits the current registration, including Captcha implementation. Generic Registration.
     */
    public void submit() {
        clickCheckDetails();
        completeCaptchaAndSubmit();
    }

    public void clickCheckDetails() {
        driverUtils.clickPageTransitionElement(checkDetailsButton);
    }

    public void completeCaptchaAndSubmit() {
        if (driverUtils.elementExists(CAPTCHA_CHECKBOX_CONTAINER)) {
            // Implicit assertion to make sure the functionality it covered.
            assertFalse(driver.findElement(GENERIC_CONTINUE_BUTTON_2).isEnabled());

            driver.switchTo().frame(0);

            for (int i = 0; i <= 3; i++) {
                recaptchaCheckbox.click();

                int j = 1;
                while (recaptchaCheckbox.getAttribute("aria-checked").equalsIgnoreCase("false")) {

                    // Have to wait for the loading icon of the captcha
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (j >= 3) {
                        break;
                    }
                    j++;
                }
            }

            driver.switchTo().defaultContent();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driverUtils.clickElementWithRetry(GENERIC_CONTINUE_BUTTON_2);
    }

    private boolean isDuplicateOrganisationPageLoaded() {
        driverUtils.waitUntilPageFullyLoaded();
        return driver.getCurrentUrl().contains("already-registered");
    }

    public boolean isMarkedCompleted(String section) {
        return !(driver.findElements(By.cssSelector("#your-" + section + "-completed")).isEmpty() &&
                driver.findElements(By.cssSelector("#sole-trader-your-" + section + "-completed")).isEmpty());
    }

    public boolean hasFieldError(FieldErrorType errorType, String fieldId) {
        WebElement fieldError = driver.findElement(By.cssSelector("#" + fieldId + "-error"));
        return fieldError.getText().toLowerCase().contains(errorType.getErrorMessage());
    }

    //571
    public void enterLtdCompanyDetails(String companyRegNumber) {

        if (driverUtils.elementExists(companyDetailsLink)) {
            driverUtils.clickPageTransitionElement(companyDetailsLink);
        }

        companyNumberField.sendKeys(companyRegNumber);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

    //571
    public void enterAboutYouSectionForRegOrgTypes() {
        enterName(ScenarioContext.DEFAULT_FIRST_NAME, ScenarioContext.DEFAULT_LAST_NAME);
        enterContactDetails(ScenarioContext.DEFAULT_PHONE_NUMBER);
        enterSecurityWord(ScenarioContext.DEFAULT_SECURITY_WORD, ScenarioContext.DEFAULT_SECURITY_HINT);
    }

    public RegistrationPage(DriverFactory driverFactory) {
        super(driverFactory);
    }
}
