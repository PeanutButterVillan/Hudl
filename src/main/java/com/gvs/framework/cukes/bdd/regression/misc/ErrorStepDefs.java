package com.gvs.framework.cukes.bdd.regression.misc;

import com.gvs.framework.cukes.TestBase;
import io.cucumber.java.en.Then;
//import org.apache.log4j.Logger;

import static org.junit.Assert.assertTrue;

public class ErrorStepDefs extends TestBase {
//    private final static Logger log = Logger.getLogger(ErrorStepDefs.class);

    @Then("^I should see the \"([^\"]*)\" error for \"([^\"]*)\"$")
    public void then_I_should_see_the_Organisation_X_already_exists_error(String errorString, String organisationName) {
        assertTrue(driverUtils.isStringVisible(errorString));
        assertTrue(addSiblingAccountErrorPage.hasCorrectOrganisationName(organisationName));
    }

    @Then("^I should see the \"([^\"]*)\" error$")
    public void then_I_should_see_the_X_error(String errorString) {
        assertTrue(driverUtils.isStringVisible(errorString));
    }
}
