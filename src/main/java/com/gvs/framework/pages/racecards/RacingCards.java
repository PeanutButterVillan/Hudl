package com.gvs.framework.pages.racecards;

import com.gvs.framework.pages.BasePage;
import com.gvs.framework.util.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class RacingCards extends BasePage{


    protected By bigRaceEntries = By.xpath("//a[@href='/racecards/big-race-entries']");


    public RacingCards(DriverFactory driverFactory) {

        super(driverFactory);
    }



    public void selectBigRaceEntries() {

        driverUtils.clickPageTransitionElement(bigRaceEntries);
    }


}
