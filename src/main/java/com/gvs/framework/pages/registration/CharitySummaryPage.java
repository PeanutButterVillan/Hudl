package com.gvs.framework.pages.registration;

import com.gvs.framework.pages.BasePage;
import com.gvs.framework.util.driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class CharitySummaryPage extends BasePage {
    private static final String CHARITY_ERROR_MESSAGE = "You have already been added to this account";
    private static final String ORGANISATION_ERROR_MESSAGE = "This organisation has already been registered";

    @FindBy(how = How.ID, using = "your-name")
    private WebElement yourNameLink;
    @FindBy(how = How.ID, using = "your-contact-details")
    private WebElement yourContactDetailsLink;
    @FindBy(how = How.ID, using = "charity-details")
    private WebElement charityDetailsLink;
    @FindBy(how = How.ID, using = "button__check-details")
    private WebElement checkYourAnswers;
    @FindBy(how = How.LINK_TEXT, using = "Sign out")
    private WebElement signOut;
    @FindBy(id = "duplicate-error-message")
    private WebElement errorMessage;
    @FindBy(how = How.CLASS_NAME, using = "govuk-heading-xl")
    private WebElement errorHeading;
    @FindBy(how = How.CLASS_NAME, using = "govuk-body")
    private WebElement errorBodyText;

    public CharitySummaryPage(DriverFactory driverFactory) {
      super(driverFactory);
    }

    public void clickSignOut() {
        signOut.click();
        driver.manage().deleteAllCookies();
    }

    public void clickYourNameLink() {
      yourNameLink.click();
    }

    public void clickCheckYourAnswers() {
      checkYourAnswers.click();
    }

    public void clickYourContactDetailsLink() {
      yourContactDetailsLink.click();
    }

    public void clickCharityDetailsLink() {
      charityDetailsLink.click();
    }

    public boolean hasCharityAlreadyExistsError(String charityName) {
      return errorHeading.getText().contains(CHARITY_ERROR_MESSAGE);
    }

    public boolean hasOrganisationAlreadyExistsError() {
      return errorHeading.getText().contains(ORGANISATION_ERROR_MESSAGE);
    }

    public boolean hasDuplicateError(String charityName) {
      return errorMessage.getText().contains(charityName);
    }
}
