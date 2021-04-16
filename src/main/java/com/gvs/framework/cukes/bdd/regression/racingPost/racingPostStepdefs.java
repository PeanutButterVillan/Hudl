package com.gvs.framework.cukes.bdd.regression.racingPost;

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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static junit.framework.TestCase.assertTrue;

public class racingPostStepdefs extends TestBase {

    private static final Logger log = Logger.getLogger(racingPostStepdefs.class);

    @Autowired
    //private WebDriver driver;



    @Given("I am on the racing post race cards page")
    public void iAmOnTheRacingPostRaceCardsPage() {
        driver.get(regressionPropertyManager.getRacingPostServiceUrl());
    }

    @When("I click the {string} header")
    public void iClickTheHeader(String arg0) {
        if (arg0.equals("Big Race Entries"))
        racingCards.selectBigRaceEntries();
    }

    @Then("the date of the next big race is in the future")
    public void theDateOfTheNextBigRaceIsInTheFuture() throws ParseException {

//FUNC for TODAY's DATE on BigRaceEntries page object
Date dateToday  = bigRaceEntries.getTodayDate();

        //get monthList of BigRaces



Date date1 = bigRaceEntries.selectMonthList();


        // FUNC for verify dates on BigRaceEntries page object
assertTrue( date1.after(dateToday) );

    }

}
