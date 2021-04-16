package com.gvs.framework.pages.misc;

import com.gvs.framework.pages.BasePage;
import com.gvs.framework.pages.registration.GovGatewayLoginPage;
import com.gvs.framework.util.driver.DriverFactory;

import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

//Below class is PageObject to handle all relevant happy and unhappy path pages which Navigates whilst duplicate charity number entered
@Component
@Scope("cucumber-glue")
public class NplusOnePages extends BasePage {
//    private static final Logger logger = Logger.getLogger(NplusOnePages.class);
    private static final String DUPLICATE_LIMITED_PARENT_USERID = "87 50 38 35 19 68";
    private static final String DUPLICATE_LIMITED_PARENT_PWD = "test12345*";


    @Autowired
    protected GovGatewayLoginPage govGatewayLoginPage;
    public String regUrlnp;
    public By elementLocator;
    //Confirm button in "Are these the correct charity details"
    @FindBy(how = How.ID, using = "button-continue")
    public WebElement confirmButton;

    //Continue button in "This organisation has already been registered"
    @FindBy(how = How.ID, using = "button-continue")
    private WebElement continueButton;

    //Yes Radio button in "This organisation has already been registered"
    @FindBy(how = How.ID, using = "tell-administrator-yes")
    private WebElement yesRadioButton;

    //No Radio button in "This organisation has already been registered"
    @FindBy(how = How.ID, using = "tell-administrator-no")
    public WebElement noRadioButton;

    //Continue button in "You agreed for your details to be sent to the account administrator"
    @FindBy(how = How.ID, using = "button-continue")
    private WebElement continueButton2;
    //Yes Radio button in "Are you sure you wish to end your registration?"
    @FindBy(how = How.ID, using = "confirmation-yes")
    private WebElement yesRadioButton2;

    //No Radio button in "Are you sure you wish to end your registration?"
    @FindBy(how = How.ID, using = "confirmation-no")
    private WebElement noRadioButton2;

    //Confirm button in "Are you sure you wish to end your registration?"
    @FindBy(how = How.ID, using = "button-continue")
    private WebElement confirmButton2;

    //Confirm button in "Are you sure you wish to end your registration?"
    @FindBy(how = How.XPATH, using = "//*[@id='idm-form--main']/h1")
    public WebElement orgAlreadyBeenRegistered;

    //Accept button
    @FindBy(how = How.ID, using = "button-accept")
    private WebElement acceptButton;
    //Decline button
    @FindBy(how = How.ID, using = "button-decline")
    private WebElement declineButton;

    //Admin radio buttion in "Do you want to give this person any admin responsibilities for your organisation? "
    @FindBy(how = How.ID, using = "identityRoleId-1")
    private WebElement adminRole;

    //Continue button in "Do you want to give this person any admin responsibilities for your organisation? "
    @FindBy(how = How.ID, using = "button-continue")
    private WebElement continueButtonAdminResponsibilityPage;

    //1044
    //Continue button in "Account access has been given"
    @FindBy(how = How.ID, using = "button-continue")
    private WebElement continueButtonAccountAccessGiven;
    //Give Service Access button in "Account profile -GOV.UK"page
    @FindBy(how = How.ID, using = "button-give-service-access")
    private WebElement giveServiceAccessButton;

    //"COMPLY WITH REACH" Service option Which service do you want this person to use?page
    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Comply with UK REACH')]")
    private WebElement complyWithUkReach;
    //"IPAFFS" Service option Which service do you want this person to use?page
    @FindBy(how = How.XPATH, using = "//*[contains(text(),'IPAFFS')]")
    private WebElement IPAFFS;
    // Continue button in "Which service do you want this person to use?page
    @FindBy(how = How.ID, using = "button-continue")
    private WebElement continueButtonWhichServicePage;


    //REACH MANAGER Service role option in "Which role do you want to give this person?"page
    @FindBy(how = How.XPATH, using = "//*[contains(text(),'REACH Manager')]")
    private WebElement reachManagerServiceRole;
    // NOTIFIER Service role option in "Which role do you want to give this person?"page
    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Notifier')]")
    private WebElement notifierServiceRole;
    // Continue button in "Which role do you want to give this person?"page
    @FindBy(how = How.ID, using = "button-continue")
    private WebElement continueButtonServiceRolePage;

    //Continue button in "Service  Access Granted" page
    @FindBy(how = How.ID, using = "button-continue")
    private WebElement continueButtonServiceAccessGrantedPage;
    //It will display :No Service Available text
    @FindBy(how = How.XPATH, using = ".//*[@id='main-content']/p[1]")
    public WebElement noServiceTextDispaly;
    //Continue button in "Account access Declined has been given"
    @FindBy(how = How.ID, using = "button-continue")
    private WebElement continueButtonAccountAccessDeclined;

    public NplusOnePages(DriverFactory driverFactory) {
        super(driverFactory);
    }


    public By nPlusPageLocs(String locs) throws Exception {
        HashMap<String, By> hmap = new HashMap();
        /*Adding elements to HashMap*/
        try {
            hmap.put("ThisOrganisationHasAlreadyBeenRegistered", By.xpath("//*[@id='idm-form--main']/h1"));
            hmap.put("YouHaveEndedYourRegistration", By.xpath("//*[@id='idm-form--main']/div/h1"));
            elementLocator = hmap.get(locs);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return elementLocator;
    }

    public void clickContinue() {
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

    /* Below Method Navigates all relevant pages when Charity Number is entered as duplicate
      from "Are these the correct charity details" to task List page*/
    public void charityNplusOneJourney() {
        confirmButton.click();
        yesRadioButton.click();
        continueButton.click();
        continueButton2.click();
    }

    /* Below Method Navigates all relevant pages when Ltd Number is entered as duplicate
      from "Are these the correct registered company details" to task List page/check your Information page*/
    public void limitedNplusOneJourney() {
        confirmButton.click();
        yesRadioButton.click();
        continueButton.click();
        continueButton2.click();
    }

    //Below method will make a decision to click either "yes" or "No" Radio button on "Are you sure you wish to end your registration?" page
    public void yesNoButtonSelection(String button) {
        driverUtils.waitForElementToBeClickable(confirmButton);
        confirmButton.click();
        if (button.equalsIgnoreCase("yes")) {
            yesRadioButton2.click();
            confirmButton2.click();
        } else {
            noRadioButton2.click();
            confirmButton2.click();
        }
    }

    //577
    // Journey for Parent org to Accept NplusOne request and choose a Account role
    public void acceptNplusOne(String parentOrgUserID, String buttonType) {
        govGatewayLoginPage.login(parentOrgUserID, DUPLICATE_LIMITED_PARENT_PWD);
        acceptButton.click();
        driverUtils.waitUntilPageFullyLoaded();
        adminRole.click();
        continueButtonAdminResponsibilityPage.click();
        continueButtonAccountAccessGiven.click();
    }
    //582
    public void declineNplusOne(String parentOrgUserID) {
        govGatewayLoginPage.login(parentOrgUserID, DUPLICATE_LIMITED_PARENT_PWD);
        declineButton.click();
        continueButtonAccountAccessDeclined.click();
    }

    //577
    //verify the Parent Org getting an  email either to Accept or Decline from Nplus One
    public String verifyParentOrgAcceptDeclineEmailFromNplus(String email) {
        String result = "";

        this.regUrlnp = result.substring(result.indexOf("<") + 1, result.indexOf(">"));
        return this.regUrlnp;
    }



    //1044
    public void npService(String service) {

        driverUtils.waitForElementToBeClickable(giveServiceAccessButton);
        giveServiceAccessButton.click();
        if (service.equalsIgnoreCase("Comply with UK REACH")) {
            complyWithUkReach.click();
            continueButtonWhichServicePage.click();
            reachManagerServiceRole.click();
            continueButtonServiceRolePage.click();
        } else {
            IPAFFS.click();
            continueButtonWhichServicePage.click();
            notifierServiceRole.click();
            continueButtonServiceRolePage.click();
        }
        continueButtonServiceAccessGrantedPage.click();
    }

    //1044
    public void verifyServiceAccessDetails(String service, String serviceType) {
        String actService = "";
        String actServiceType = "";
        try {
            List<WebElement> rows = driver.findElements(By.cssSelector("tbody > tr"));
            for (WebElement row : rows) {
                WebElement cell1 = row.findElement(By.cssSelector("td:nth-of-type(1)"));
                WebElement cell2 = row.findElement(By.cssSelector("td:nth-of-type(2)"));
                if (cell1.getText().split("\n")[1].equals(service)
                        && cell2.getText().split("\n")[1].equals(serviceType)) {
                    actService = cell1.getText().split("\n")[1];
                    actServiceType = cell2.getText().split("\n")[1];

                    break;
                }
            }
        } catch (Exception ex) {

        }
        Assert.assertTrue("ExpectedText: " + service + "  ActualText: " + actService, actService.equalsIgnoreCase(service));
        Assert.assertTrue("ExpectedText: " + serviceType + "  ActualText: " + actServiceType, actServiceType.equalsIgnoreCase(serviceType));
    }
    //582
    //Verify whether NplusOne Record in ManageTeamAccess page is displaying or not after declining the request
    public void findRecordInManageTeamAccessPage(String admin) {
        boolean expTextCondition = false;
        boolean actTextCondition = false;
        try {
            List<WebElement> rows = driver.findElements(By.cssSelector("tbody > tr"));
            for (WebElement row : rows) {
                WebElement option = row.findElement(By.cssSelector(" td:nth-child(2)"));
                if (option.getText().split("\n")[1].equals(admin)) {
                    actTextCondition = true;

                    break;
               }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
         Assert.assertEquals("Is NplusOne Record in ManageTeamAccess page displaying?: ", expTextCondition,actTextCondition);
        }
}
