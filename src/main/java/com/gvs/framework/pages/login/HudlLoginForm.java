package com.gvs.framework.pages.login;

import com.gvs.framework.pages.BasePage;

import com.gvs.framework.util.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class HudlLoginForm extends BasePage {


    @FindBy(how = How.XPATH, using = "//*[@id=\"email\"]")
    public WebElement email;

    @FindBy(how = How.XPATH, using = "//*[@id=\"logIn\"]")
    public WebElement login;

    @FindBy(how = How.XPATH, using = "//*[@id=\"password\"]")
    public WebElement password;

    public HudlLoginForm(DriverFactory driverFactory) {
        super(driverFactory);
    }

    public void clickLogin() {
        login.click();
    }

    public void inputEmail(String inEmail) {
        driverUtils.clearFieldAndSendKeys(email, inEmail);
    }

    public void inputPassword(String inPassword) {
        driverUtils.clearFieldAndSendKeys(password, inPassword);
    }

    public boolean verifyContents(String query) {
        return driverUtils.isStringVisible(query);
    }

}
