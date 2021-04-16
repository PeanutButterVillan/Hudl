package com.gvs.framework.pages.registration;

import com.gvs.framework.pages.BasePage;
import com.gvs.framework.util.driver.DriverFactory;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class CheckYourInformationPage extends BasePage {

    //check your information
    @FindBy(how = How.XPATH, using = "//*[@id=\"content\"]/div[1]/div/h1")
    public WebElement checkYourInformationText;
    // Register invitee - Your name section
    @FindBy(how = How.CSS, using = "dl > div:nth-child(1) > dd")
    public WebElement inviteeYourName;
    // Register invitee - Your contact details section
    @FindBy(how = How.CSS, using = "dl > div:nth-child(2) > dd")
    public WebElement inviteeYourContactDetails;

    @FindBy(how = How.XPATH, using = "//*[@class=\"govuk-header__content\"]/a[2]")
    public WebElement nav;

    @FindBy(how = How.CSS, using = "h1")
    private WebElement completePanel;

    public CheckYourInformationPage(DriverFactory driverFactory) {
        super(driverFactory);
    }

    public void inviteeYourNameSectionContains(String name) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.textToBePresentInElement(inviteeYourName, name));
    }

    public void inviteeYourContactDetailsSectionContains(String detail) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.textToBePresentInElement(inviteeYourContactDetails, detail));
    }
    public void seeRegistrationComplete() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.textToBePresentInElement(completePanel, "complete"));
    }

    public void validateLink(String link) {
        Assert.assertEquals(link, nav.getText());
    }
}
