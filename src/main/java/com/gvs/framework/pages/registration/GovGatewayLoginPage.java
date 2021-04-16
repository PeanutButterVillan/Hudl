package com.gvs.framework.pages.registration;

import com.gvs.framework.pages.BasePage;
import com.gvs.framework.util.driver.DriverFactory;
//import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class GovGatewayLoginPage extends BasePage {
//    private static final Logger log = Logger.getLogger(GovGatewayLoginPage.class);

    private static final String START_NOW_BUTTON_TEXT = "Start now";

    private static final By USERNAME_FIELD = By.id("user_id");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By CREATE_ACCOUNT_BUTTON = By.id("no-account");

    private static final By STAY_BUTTON = By.id("Stay");
    private static final By SWITCH_BUTTON = By.id("Switch");

    private static final By CHEMICALS_START_NOW_BUTTON = By.id("btnStartNowB2C");

    private static final By SIGN_OUT_CLEAR_LINK = By.xpath("//*[@id='proposition-link']/a");

    public void login(String username, String password){
        bypassKnownPrompts(true);
        driver.findElement(USERNAME_FIELD).sendKeys(username);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_1);
        bypassKnownPrompts(false);
    }

    public void createNewAccount(){
        bypassKnownPrompts(true);
        driver.findElement(CREATE_ACCOUNT_BUTTON).click();
    }

    public void logoutCompletely(String url) {
        try {
            driver.get(url);
            if(driverUtils.elementExists(SIGN_OUT_CLEAR_LINK)){
                driverUtils.clickPageTransitionElement(SIGN_OUT_CLEAR_LINK);
                driver.get(url);
            }

        } catch (Exception ex) {
            driver.get(url);

        }
    }

    /**
     * @param switchUser if true will switch the user, if false will 'stay signed in'
     */
    public void bypassKnownPrompts(boolean switchUser){
        //Demo service bypass
        if(driverUtils.isStringVisible(START_NOW_BUTTON_TEXT)){
            driverUtils.clickByText("button",START_NOW_BUTTON_TEXT);
        }

        //"You are already logged in prompt"
        if(driverUtils.elementExists(SWITCH_BUTTON)){
            if(switchUser){
                driver.findElement(SWITCH_BUTTON).click();
            } else {
                driver.findElement(STAY_BUTTON).click();
            }
            driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_1);
        }

        //Chemicals URL landing page before login
        if(driverUtils.elementExists(CHEMICALS_START_NOW_BUTTON)){
            driverUtils.clickPageTransitionElement(CHEMICALS_START_NOW_BUTTON);
        }
    }

    public GovGatewayLoginPage(DriverFactory driverFactory) { super(driverFactory); }
}
