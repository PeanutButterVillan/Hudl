package com.gvs.framework.cukes.bdd.regression.springNature;

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

public class SpringNatureStepdefs extends TestBase{

    private static final Logger log = Logger.getLogger(SpringNatureStepdefs.class);

    @Autowired
    private springNatureContentType contentType;
    @Given("^I am on the Springer Start Page$")
    public void visitHomepage() {


        driver.get(regressionPropertyManager.getSpringNatureServiceUrl());
    }

    @Given("^I input a \"([^\"]*)\"$")
    public void given_I_input_an_author_X(String authorName) {
        springLinkPage.inputAuthor(authorName);

        springNatureContentType.Type.ARTICLE.setAuthor(authorName);
        springNatureContentType.Type.BOOK.setAuthor(authorName);
        springNatureContentType.Type.CONFERENCE_PAPER.setAuthor(authorName);
    }


    @When("I click on search")
    public void iClickOnSearch() {
        springLinkPage.submitSearch();
    }

    @Then("^I see a \"([^\"]*)\" total of \"([^\"]*)\"$")
    public void iSeeATotal(String expectedNumber, String expectedType) throws ParseException {
            String actualTotal =  springLinkPage.getTypeTotal(expectedNumber, expectedType);
            log.info("Expected Type: " + expectedType + " Actual Total: " + actualTotal);
            if (expectedType.equals("Article")) {
                contentType.setNewTotalsNumber(springNatureContentType.Type.ARTICLE, actualTotal);
                springLinkPage.verifyTotal(springNatureContentType.Type.ARTICLE, expectedNumber);
            }
            else if (expectedType.equals("Book")) {
                contentType.setNewTotalsNumber(springNatureContentType.Type.BOOK, actualTotal);
                springLinkPage.verifyTotal(springNatureContentType.Type.BOOK, expectedNumber);
            }
            else if (expectedType.equals("Conference Paper")) {
                contentType.setNewTotalsNumber(springNatureContentType.Type.CONFERENCE_PAPER, actualTotal);
                springLinkPage.verifyTotal(springNatureContentType.Type.CONFERENCE_PAPER, expectedNumber);
            }





        }


}
