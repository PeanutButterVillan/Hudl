package com.gvs.framework.cukes.bdd.regression.registration;

import com.gvs.framework.config.ScenarioContext;
import com.gvs.framework.cukes.TestBase;
import com.gvs.framework.pages.enums.AccountAccessType;
import com.gvs.framework.pages.enums.CompanyType;
import com.gvs.framework.pages.enums.EnrolmentStatusType;
import com.gvs.framework.util.misc.CharityNumber;
import com.gvs.framework.pages.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class CharityStepDefs extends TestBase {
//  private static final Logger log = Logger.getLogger(CharityStepDefs.class);

  private static final String SIGNIN_BUTTON_TEXT = "Sign in";
  private static final String COMPLY_REACH_TEXT = "Comply with UK REACH";
  private static final String YOUR_DEFRA_ACCOUNT_TEXT = "Your Defra account";
  private static final String YOUR_ACCOUNT_TEXT = "Your Account";

  @Autowired
  private CharityNumber charityNumber;
  protected By charityLink = By.id("charity-details");
  @Given("^I login as the first user$")
  public void given_I_login_as_the_first_user() {
    //driver.get(regressionPropertyManager.getDemoServiceUrl());
    govGatewayLoginPage.createNewAccount();
  }

  @Given("^I login as the second user$")
  public void given_I_login_as_the_second_user() {
    given_I_login_as_the_first_user();
  }

  @When("^I login as the first user again$")
  public void when_I_login_as_the_first_user_again() {
    //driver.get(regressionPropertyManager.getDemoServiceUrl());
    govGatewayLoginPage.login(scenarioContext.getValue(ScenarioContext.USER_ID_KEY),
            scenarioContext.getValue(ScenarioContext.USER_PASSWORD_KEY));
    resumePage.clickContinue();
  }

  @When("^I create a business account for charity$")
  public void when_i_create_a_business_account_for_charity() {


//      scpPage.selectAccessType(AccountAccessType.BUSINESS);
  //    scpPage.selectCompanyType(CompanyType.CHARITY);
  }

  @When("^I create a \"charity\" account and reach the task list$")
  public void when_I_create_a_charity_account_and_reach_the_task_list() {
    when_i_create_a_business_account_for_charity();
  }

  @When("^I enter details for (a new|the same) charity named \"([^\"]*)\" registered in \"([^\"]*)\" in all task list sections$")
  public void when_i_enter_details_for_X_charity_named_Y_registered_in_Z_in_all_task_list_sections(String newOrTheSame, String charityName, String countryName) {
    CharityNumber.Country country = CharityNumber.Country.byName(countryName);
    boolean isDuplicate = "the same".equals(newOrTheSame);
    addCharityDetails(country, isDuplicate, charityName);
  }

  @When("^I enter details for a new charity named \"([^\"]*)\" registered in \"([^\"]*)\"$")
  public void when_I_enter_details_for_a_new_charity_named_X_registered_in_Y(String charityName, String countryName) {
    when_I_enter_details_for_a_new_charity_named_X_registered_in_Y_without_submitting(charityName,countryName);
    accountManagementRegistrationPage.submit();
  }

  @When("^I enter details for a new charity named \"([^\"]*)\" registered in \"([^\"]*)\" without submitting$")
  public void when_I_enter_details_for_a_new_charity_named_X_registered_in_Y_without_submitting(String charityName, String countryName) {
  //  scpPage.selectCompanyType(CompanyType.CHARITY);
    CharityNumber.Country country = CharityNumber.Country.byName(countryName);
    addCharityBusinessDetails(country, Boolean.parseBoolean(charityNumber.getNewCharityNumber(country)), charityName);
  }

  @When("^I enter details for the same charity named \"([^\"]*)\" registered in \"([^\"]*)\"$")
  public void when_I_enter_details_for_the_same_charity_named_X_registered_in_Y(String charityName, String countryName) {
  //  scpPage.selectCompanyType(CompanyType.CHARITY);
    CharityNumber.Country country = CharityNumber.Country.byName(countryName);
    addCharityBusinessDetails(country, Boolean.parseBoolean(charityNumber.getExistingCharityNumber(country)), charityName);
    accountManagementRegistrationPage.submit();
  }


  @When("^I re-enter the same charity details as before$")
  public void i_re_enter_the_same_charity_details_as_before() {
   // scpPage.selectCompanyType(CompanyType.CHARITY);
    charityYourOrgDetailsPage.inputCharityNumber(CharityNumber.Country.ENGLAND, charityNumber.getExistingCharityNumber(CharityNumber.Country.ENGLAND));
  }

  @When("^I enter details for an already registered charity$")
  public void i_enter_details_for_an_already_registered_charity() {
    charitySummaryPage.clickCharityDetailsLink();
    charityYourOrgDetailsPage.inputCharityNumber(CharityNumber.Country.NORTHERN_IRELAND, "NIC123456");

  }

  @When("^I check my details and enroll the account$")
  public void i_navigate_to_Check_your_information_page_and_select_Continue() {
    charitySummaryPage.clickCheckYourAnswers();
    driverUtils.clickPageTransitionElement(BasePage.GENERIC_CONTINUE_BUTTON_2);
  }

  @When("^I log out from the summary page before enrolling the account$")
  public void the_initial_user_logs_out() {
    charitySummaryPage.clickSignOut();
  }

  @Then("^I log out after enrollment$")
  public void i_log_out_after_registration() {
    //demoEnrolmentPage.logout();
  }

  @Then("^I should see an error that the organisation named \"([^\"]*)\" already exists$")
  public void User_A_should_be_displayed_an_error_Organisation_already_exists(String charity) {
    then_i_should_see_an_error_Organisation_already_exists();
  }

  @Then("^I should see an error that the Organisation already exists$")
  public void then_i_should_see_an_error_Organisation_already_exists() {
    Assert.assertTrue(charitySummaryPage.hasOrganisationAlreadyExistsError());
  }



  @When("^I input \"([^\"]*)\" for my charity number registered in \"([^\"]*)\"$")
  public void input_charity_number(String charityNumber, String countryName) {
    CharityNumber.Country county = CharityNumber.Country.byName(countryName);
    charitySummaryPage.clickCharityDetailsLink();
    charityYourOrgDetailsPage.inputCharityNumber(county, charityNumber);
  }

  @When("^I leave charity name empty$")
  public void i_leave_charity_name_empty() {
    charitySummaryPage.clickCharityDetailsLink();
    charityYourOrgDetailsPage.inputCharityNumber(CharityNumber.Country.ENGLAND, "000011");
    charityYourOrgDetailsPage.inputCharityName("");
  }

  private void addCharityAccountDetails(CharityNumber.Country country, String charityNumber, String charityName) {
    charitySummaryPage.clickCharityDetailsLink();
    addCharityAccountBusinessDetails(country, charityNumber, charityName);
    charitySummaryPage.clickYourNameLink();
    personalAboutYouPage.provideNameDetails("Test", "Char");
    charitySummaryPage.clickYourContactDetailsLink();
    personalAboutYouPage.provideContactDetails("12345");
  }

  private void addCharityNameDetails(CharityNumber.Country country, String charityName) {
    charitySummaryPage.clickYourNameLink();
    personalAboutYouPage.provideNameDetails("Test", "Char");
    charitySummaryPage.clickYourContactDetailsLink();
    personalAboutYouPage.provideContactDetails("12345");
  }
//CIDM 710
  private void addCharityNameDetails() {
    charitySummaryPage.clickYourNameLink();
    personalAboutYouPage.provideNameDetails(ScenarioContext.DEFAULT_FIRST_NAME, ScenarioContext.DEFAULT_LAST_NAME);
    charitySummaryPage.clickYourContactDetailsLink();
    personalAboutYouPage.provideContactDetails(ScenarioContext.DEFAULT_PHONE_NUMBER);
  }

  private void addCharityAccountDetailsManually(CharityNumber.Country country, String charityName) {
    addCharityAccountBusinessDetailsManually(country, charityName);
    charitySummaryPage.clickYourNameLink();
    personalAboutYouPage.provideNameDetails("Test", "Char");
    charitySummaryPage.clickYourContactDetailsLink();
    personalAboutYouPage.provideContactDetails("12345");

  }

  private void addCharityAccountBusinessDetailsManually(CharityNumber.Country country, String charityName) {
    charityYourOrgDetailsPage.inputCharityName(charityName);

    try {
      enterCharityDetailsManually(dataEntityManager.getNewCharityEntity());
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }

  }

  private void addCharityAccountBusinessDetails(CharityNumber.Country country, String charityNumberInput, String charityName) {
    charityYourOrgDetailsPage.inputCharityNumber(country, String.valueOf(charityNumberInput));
    charityYourOrgDetailsPage.inputCharityName(charityName);
    charityYourOrgDetailsPage.inputAddress("B1 1TT");
    charityYourOrgDetailsPage.inputCharityContact("12345", "test@charity.com");
  }

  private void addCharityNameBusinessDetails(CharityNumber.Country country, String charityName) {
    charityYourOrgDetailsPage.inputCharityName(charityName);
    charityYourOrgDetailsPage.inputAddress("B1 1TT");
    charityYourOrgDetailsPage.inputCharityContact("12345", "test@charity.com");
  }

  private void addCharityDetails(CharityNumber.Country country, boolean setAsDuplicate, String charityName) {
    charitySummaryPage.clickCharityDetailsLink();
    addCharityBusinessDetails(country, setAsDuplicate, charityName);
    charitySummaryPage.clickYourNameLink();
    personalAboutYouPage.provideNameDetails("Test", "Char");
    charitySummaryPage.clickYourContactDetailsLink();
    personalAboutYouPage.provideContactDetails("12345");
  }
  private void addCharityBusinessDetails(CharityNumber.Country country, boolean charityNumberInput, String charityName) {
    charityYourOrgDetailsPage.inputCharityNumber(country, String.valueOf(charityNumberInput));

    charityYourOrgDetailsPage.inputCharityName(charityName);
    charityYourOrgDetailsPage.inputAddress("B1 1TT");
    charityYourOrgDetailsPage.inputCharityContact("12345", "test@charity.com");
  }

  @When("I enter details for a new charity named {string} registered in {string} with {string}")
  public void iEnterDetailsForANewCharityNamedRegisteredInWith(String charityName, String countryName, String charityNumber) {
    CharityNumber.Country country = CharityNumber.Country.byName(countryName);
    addCharityAccountDetails(country, charityNumber, charityName);
    registrationPage.enterSecurityWord(ScenarioContext.DEFAULT_SECURITY_WORD, ScenarioContext.DEFAULT_SECURITY_HINT);
  }

  @And("I check my details and enrol the account")
  public void iCheckMyDetailsAndEnrolTheAccount() {
    charitySummaryPage.clickCheckYourAnswers();
    accountManagementRegistrationPage.submit();

  }

  @When("I manually enter details for a new charity named {string} registered in {string} with {string}")
  public void iManuallyEnterDetailsForANewCharityNamedRegisteredInWith(String charityName, String countryName, String charityNumber) {
    CharityNumber.Country country = CharityNumber.Country.byName(countryName);
    addCharityAccountDetailsManually(country, charityName);
    registrationPage.enterSecurityWord(ScenarioContext.DEFAULT_SECURITY_WORD, ScenarioContext.DEFAULT_SECURITY_HINT);
  }

  @When("I enter details for a new charity named {string} registered in {string} with charity_number")
  public void iEnterDetailsForANewCharityNamedRegisteredInWithCharity_number(String charityName, String countryName) {
    CharityNumber.Country country = CharityNumber.Country.byName(countryName);

    if (driverUtils.elementExists(charityLink)) {
      driverUtils.clickPageTransitionElement(charityLink);
    }
    registrationPage.RelatedOrgInputCharityNumber(countryName, registrationPage.getAlphaNumericString(country));
    addCharityNameBusinessDetails(country, charityName);
    addCharityNameDetails(country, charityName);
    registrationPage.enterSecurityWord(ScenarioContext.DEFAULT_SECURITY_WORD, ScenarioContext.DEFAULT_SECURITY_HINT);
  }

  @When("I manually enter details for a new charity named {string} registered in {string} with charity_number")
  public void iManuallyEnterDetailsForANewCharityNamedRegisteredInWithCharity_number(String charityName, String countryName) {
    CharityNumber.Country country = CharityNumber.Country.byName(countryName);

    if (driverUtils.elementExists(charityLink)) {
      driverUtils.clickPageTransitionElement(charityLink);
    }
    registrationPage.RelatedOrgInputCharityNumber(countryName, registrationPage.getAlphaNumericString(country));
    addCharityAccountDetailsManually(country, charityName);
    addCharityNameDetails(country, charityName);
    registrationPage.submit();
  }

  @And("I check my details for charity and enrol the account")
  public void iCheckMyDetailsForCharityAndEnrolTheAccount() {

  }

  @When("I select country {string} and enter duplicate charity number {string}")
  public void i_select_country_and_enter_duplicate_charity_number(String countryName, String duplicateCharityNumber) {


    addCharityNameDetails();
    registrationPage.enterSecurityWord(ScenarioContext.DEFAULT_SECURITY_WORD, ScenarioContext.DEFAULT_SECURITY_HINT);
    input_charity_number(duplicateCharityNumber, countryName);
    nplusOnePages.charityNplusOneJourney();
    registrationPage.submit();
  }


  //711
  @Given("I select Option{string} on Page {string}")
  public void i_select_Option_on_Page(String option, String page) {

    addCharityNameDetails();
    registrationPage.enterSecurityWord(ScenarioContext.DEFAULT_SECURITY_WORD, ScenarioContext.DEFAULT_SECURITY_HINT);
    input_charity_number( ScenarioContext.DEFAULT_DUPLICATE_CHARITY_NUMBER,ScenarioContext.DEFAULT_CHARITY_COUNTRY);
    nplusOnePages.confirmButton.click();
    nplusOnePages.noRadioButton.click();
    nplusOnePages.yesNoButtonSelection(option);
  }

  @Then("I should see Page : {string}")
  public void i_should_see_Page(String expTxt) throws Throwable {
    String actTxt= driverUtils.waitForElementToBeClickable(nplusOnePages.nPlusPageLocs(expTxt.trim().replaceAll("\\s+",""))).getText();
    assertEquals(expTxt.trim().replaceAll("\\s+","").toLowerCase(),actTxt.trim().replaceAll("\\s+","").toLowerCase());
  }
  //578
  @Then("Page should display text: {string}")
  public void page_should_display_text(String expText) {
    driverUtils.waitForElementToBeClickable(nplusOnePages.noServiceTextDispaly);
    String actText=nplusOnePages.noServiceTextDispaly.getText();
//    log.info("Actual Text: " + actText + "  " + "Expected Text: " + expText);
    Assert.assertTrue(actText.trim().replaceAll("\\s+", " ").contains(expText.trim().replaceAll("\\s+", " ")));
  }

  //709
  @When("I select country {string} and enter charity number {string} and reach {string} page")
  public void i_select_country_and_enter_charity_number_and_reach_page(String country, String duplicateCharityNumber, String expPage) {


    addCharityNameDetails();
    registrationPage.enterSecurityWord(ScenarioContext.DEFAULT_SECURITY_WORD, ScenarioContext.DEFAULT_SECURITY_HINT);
    input_charity_number( ScenarioContext.DEFAULT_DUPLICATE_CHARITY_NUMBER,ScenarioContext.DEFAULT_CHARITY_COUNTRY);
    nplusOnePages.charityNplusOneJourney();
    registrationPage.clickCheckDetails();
    driverUtils.waitForElementToBeClickable(registrationPage.taskListPageTitle);
    String actTxt= registrationPage.taskListPageTitle.getText();
    assertTrue("ExpectedText: "+expPage +"  ActualText: "+actTxt,expPage.equalsIgnoreCase(actTxt));
  }
  @Then("I should Not see {string} on Page {string}")
  public void i_should_Not_see_on_Page(String expTxt, String expPage) {
    boolean expTextCondition=false;
    try {
      WebElement   optionsToSelect = driverFactory.getInstance().findElement(By.xpath(".//*[@id='main-content']//*"));
      List<WebElement> optionsToSelects= optionsToSelect.findElements(By.tagName("dt"));
      if (optionsToSelects.size()==5) {
        for (WebElement option : optionsToSelects) {
            if (option.getText().equals(expTxt)) {
              expTextCondition=true;
            break;
          }}
      }
      else{
        expTextCondition=true;
      }
      assertFalse("Check Your Information Page should display: "+expTxt,expTextCondition);
    }
    catch (Exception e) {
     e.printStackTrace();
    }
  }

  @And("the user clicks on the sign out link")
  public void theUserClicksOnTheSignOutLink() {

  //  demoEnrolmentPage.logout();

    //ASSERT on Your Defra account

    assertTrue("DEFRA services available are missing",
            driverUtils.isStringVisible(YOUR_DEFRA_ACCOUNT_TEXT));

    //driverUtils.clickByText("a",SIGNIN_BUTTON_TEXT);
    assertFalse(driverUtils.isStringVisible(SIGNIN_BUTTON_TEXT));
    driverUtils.clickByText("a", COMPLY_REACH_TEXT);

    driverUtils.waitUntilPageFullyLoaded();
    driverUtils.clickByText("a", YOUR_ACCOUNT_TEXT);

    String charity1GGID = scenarioContext.getValue("CharityGGID");

    String charityPassword = ScenarioContext.DEFAULT_PASSWORD;

    govGatewayLoginPage.login(charity1GGID, charityPassword);

  }

  @Then("I see account {string} for service {string}")
  public void iSeeAccountForService(String accountName, String subscribedService) {
    assertTrue("Incorrect account name is being displayed",
            driverUtils.isStringVisible(accountName));
    assertTrue("Incorrect service is subscribed",
            driverUtils.isStringVisible(subscribedService));

  }
}
