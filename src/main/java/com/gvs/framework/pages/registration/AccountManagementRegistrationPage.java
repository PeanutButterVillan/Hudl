package com.gvs.framework.pages.registration;

import com.gvs.framework.util.driver.DriverFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class AccountManagementRegistrationPage extends RegistrationPage {

    public void enterAddress(String postcode){ super.enterAddress(postcode); }

    public void enterCompanyName(String companyName){ super.enterCompanyname(companyName); }

    public void enterCompanyContactDetails(String companyPhoneNumber, String companyEmailAddress){
        companyPhoneNumberField.sendKeys(companyPhoneNumber);
        companyEmailAddressField.sendKeys(companyEmailAddress);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

    public void enterFullCompanyDetails(String companyNumber, String companyPhoneNumber, String companyEmailAddress){
        companyNumberField.sendKeys(companyNumber);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
        enterCompanyContactDetails(companyPhoneNumber,companyEmailAddress);
    }

    public void submit(){
        super.completeCaptchaAndSubmit();
    }

    public AccountManagementRegistrationPage(DriverFactory driverFactory) { super(driverFactory); }
}
