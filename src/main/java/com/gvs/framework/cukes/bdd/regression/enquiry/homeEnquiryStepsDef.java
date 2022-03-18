package com.gvs.framework.cukes.bdd.regression.enquiry;

import com.gvs.framework.cukes.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

import static junit.framework.TestCase.assertTrue;





import com.gvs.framework.cukes.TestBase;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

import static junit.framework.TestCase.assertTrue;
import com.gvs.framework.cukes.TestBase;
import com.gvs.framework.cukes.bdd.regression.enquiry.homeEnquiryStepsDef;

import com.gvs.framework.pages.search.HomeEnquiryForm;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import com.gvs.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

import static junit.framework.TestCase.assertTrue;
public class homeEnquiryStepsDef extends TestBase {


        private static final Logger log = Logger.getLogger(homeEnquiryStepsDef.class);
        @Autowired

        @Given("^I am on the home enquiry page$")
        public void visitHomepage() {

            driver.get(regressionPropertyManager.getPolicyExpertServiceUrl());
        }

        @When("I enter a title \"([^\"]*)\"$")
        public void iEnterTitle(String title) {
            homeEnquiryPage.selectTitle(title);
        }

        @And("^I enter first name \"([^\"]*)\"$")
        public void searchFor(String firstName) {

            homeEnquiryPage.inputFirstName(firstName);

        }

        @And("^I input \"([^\"]*)\" as my second name$")
        public void given_I_input_a_second_name(String secondName) {
            homeEnquiryPage.inputLastName(secondName);

        }

        @Then("^the page should contain \"(.*)\"$")
        public void checkTitle(String query) {

            log.info("ASSERTION on home enquiry");
            assertTrue(homeEnquiryPage.verifyContents(query));

        }

        @And("^I input \"([^\"]*)\" as my birth day$")
        public void iInputAsMyBirthDay(String dateOfBirth) {
            String[] dob = dateOfBirth.split("/");
            homeEnquiryPage.inputDateOfBirth(dob[0], dob[1], dob[2]);
    }
}

