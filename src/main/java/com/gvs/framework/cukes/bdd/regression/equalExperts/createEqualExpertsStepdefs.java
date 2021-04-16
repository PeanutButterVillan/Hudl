package com.gvs.framework.cukes.bdd.regression.equalExperts;

import io.cucumber.java.en.Given;


import com.gvs.framework.cukes.TestBase;
import com.gvs.framework.cukes.bdd.regression.search.BranchSearchSteps;
import com.gvs.framework.pages.registration.CharityYourOrgDetailsPage;
import com.gvs.framework.pages.registration.RegistrationPage;
import com.gvs.framework.pages.search.HomeSearchPage;
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
import com.gvs.framework.util.misc.pojo.springNatureContentType;
import java.text.ParseException;

import static junit.framework.TestCase.assertTrue;


public class createEqualExpertsStepdefs extends TestBase{



    private static final Logger log = Logger.getLogger(com.gvs.framework.cukes.bdd.regression.springNature.SpringNatureStepdefs.class);

    @Autowired

    @Given("I am on the Hotel Start Page")
    public void iAmOnTheHotelStartPage() {

        driver.get(regressionPropertyManager.getEqualExpertsHotelServiceUrl());

    }


    @Given("^I input a \"([^\"]*)\" as my first name$")
    public void given_I_input_a_first_name(String firstName) {
        equalExpertsPage.inputFirstName(firstName);

    }

    @And("^I input a \"([^\"]*)\" as my second name$")
    public void given_I_input_a_second_name(String secondName) {
        equalExpertsPage.inputLastName(secondName);

    }

    @And("^I input a \"([^\"]*)\" as my deposit$")
    public void given_I_input_a_deposit(String deposit) {
        equalExpertsPage.enterDeposit(deposit);

    }

    @And("^I input a \"([^\"]*)\" as my price$")
    public void given_I_input_a_total_price(String totalPrice) {
        equalExpertsPage.inputTotalPrice(totalPrice);

    }

    @And("^I input a \"([^\"]*)\" as my check-in$")
    public void iInputAAsMyCheckIn(String arg0) throws ParseException {

        // click into the field
        // move the month on one - //*[@id="ui-datepicker-div"]/div/a[2]/span
        // how many clicks are required - months on from the curren month default ?
        // store data month
        // store date year
        // click on day required
        // need to break up the entered date into day / month / year 2022-02-10
        String[] parts = arg0.split("-");
        equalExpertsPage.selectCheckIn(parts[1],parts[0]);
        equalExpertsPage.selectDay(parts[2]);
    }

    @And("^I input a \"([^\"]*)\" as my check-out$")
    public void iInputAAsMyCheckOut(String arg0) throws ParseException {
        String[] parts = arg0.split("-");
        equalExpertsPage.selectCheckOut (parts[1],parts[0]);
        equalExpertsPage.selectDay(parts[2]);
    }

    @When("I click on save")
    public void iClickOnSave() {
        equalExpertsPage.submitSave();
    }

    @Then("I see a {string} of {string}")
    public void iSeeAOf(String arg0, String arg1) throws ParseException {
        equalExpertsPage.verifyColumnTotals(arg0,arg1);
    }

    @When("I click on delete")
    public void iClickOnDelete() {
        equalExpertsPage.submitDelete();
    }

    @Given("I identify a {string} of {string}")
    public void iIdentifyAOf(String arg0, String arg1) {
        switch(arg1)
        {
            case "Firstname":
                equalExpertsPage.setFirstName(arg0);
            case "Surname":
                equalExpertsPage.setLastName(arg0);
            case "Price":
                equalExpertsPage.setPrice(arg0);
        }
    }
}
