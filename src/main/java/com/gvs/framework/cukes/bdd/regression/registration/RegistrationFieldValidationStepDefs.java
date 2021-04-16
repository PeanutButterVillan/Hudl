package com.gvs.framework.cukes.bdd.regression.registration;

import com.gvs.framework.config.ScenarioContext;
import com.gvs.framework.cukes.TestBase;
import com.gvs.framework.pages.enums.FieldErrorType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import org.apache.log4j.Logger;

import static org.junit.Assert.assertTrue;

public class RegistrationFieldValidationStepDefs extends TestBase {
//    private final Logger log = Logger.getLogger(RegistrationFieldValidationStepDefs.class);

    @When("^I input \"([^\"]*)\" as my name$")
    public void input_first_name(String firstName) {
        registrationPage.enterName(firstName,"Lastname");
    }
    @When("^I input \"([^\"]*)\" as my telephone number$")
    public void input_telephone_number(String telephoneNumber) {
        registrationPage.enterContactDetails(telephoneNumber);
    }
    @When("^I input \"([^\"]*)\" as my UK postcode along with my other address details$")
    public void when_I_input_UK_postcode(String postcode) {
        registrationPage.enterAddress(postcode);
    }
    @When("^I try to input \"([^\"]*)\" as my UK postcode along with my other address details$")
    public void when_I_try_to_input_UK_postcode(String postcode) {
        registrationPage.enterPostCodeAndSubmit(postcode);
    }

    @When("^I manually input \"([^\"]*)\" as my postcode$")
    public void when_I_manually_input_X_as_my_postcode(String postcode) {
        registrationPage.enterAddressManually(
                ScenarioContext.DEFAULT_SUB_BUILDING_NAME,
                ScenarioContext.DEFAULT_BUILDING_NUMBER,
                ScenarioContext.DEFAULT_BUILDING_NAME,
                ScenarioContext.DEFAULT_STREET,
                ScenarioContext.DEFAULT_TOWN,
                ScenarioContext.DEFAULT_COUNTY,
                postcode,
                ScenarioContext.DEFAULT_COUNTRY);
    }

    @When("^I input \"([^\"]*)\" as my international postcode along with my other address details$")
    public void input_international_postcode(String postcode) {
      registrationPage.enterNonUKCompanyNameAndAddress(
              ScenarioContext.DEFAULT_COMPANY_NAME,
              ScenarioContext.DEFAULT_SUB_BUILDING_NAME,
              ScenarioContext.DEFAULT_BUILDING_NUMBER,
              ScenarioContext.DEFAULT_BUILDING_NAME,
              ScenarioContext.DEFAULT_STREET,
              ScenarioContext.DEFAULT_TOWN,
              postcode,
              ScenarioContext.DEFAULT_COUNTRY);

    }


    @Then("^I am taken to the postcode select page$")
    public void i_am_taken_to_the_postcode_select_page() {
      String charityPostcodeURL = "/postcode";
      assertTrue(driver.getCurrentUrl().contains(charityPostcodeURL));
    }

    @Then("^I am taken to the charity name page$")
    public void i_am_taken_to_the_charity_name_page() {
      String charityNameURL = "/charity-name";

      assertTrue(driver.getCurrentUrl().contains(charityNameURL));
    }

    @Then("^I see the \"(name|contact-details|address|security-word)\" section marked completed$")
    public void then_I_see_the_X_section_marked_completed(String section) {
      assertTrue(registrationPage.isMarkedCompleted(section));
    }

    @Then("^I am taken to the business contact details page$")
    public void then_I_am_taken_to_business_contact_details_page() {
        assertTrue(driver.getCurrentUrl().contains("/non-uk-organisation-details/business-contact-details"));
    }

    @Then("^I see a \"([^\"]*)\" error on the \"([^\"]*)\" field$")
    public void then_I_see_a_X_error_on_the_Y_field(String error, String fieldId) {
      FieldErrorType fieldErrorType = FieldErrorType.valueOf(error);
//      log.info("errorType: " + fieldErrorType.name());
      assertTrue(registrationPage.hasFieldError(fieldErrorType, fieldId));
    }

}
