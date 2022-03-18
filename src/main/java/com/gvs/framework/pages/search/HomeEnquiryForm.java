package com.gvs.framework.pages.search;

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
public class HomeEnquiryForm extends BasePage {

    // What are the properties that we need ?

    @FindBy(how = How.XPATH, using = "//*[@id=\"main\"]/div[2]/div/div/div[1]/div/div[1]/div/div[1]/div[2]/div[2]/div/div[1]/div/input")
    public WebElement firstName;

    @FindBy(how = How.XPATH, using = "//*[@id=\"main\"]/div[2]/div/div/div[1]/div/div[1]/div/div[1]/div[2]/div[3]/div/div[1]/div/input")
    public WebElement lastName;

    @FindBy(how = How.XPATH, using = "//*[@id=\"main\"]/div[2]/div/div/div[1]/div/div[1]/div/div[1]/div[2]/div[4]/div/div[1]/div/div/div[1]/select")
    public WebElement dobDay;

    @FindBy(how = How.XPATH, using = "//*[@id=\"main\"]/div[2]/div/div/div[1]/div/div[1]/div/div[1]/div[2]/div[4]/div/div[1]/div/div/div[2]/select")
    public WebElement dobMonth;

    @FindBy(how = How.XPATH, using = "//*[@id=\"main\"]/div[2]/div/div/div[1]/div/div[1]/div/div[1]/div[2]/div[4]/div/div[1]/div/div/div[3]/select")
    public WebElement dobYear;

    @FindBy(how = How.XPATH, using = "//*[@id=\"main\"]/div[2]/div/div/div[1]/div/div[1]/div/div[1]/div[2]/div[1]/div/div[1]/div/select")
    public WebElement selectTitle;

    public HomeEnquiryForm(DriverFactory driverFactory) {
        super(driverFactory);
    }

    public void selectTitle(String title) {
        driverUtils.clickPageTransitionElement(selectTitle);
        selectTitle.sendKeys(title);
        selectTitle.sendKeys(Keys.RETURN);
    }

    public void inputFirstName(String inFirstName) {
        firstName.clear();
        firstName.sendKeys(inFirstName);

    }

    public void inputLastName(String inLastName) {
        lastName.clear();
        lastName.sendKeys(inLastName);

    }

    public void inputDateOfBirth(String Day, String Month, String Year) {
        dobDay.sendKeys(Day);
        dobMonth.sendKeys(Month);
        dobYear.sendKeys(Year);

    }

    public boolean verifyContents(String query) {
        Boolean found =  driver.findElement(By.xpath("//*[contains(text(), query)]")).isDisplayed();
        return found;
    }

}
