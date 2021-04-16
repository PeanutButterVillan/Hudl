package com.gvs.framework.cukes;

import com.gvs.framework.config.ScenarioContext;
import com.gvs.framework.config.SpringContext;

import com.gvs.framework.config.regression.RegressionPropertyManager;
import com.gvs.framework.data.DataEntityManager;
import com.gvs.framework.data.pojo.DataEntity;
import com.gvs.framework.model.AdminLogInInfo;


import com.gvs.framework.pages.racecards.BigRaceEntries;
import com.gvs.framework.pages.racecards.RacingCards;
import com.gvs.framework.pages.registration.AccountManagementRegistrationPage;
import com.gvs.framework.pages.registration.RegistrationPage;


import com.gvs.framework.pages.search.HomeSearchPage;
import com.gvs.framework.pages.springerNature.SpringLinkPage;
import com.gvs.framework.pages.equalExperts.equalExpertPage;
import com.gvs.framework.util.driver.DriverFactory;
import com.gvs.framework.util.driver.DriverUtils;

import com.gvs.framework.pages.misc.*;
import com.gvs.framework.pages.registration.*;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class TestBase {
    private static Logger log = Logger.getLogger(TestBase.class);

    //UTILS FOR DRIVER, TEST, SPRING
    @Autowired
    protected SpringContext springContext;
    @Autowired
    protected ScenarioContext scenarioContext;

    @Autowired
    protected RegressionPropertyManager regressionPropertyManager;
    @Autowired
    protected DriverUtils driverUtils;
    @Autowired
    protected DriverFactory driverFactory;
    @Autowired
    protected RemoteWebDriver driver;


    @Autowired
    protected DataEntityManager dataEntityManager;



    @Autowired
    protected RegistrationPage registrationPage;
    @Autowired
    protected AccountManagementRegistrationPage accountManagementRegistrationPage;


    @Autowired
    protected AddSiblingAccountErrorPage addSiblingAccountErrorPage;
    @Autowired
    protected AdminLogInInfo adminLogInInfo;

    @Autowired
    protected CharitySummaryPage charitySummaryPage;
    @Autowired
    protected CharityYourOrgDetailsPage charityYourOrgDetailsPage;


    @Autowired
    protected HomeSearchPage homeSearchPage;
    @Autowired
    protected BigRaceEntries bigRaceEntries;
    @Autowired
    protected RacingCards racingCards;

    @Autowired
    protected CheckYourInformationPage checkYourInformationPage;

    @Autowired
    protected GovGatewayLoginPage govGatewayLoginPage;

    @Autowired
    protected SpringLinkPage springLinkPage;

    @Autowired
    protected equalExpertPage equalExpertsPage;

    @Autowired
    protected PersonalAboutYouPage personalAboutYouPage;
    @Autowired
    protected PrivacyNoticeUpdatePage privacyNoticeUpdatePage;
    @Autowired
    protected ResumePage resumePage;

    @Autowired
    protected NplusOnePages nplusOnePages;
    @Autowired
    protected SignInSignOutPage signInSignOutPage;



    protected void enterCharityDetailsManually(String firstName,
                                               String lastName,
                                               String contactNumber,
                                               String companyName,
                                               String subBuildingName,
                                               String buildingNumber,
                                               String buildingName,
                                               String street,
                                               String townOrCity,
                                               String postcode,
                                               String country,
                                               String emailID,
                                               String securityWord,
                                               String securityHint) throws Throwable {

        registrationPage.enterRandomCharityDetailsManuallyWithRetry(contactNumber, companyName, subBuildingName, buildingNumber, buildingName,
                street, townOrCity, postcode, country, contactNumber, emailID);
        registrationPage.enterSecurityWord(ScenarioContext.DEFAULT_SECURITY_WORD, ScenarioContext.DEFAULT_SECURITY_HINT);

    }

    protected void enterCharityDetails(String firstName,
                                       String lastName,
                                       String contactNumber,
                                       String companyName,
                                       String subBuildingName,
                                       String buildingNumber,
                                       String buildingName,
                                       String street,
                                       String townOrCity,
                                       String postcode,
                                       String country,
                                       String emailID,
                                       String securityWord,
                                       String securityHint) throws Throwable {
        registrationPage.enterName(firstName, lastName);
        registrationPage.enterContactDetails(contactNumber);
        registrationPage.enterRandomCharityDetailsWithRetry(contactNumber, companyName, subBuildingName, buildingNumber, buildingName,
                street, townOrCity, postcode, country, contactNumber, emailID);
        registrationPage.enterSecurityWord(ScenarioContext.DEFAULT_SECURITY_WORD, ScenarioContext.DEFAULT_SECURITY_HINT);
        registrationPage.submit();
    }

    /*
   Below Method verify whether relevant data input entered in "Manual address" populated or not in "Check your Information" page
    */
    protected void enterSoleTraderDetailsManualAddress(String firstName, String lastName, String contactNumber, String companyName,
                                                       String subBuildingName, String buildingNumber, String buildingName, String street,
                                                       String townOrCity, String postCode, String emailID, String securityWord, String securityHint) {
        registrationPage.enterSoleTraderName(firstName, lastName);
        registrationPage.enterSoleTraderContactDetails(contactNumber);
        registrationPage.enterSoleTraderCompanyDetailsManualAddress(companyName, subBuildingName, buildingNumber, buildingName,
                street, townOrCity, postCode, contactNumber, emailID);
        registrationPage.enterSoleTraderSecurityWord(securityWord, securityHint);
        registrationPage.clickCheckDetails();
        String actManualAddress = driverFactory.getInstance().findElement(By.xpath("//*[contains(text(), 'Business trading address')]/following-sibling::dd[1]")).getText();
//        log.info("ActualManualAddress" + actManualAddress.trim().replaceAll("\\s+", " "));
//        log.info("ExpectedManualAddress" + "Girona Building");
        Assert.assertTrue(actManualAddress.trim().replaceAll("\\s+", " ").contains("Girona Building".trim().replaceAll("\\s+", " ")));
        registrationPage.completeCaptchaAndSubmit();
    }

    //573
    //Below Method enters all relevant data until TaskList page
    protected void enterSoleTraderDetailsUpToTaskList(String firstName, String lastName, String contactNumber, String companyName,
                                                      String subBuildingName, String buildingNumber, String buildingName, String street,
                                                      String townOrCity, String postCode, String emailID) {
        registrationPage.enterSoleTraderName(firstName, lastName);
        registrationPage.enterSoleTraderContactDetails(contactNumber);
        registrationPage.enterSoleTraderCompanyDetailsManualAddress(companyName, subBuildingName, buildingNumber, buildingName,
                street, townOrCity, postCode, contactNumber, emailID);
    }

    protected void enterSoleTraderDetails(String firstName,
                                          String lastName,
                                          String contactNumber,
                                          String companyName,
                                          String postcode,
                                          String emailID,
                                          String securityWord,
                                          String securityHint) {
        registrationPage.enterSoleTraderName(firstName, lastName);
        registrationPage.enterSoleTraderContactDetails(contactNumber);
        registrationPage.enterSoleTraderCompanyDetails(companyName, postcode, contactNumber, emailID);
        registrationPage.enterSoleTraderSecurityWord(securityWord, securityHint);
        registrationPage.submit();
    }



    protected void enterNonUkOrgCompanyDetails(String firstName,
                                               String lastName,
                                               String contactNumber,
                                               String companyName,
                                               String subBuildingName,
                                               String buildingNumber,
                                               String buildingName,
                                               String street,
                                               String townOrCity,
                                               String postcode,
                                               String country,
                                               String emailID,
                                               String securityWord,
                                               String securityHint) {
        registrationPage.enterName(firstName, lastName);
        registrationPage.enterContactDetails(contactNumber);
        registrationPage.enterNonUKCompanyDetails(companyName, subBuildingName, buildingNumber, buildingName,
                street, townOrCity, postcode, country, contactNumber, emailID);
        registrationPage.enterSecurityWord(securityWord, securityHint);
        registrationPage.submit();
    }

    protected void enterCharityDetailsManually(DataEntity dataEntity) throws Throwable {
        enterCharityDetailsManually(dataEntity.getAdminOrDirector().getFirstName(),
                dataEntity.getAdminOrDirector().getSecondName(),
                dataEntity.getAdminOrDirector().getContactNumber(),
                dataEntity.getBusinessDetails().getCompanyName(),
                dataEntity.getAddress().getSubBuildingName(),
                dataEntity.getAddress().getBuildingNumber(),
                dataEntity.getAddress().getBuildingName(),
                dataEntity.getAddress().getStreetName(),
                dataEntity.getAddress().getTownOrCity(),
                dataEntity.getAddress().getPostCode(),
                dataEntity.getAddress().getCountry(),
                dataEntity.getBusinessDetails().getBusinessEmailId(),
                ScenarioContext.DEFAULT_SECURITY_WORD,
                ScenarioContext.DEFAULT_SECURITY_HINT);
    }

    protected void enterCharityDetails(DataEntity dataEntity) throws Throwable {
        enterCharityDetails(dataEntity.getAdminOrDirector().getFirstName(),
                dataEntity.getAdminOrDirector().getSecondName(),
                dataEntity.getAdminOrDirector().getContactNumber(),
                dataEntity.getBusinessDetails().getCompanyName(),
                dataEntity.getAddress().getSubBuildingName(),
                dataEntity.getAddress().getBuildingNumber(),
                dataEntity.getAddress().getBuildingName(),
                dataEntity.getAddress().getStreetName(),
                dataEntity.getAddress().getTownOrCity(),
                dataEntity.getAddress().getPostCode(),
                dataEntity.getAddress().getCountry(),
                dataEntity.getBusinessDetails().getBusinessEmailId(),
                ScenarioContext.DEFAULT_SECURITY_WORD,
                ScenarioContext.DEFAULT_SECURITY_HINT);
    }

    protected void enterSoleTraderDetails(DataEntity dataEntity) {
        enterSoleTraderDetails(dataEntity.getAdminOrDirector().getFirstName(),
                dataEntity.getAdminOrDirector().getSecondName(),
                dataEntity.getAdminOrDirector().getContactNumber(),
                dataEntity.getBusinessDetails().getCompanyName(),
                dataEntity.getAddress().getPostCode(),
                dataEntity.getBusinessDetails().getBusinessEmailId(),
                ScenarioContext.DEFAULT_SECURITY_WORD,
                ScenarioContext.DEFAULT_SECURITY_HINT);
    }



    protected void enterNonUkOrgCompanyDetails(DataEntity dataEntity) {
        enterNonUkOrgCompanyDetails(dataEntity.getAdminOrDirector().getFirstName(),
                dataEntity.getAdminOrDirector().getSecondName(),
                dataEntity.getAdminOrDirector().getContactNumber(),
                dataEntity.getBusinessDetails().getCompanyName(),
                dataEntity.getAddress().getSubBuildingName(),
                dataEntity.getAddress().getBuildingNumber(),
                dataEntity.getAddress().getBuildingName(),
                dataEntity.getAddress().getStreetName(),
                dataEntity.getAddress().getTownOrCity(),
                dataEntity.getAddress().getPostCode(),
                dataEntity.getAddress().getCountry(),
                dataEntity.getBusinessDetails().getBusinessEmailId(),
                ScenarioContext.DEFAULT_SECURITY_WORD,
                ScenarioContext.DEFAULT_SECURITY_HINT);
    }

    /*
    Below Method fetch appropriate TestData for from dataEntity on fly
     */
    protected void enterSoleTraderDetailsManualAddress(DataEntity dataEntity) {
        enterSoleTraderDetailsManualAddress(dataEntity.getAdminOrDirector().getFirstName(),
                dataEntity.getAdminOrDirector().getSecondName(),
                dataEntity.getAdminOrDirector().getContactNumber(),
                dataEntity.getBusinessDetails().getCompanyName(),
                dataEntity.getAddress().getSubBuildingName(),
                dataEntity.getAddress().getBuildingNumber(),
                dataEntity.getAddress().getBuildingName(),
                dataEntity.getAddress().getStreetName(),
                dataEntity.getAddress().getTownOrCity(),
                dataEntity.getAddress().getPostCode(),
                dataEntity.getBusinessDetails().getBusinessEmailId(),
                ScenarioContext.DEFAULT_SECURITY_WORD,
                ScenarioContext.DEFAULT_SECURITY_HINT);
    }

    //573
    protected void enterSoleTraderDetailsUpToTaskList(DataEntity dataEntity) {
        enterSoleTraderDetailsUpToTaskList(dataEntity.getAdminOrDirector().getFirstName(),
                dataEntity.getAdminOrDirector().getSecondName(),
                dataEntity.getAdminOrDirector().getContactNumber(),
                dataEntity.getBusinessDetails().getCompanyName(),
                dataEntity.getAddress().getSubBuildingName(),
                dataEntity.getAddress().getBuildingNumber(),
                dataEntity.getAddress().getBuildingName(),
                dataEntity.getAddress().getStreetName(),
                dataEntity.getAddress().getTownOrCity(),
                dataEntity.getAddress().getPostCode(),
                dataEntity.getBusinessDetails().getBusinessEmailId());
    }

    //570
    protected void enterIndividualDetails(DataEntity dataEntity) {
        enterIndividualDetails(dataEntity.getAdminOrDirector().getFirstName(),
                dataEntity.getAdminOrDirector().getSecondName(),
                dataEntity.getAdminOrDirector().getContactNumber(),
                dataEntity.getBusinessDetails().getCompanyName(),
                dataEntity.getAddress().getSubBuildingName(),
                dataEntity.getAddress().getBuildingNumber(),
                dataEntity.getAddress().getBuildingName(),
                dataEntity.getAddress().getStreetName(),
                dataEntity.getAddress().getTownOrCity(),
                dataEntity.getAddress().getPostCode(),
                dataEntity.getAddress().getCountry(),
                dataEntity.getBusinessDetails().getBusinessEmailId(),
                ScenarioContext.DEFAULT_SECURITY_WORD,
                ScenarioContext.DEFAULT_SECURITY_HINT);
    }

    //570
    /*
   Below Method verify whether relevant data input entered in "Manual address" page for Individual type
     and cross check similar is been  populated or not in "Check your Information" page
    */
    protected void enterIndividualDetails(String firstName,
                                          String lastName,
                                          String contactNumber,
                                          String companyName,
                                          String subBuildingName,
                                          String buildingNumber,
                                          String buildingName,
                                          String street,
                                          String townOrCity,
                                          String postcode,
                                          String country,
                                          String emailID,
                                          String securityWord,
                                          String securityHint) {
        registrationPage.enterName(firstName, lastName);
        registrationPage.enterContactDetails(contactNumber);
        registrationPage.enterIndividualDetails(companyName, subBuildingName, buildingNumber, buildingName,
                street, townOrCity, postcode, country, contactNumber, emailID);
        registrationPage.enterSecurityWord(securityWord, securityHint);
        registrationPage.clickCheckDetails();
        String actManualAddress = driverFactory.getInstance().findElement(By.xpath("//*[contains(text(), 'Your address')]/following-sibling::dd[1]")).getText();
//        log.info("ActualManualAddress" + actManualAddress.trim().replaceAll("\\s+", " "));
//        log.info("ExpectedManualAddress" + "Girona Building");
        Assert.assertTrue(actManualAddress.trim().replaceAll("\\s+", " ").contains("Girona Building".trim().replaceAll("\\s+", " ")));
        registrationPage.completeCaptchaAndSubmit();
    }
}
