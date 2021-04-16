package com.gvs.framework.pages.misc;

import com.gvs.framework.pages.BasePage;
import com.gvs.framework.util.driver.DriverFactory;
//import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class PrivacyNoticeUpdatePage extends BasePage {

//    private static final Logger LOGGER = Logger.getLogger(PrivacyNoticeUpdatePage.class);

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Privacy notice")
    private WebElement privacyNoticeLink;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "cookie settings")
    private WebElement cookieSettingsLink;

    @FindBy(how = How.ID, using = "acceptCookiesCheckbox")
    private WebElement acceptCookiesCheckbox;
    
    public PrivacyNoticeUpdatePage(DriverFactory driverFactory) {
        super(driverFactory);
    }

    public void clickContinue() {
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

    public String getPrivacyNoticeLink() {
        driverUtils.waitUntilPageFullyLoaded();
        //driverUtils.waitForVisibilityOf(privacyNoticeLink);
        return privacyNoticeLink.getAttribute("href");
    }

    public boolean hasCookieSettingsLink() {
        try {
            driverUtils.waitUntilPageFullyLoaded();
            //driverUtils.waitForElementToBeClickable(cookieSettingsLink);
        } catch (TimeoutException e) {

            return false;
        }
        return true;
    }
    
    public String getCookiesSettingsLinkHref() {
        return cookieSettingsLink.getAttribute("href");
    }
    
    public void acceptCookiesPolicyChange() {
        driverUtils.waitUntilPageFullyLoaded();
        acceptCookiesCheckbox.click();
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }
}
