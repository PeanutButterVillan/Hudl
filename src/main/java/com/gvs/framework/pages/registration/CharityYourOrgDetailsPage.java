package com.gvs.framework.pages.registration;

import com.gvs.framework.util.driver.DriverFactory;
import com.gvs.framework.util.misc.CharityNumber.Country;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class CharityYourOrgDetailsPage extends RegistrationPage {

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

  public CharityYourOrgDetailsPage(DriverFactory driverFactory) {
    super(driverFactory);
  }

  public void inputCharityNumber(Country country, String charityNumber) {
    switch (country) {
      case ENGLAND:
        englandWalesRadio.click();
        charityNumEng.clear();
        charityNumEng.sendKeys(charityNumber);
        break;
      case SCOTLAND:
        scotlandRadio.click();
        charityNumScot.clear();
        charityNumScot.sendKeys(charityNumber);
        break;
      case NORTHERN_IRELAND:
        niRadio.click();
        charityNumNI.clear();
        charityNumNI.sendKeys(charityNumber);
        break;
      default:
        throw new IllegalArgumentException("Unknown country: " + country);
    }
    driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
  }

  public void inputCharityName(String charityName) {
    charityNameInput.clear();
    charityNameInput.sendKeys(charityName);
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
}
