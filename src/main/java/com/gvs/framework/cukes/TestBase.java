package com.gvs.framework.cukes;

import com.gvs.framework.config.ScenarioContext;
import com.gvs.framework.config.SpringContext;

import com.gvs.framework.config.regression.RegressionPropertyManager;
import com.gvs.framework.data.DataEntityManager;
import com.gvs.framework.data.pojo.DataEntity;

import com.gvs.framework.pages.search.HomeSearchPage;

import com.gvs.framework.util.driver.DriverFactory;
import com.gvs.framework.util.driver.DriverUtils;

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
    protected HomeSearchPage homeSearchPage;

    /*
    Below Method fetch appropriate TestData for from dataEntity on fly
     */
    protected void enterCostCutterSearchDetails(DataEntity dataEntity) {
        enterCostCutterSearchDetails(
                dataEntity.getBranchDetails().getBranchTown()
                );
    }

    protected void enterCostCutterSearchDetails(String branchTown
                                          ) {
        //registrationPage.enterSoleTraderName(firstName, lastName);
        //registrationPage.enterSoleTraderContactDetails(contactNumber);
        //registrationPage.enterSoleTraderCompanyDetails(companyName, postcode, contactNumber, emailID);
        //registrationPage.enterSoleTraderSecurityWord(securityWord, securityHint);
        //registrationPage.submit();
    }

}
