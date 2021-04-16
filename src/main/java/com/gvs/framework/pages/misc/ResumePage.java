package com.gvs.framework.pages.misc;

import com.gvs.framework.pages.BasePage;
import com.gvs.framework.util.driver.DriverFactory;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class ResumePage extends BasePage {
    @FindBy(how = How.XPATH, using = "//*[@id=\"profile\"]/ul/li/a")
    public WebElement resume;

    @FindBy(how = How.XPATH, using = "/html/body/")
    public WebElement success;

    public ResumePage(DriverFactory driverFactory) {
        super(driverFactory);
    }

    public void waitForResume() {
        //driverUtils.waitForElementToBeClickable(resume);
    }

    public void validateLink(String link) {
        Assert.assertEquals(link, resume.getText());
    }

    public void clickContinue() {
        driverUtils.clickPageTransitionElement(GENERIC_CONTINUE_BUTTON_2);
    }

}
