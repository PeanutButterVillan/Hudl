package com.gvs.framework.cukes.bdd.regression.search;

import com.gvs.framework.cukes.TestBase;
import com.gvs.framework.pages.registration.CharityYourOrgDetailsPage;
import com.gvs.framework.pages.registration.RegistrationPage;
import com.gvs.framework.pages.search.HomeSearchPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
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

import static junit.framework.TestCase.assertTrue;

public class BranchSearchSteps extends TestBase {

    private static final Logger log = Logger.getLogger(BranchSearchSteps.class);

@Autowired

    @Given("^I am on the Costcutter search page$")
    public void visitHomepage() {

    driver.get(regressionPropertyManager.getCostCutterServiceUrl());
    }

    @When("^I search for \"(.*)\"$")
    public void searchFor(String query) {

        homeSearchPage.inputSearchLocation(query);

    }

    @Then("^the page should contain \"(.*)\"$")
    public void checkTitle(String query) {

        log.info("ASSERTION on BranchSearchSteps");
        assertTrue(homeSearchPage.verifyContents(query));

    }

}
