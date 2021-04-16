package com.gvs.framework.pages.registration;

import com.gvs.framework.pages.BasePage;
import com.gvs.framework.util.driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * "PersonalAboutYouPage.java" class file handles all the pages relevant to "About you" sections  Element Locators for automation framework
 * This Page is demonstration of Page Object Model of the framework
 *
 */

@Component
@Scope("cucumber-glue")
public class PersonalAboutYouPage extends BasePage {

  @FindBy(how = How.ID, using = "firstName")
  private WebElement personalFirstName;

  @FindBy(how = How.ID, using = "lastName")
  private WebElement personalLastName;

  @FindBy(how = How.ID, using = "telephoneNumber")
  private WebElement personalTelephone;

  /*Below Locators for "Your address" page of "AboutYou" Section*/

  @FindBy(how = How.ID, using = "postcode")
  private WebElement postCodeField;

  @FindBy(how = How.ID, using = "address")
  private WebElement addressKey;

  @FindBy(how = How.ID, using = "country")
  private WebElement countryDropDown;

  @FindBy(how = How.XPATH, using = "//a[contains(text(), 'address manually')]")
  private WebElement manualAddressLink;

  @FindBy(how = How.ID, using = "buildingNumber")
  private WebElement buildingNumberField;

  @FindBy(how = How.ID, using = "street")
  private WebElement streetField;

  @FindBy(how = How.ID, using = "town")
  private WebElement townField;



  @FindBy(how = How.ID, using = "personal-address-manual")
  private WebElement enterTheAddressManuallyLink;


  /*@FindBy(how = How.ID, using = "address")
  private WebElement selectTheAddressDropdown;*/

  @FindBy(how = How.ID, using = "changePostcode")
  private WebElement changeButton;

  @FindBy(how = How.ID, using = "personal-address-manual")
  private WebElement iCannotFindTheAddressInTheListLink;


  //---------------

  @FindBy(how = How.ID, using = "personal-postcode")
  private WebElement searchForMyAddressByUKPostcodeLink;

  @FindBy(how = How.CLASS_NAME, using = "govuk-fieldset__heading")
  private WebElement pageHeading;


  public PersonalAboutYouPage(DriverFactory driverFactory) { super(driverFactory); }

  public void provideNameAndContactDetails(String firstName, String lastName, String telephoneNumber) {
    fillInName(firstName, lastName);
    fillInTelephoneNumber(telephoneNumber);
    driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
  }

  public void provideNameDetails(String firstName, String lastName) {
    fillInName(firstName, lastName);
    driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
  }

  public void provideContactDetails(String telephoneNumber) {
    fillInTelephoneNumber(telephoneNumber);
    driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
  }

  public void provideYourAddressDetails(String postCode) {
    postCodeField.sendKeys(postCode);
    driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);

    Select select = new Select(addressKey);
    select.selectByIndex(5);
    driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
  }

  public void provideManualPostcode(String postcode, boolean isUk) {
    manualAddressLink.click();
    changeBuildingNumberField("22");
    changeStreetField("Test Street");
    changeTownField("Test Town");
    changeIsUkField(isUk);
    changePostCodeField(postcode);

    clickContinueButton();
  }

  public void changeBuildingNumberField(String number) {
    buildingNumberField.clear();
    buildingNumberField.sendKeys(number);
  }

  public void changeStreetField(String street) {
    streetField.clear();
    streetField.sendKeys(street);
  }

  public void changeTownField(String town) {
    townField.clear();
    townField.sendKeys(town);
  }

  public void changeIsUkField(boolean isUk) {
    Select countryList = new Select(countryDropDown);

    if (isUk) {
      countryList.selectByVisibleText("United Kingdom of Great Britain and Northern Ireland");
    } else {
      countryList.selectByVisibleText("Algeria");
    }
  }

  public void changePostCodeField(String postcode) {
    postCodeField.clear();
    postCodeField.sendKeys(postcode);
  }

  public void clickContinueButton() {
    driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
  }

  private void fillInName(String firstName, String lastName) {
    personalFirstName.clear();
    personalFirstName.sendKeys(firstName);
    personalLastName.clear();
    personalLastName.sendKeys(lastName);
  }

  private void fillInTelephoneNumber(String telephoneNumber) {
    personalTelephone.clear();
    personalTelephone.sendKeys(telephoneNumber);
  }



  //-----------

  public void inputPostCode(String postcode) {
    postCodeField.clear();
    postCodeField.sendKeys(postcode);
  }

  public void postCodeFieldContinueButton() {
    driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);  }

  public void clickEnterTheAddressManuallyLink() {
    driverUtils.clickPageTransitionElement(enterTheAddressManuallyLink);
  }




  //--------------

  public void selectTheFirstAddress() {
    driverUtils.waitUntilPageFullyLoaded();
    Select addressList = new Select(addressKey);
    addressList.selectByIndex(1);
  }

  public void clickChangeLink() {
    driverUtils.clickPageTransitionElement(changeButton);
  }

  public void clickICannotFindTheAddressInTheListLink() {
    driverUtils.clickPageTransitionElement(iCannotFindTheAddressInTheListLink);
  }



  //----------

  public void clickSearchForMyAddressByUKPostcodeLink() {
    driverUtils.clickPageTransitionElement(searchForMyAddressByUKPostcodeLink);
  }

  public boolean isUpdateAddressManuallyPageLoaded() {
    driverUtils.waitUntilPageFullyLoaded();
    return pageHeading != null &&
            pageHeading.isDisplayed() &&
            "What is your address?".equals(pageHeading.getText()) &&
            driver.getCurrentUrl().contains("personal-address-manual");
  }

}
