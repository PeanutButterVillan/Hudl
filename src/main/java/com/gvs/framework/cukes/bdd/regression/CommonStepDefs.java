package com.gvs.framework.cukes.bdd.regression;

import com.gvs.framework.cukes.TestBase;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;

import static com.gvs.framework.pages.BasePage.GENERIC_CONTINUE_BUTTON_2;

public class CommonStepDefs extends TestBase {
    private static final Logger log = Logger.getLogger(CommonStepDefs.class);

    @When("^I click continue")
    public void when_I_click_continue() {
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }
}
