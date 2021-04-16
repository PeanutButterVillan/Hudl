package com.gvs.framework.pages.misc;

import com.gvs.framework.pages.BasePage;
import com.gvs.framework.util.driver.DriverFactory;
//import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//Below class is PageObject to handle all relevant pages (Your Defra account and resume-registration) while carrying SignInSignOut Activities
@Component
@Scope("cucumber-glue")
public class SignInSignOutPage extends BasePage {

//    private static final Logger LOGGER = Logger.getLogger(SignInSignOutPage.class);
    public By elementLocator;
    //"Your Defra Account" Header text  in "Your Defra Account"Page
    @FindBy(how = How.XPATH, using = "//*[@id='main-content']//h1")
    public WebElement yourDefraAccountText;
    //"Comply with UK REACH" link in "Your Defra Account"Page
    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Comply with UK REACH')]")
    public WebElement complyReachText;
    //Continue button in "Resume Registration"
    @FindBy(how = How.ID, using = "button-continue")
    public WebElement buttonContinue;

    public SignInSignOutPage(DriverFactory driverFactory) {
        super(driverFactory);
    }

}