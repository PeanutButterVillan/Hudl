package com.gvs.framework.cukes.bdd.integration;

import com.gvs.framework.config.ScenarioContext;
import com.gvs.framework.cukes.TestBase;
import com.gvs.framework.pages.enums.AccountAccessType;
import com.gvs.framework.pages.enums.CompanyType;

import cucumber.api.java.en.When;
//import org.apache.log4j.Logger;




/**
 * IdentityLandingPage > GovGateway > RegistrationEmailPage > RegistrationUserPage > RegistrationTypePage > RegistrationDetailsPage
 */
public class RegistrationStepDefs extends TestBase {
//    private static Logger logger = Logger.getLogger(RegistrationStepDefs.class);

    @When("^I create a new user account$")
    public void when_i_create_a_new_user_account()  {
        int errorCount = 0;

        driverUtils.waitUntilPageFullyLoaded(); // Wait until page is actually loaded to figure out whether something has gone wrong
        while(driverUtils.isStringVisible("Site can't be reached")){
            errorCount++;
//            logger.warn("Encountered error during page load: \"Site can't be reached\". \nError count: " + errorCount
//                    + "\nRefreshing page and retrying.");
            driver.navigate().refresh();
            try {
                Thread.sleep(1000); //Wait until the dom cache references the next page to ensure consistency
            } catch (InterruptedException e) { e.printStackTrace(); }
            driverUtils.waitUntilPageFullyLoaded();
            if(errorCount>=3){
                break;
            }
        }

        govGatewayLoginPage.createNewAccount();
    }


}