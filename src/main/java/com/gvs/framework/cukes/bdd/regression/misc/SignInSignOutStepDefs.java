package com.gvs.framework.cukes.bdd.regression.misc;

import com.gvs.framework.cukes.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import org.apache.log4j.Logger;
import static org.junit.Assert.*;

public class SignInSignOutStepDefs extends TestBase {
//    private final static Logger log = Logger.getLogger(SignInSignOutStepDefs.class);
    private static final String SIGNIN_BUTTON_TEXT = "Sign in";
    private static final String YOUR_DEFRA_ACCOUNT_TEXT = "Your Defra account";

    @Then("I should Not see {string}")
    public void i_should_Not_see(String expText) {
        assertEquals(YOUR_DEFRA_ACCOUNT_TEXT.trim().replaceAll("\\s+", "").toLowerCase(), signInSignOutPage.yourDefraAccountText.getText().trim().replaceAll("\\s+", "").toLowerCase());
        assertFalse(driverUtils.isStringVisible(SIGNIN_BUTTON_TEXT));
    }

    @When("I click ServiceLink:{string}")
    public void i_click_ServiceLink(String expLoc) {
        signInSignOutPage.complyReachText.click();
    }

    @Given("I SignIn back and click Continue on resume-registration page")
    public void i_SignIn_back_and_click_Continue_on_resume_registration_page() {
        //govGatewayLoginPage.login(scpPage.ggUserId, dataEntityManager.getNewSoleTraderEntity().getAdminOrDirector().getPassword());
        signInSignOutPage.buttonContinue.click();
        driverUtils.waitUntilPageFullyLoaded();
    }

    @Then("User should return to Page: {string}")
    public void user_should_return_to_Page(String expTxt) {
        assertEquals(expTxt.trim().replaceAll("\\s+", "").toLowerCase(), driver.getTitle().trim().replaceAll("\\s+", "").toLowerCase());
    }

}
