package com.gvs.framework.cukes.bdd.regression.login;

import com.gvs.framework.cukes.TestBase;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.gvs.framework.cukes.bdd.regression.login.hudlLoginStepsDef;

import com.gvs.framework.pages.login.HudlLoginForm;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import static junit.framework.TestCase.assertTrue;
public class hudlLoginStepsDef extends TestBase {


        private static final Logger log = Logger.getLogger(hudlLoginStepsDef.class);
        @Autowired

        @Given("^I am on the hudl login page$")
        public void visitLoginPage() {

            driver.get(regressionPropertyManager.getHudlServiceUrl());
        }

        @When("I enter my email \"([^\"]*)\"$")
        public void iEnterEmail(String email) {
            hudlLoginPage.inputEmail(email);
        }

        @And("^I input \"([^\"]*)\" as my password and submit$")
        public void I_input_a_password(String password) {
            hudlLoginPage.inputPassword(password);
            hudlLoginPage.login.click();
        }

        @Then("^the page should contain \"(.*)\"$")
        public void checkTeam(String teamName) {

            log.info("ASSERTION on logging in");
            assertTrue(hudlLoginPage.verifyContents(teamName));

        }

}

