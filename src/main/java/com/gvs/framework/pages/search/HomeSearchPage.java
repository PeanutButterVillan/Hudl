package com.gvs.framework.pages.search;

import com.gvs.framework.pages.BasePage;

import com.gvs.framework.util.driver.DriverFactory;
import com.gvs.framework.util.misc.CharityNumber;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class HomeSearchPage extends BasePage {

    // What are the properties that we need ?

    @FindBy(how = How.NAME, using = "searchLocation")
    public WebElement searchLocation;

    @FindBy(how = How.XPATH, using = "//*[@id=\"findOptions\"]/div[1]/form/fieldset/button")
    public WebElement searchButton;



    public HomeSearchPage(DriverFactory driverFactory) {
        super(driverFactory);
    }


    public void inputSearchLocation(String locationName) {
        searchLocation.clear();
        searchLocation.sendKeys(locationName);
        driverUtils.clickPageTransitionElement(searchButton);
    }

    public boolean verifyContents(String query) {
        Boolean found =  driver.findElement(By.xpath("//*[contains(text(), query)]")).isDisplayed();
        return found;
    }

}
