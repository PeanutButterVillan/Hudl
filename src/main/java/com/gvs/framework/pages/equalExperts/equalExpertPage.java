package com.gvs.framework.pages.equalExperts;

import com.gvs.framework.pages.BasePage;
import com.gvs.framework.util.driver.DriverFactory;
import com.gvs.framework.util.misc.pojo.springNatureContentType;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;


import com.gvs.framework.cukes.bdd.regression.springNature.SpringNatureStepdefs;
        import com.gvs.framework.pages.BasePage;
        import com.gvs.framework.util.driver.DriverFactory;

        import org.apache.log4j.Logger;
        import org.junit.Assert;
        import org.openqa.selenium.*;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.How;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Scope;
        import org.springframework.stereotype.Component;



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
        import com.gvs.framework.util.misc.pojo.springNatureContentType;
        import java.text.DateFormat;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
import java.util.List;

@Component
@Scope("cucumber-glue")

    public class equalExpertPage extends BasePage {


    private static final Logger log = Logger.getLogger(com.gvs.framework.pages.springerNature.SpringLinkPage.class);

    @Autowired
    private springNatureContentType springcontentType;

    private String firstNameSelected;

    private String lastNameSelected;

    private String priceSelected;

    @FindBy(how = How.XPATH, using = "//input[@type='button' and @value=' Save ']")
    public WebElement saveButton;



    @FindBy(how = How.ID, using = "firstname")
    public WebElement firstname;

    @FindBy(how = How.ID, using = "checkin")
    public WebElement checkin;

    @FindBy(how = How.CLASS_NAME, using = "ui-datepicker-calendar")
    public WebElement contentType;

    @FindBy(how = How.ID, using = "bookings")
    public WebElement contentType2;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ui-datepicker-div\"]/div/a[2]")
    public WebElement selectMonthIn;

    @FindBy(how = How.ID, using = "checkout")
    public WebElement checkout;

    @FindBy(how = How.XPATH, using = "//*[@id=\"ui-datepicker-div\"]/div/a[2]")
    public WebElement selectMonthOut;

    protected static final By DEPOSIT_DROPDOWN = By.id("depositpaid");

    @FindBy(how = How.ID, using = "lastname")
    public WebElement lastname;

    @FindBy(how = How.ID, using = "totalprice")
    public WebElement totalprice;

    @FindBy(how = How.CLASS_NAME, using = "ui-datepicker-month")
    public WebElement selectedMonth;

    @FindBy(how = How.CLASS_NAME, using = "ui-datepicker-year")
    public WebElement selectedYear;

    public equalExpertPage(DriverFactory driverFactory) {
        super(driverFactory);
    }


    public void inputFirstName(String firstName) {
        firstname.clear();
        firstname.sendKeys(firstName);
    }

    public void inputLastName(String lastName) {
        lastname.clear();
        lastname.sendKeys(lastName);
    }

    public void inputTotalPrice(String totalPrice) {
        totalprice.clear();
        totalprice.sendKeys(totalPrice);
    }

    public void enterDeposit(String deposit) {


        Select depositDropdown = new Select(driver.findElement(DEPOSIT_DROPDOWN));
        depositDropdown.selectByVisibleText(deposit);

    }

    public void selectCheckIn(String expectedMonth, String expectedYear) {
        checkin.click();
        int integerMonth = Integer.parseInt(expectedMonth);
        // need to convert number of month to the name string
        String localMonth =  Month.of(integerMonth).getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH);
        while (!(localMonth.equals(selectedMonth.getText()) && expectedYear.equals(selectedYear.getText())))
            selectMonthIn.click();
    }

    public void selectCheckOut(String expectedMonth, String expectedYear) {
        checkout.click();

        int integerMonth = Integer.parseInt(expectedMonth);
        // need to convert number of month to the name string
        String localMonth =  Month.of(integerMonth).getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH);
        while (!(localMonth.equals(selectedMonth.getText()) && expectedYear.equals(selectedYear.getText())))
            selectMonthIn.click();

    }

    public void submitSave() {
        driverUtils.clickPageTransitionElement(saveButton);
    }

    public boolean verifyContents(String query) {
        Boolean found = driver.findElement(By.xpath("//*[contains(text(), query)]")).isDisplayed();
        return found;
    }

    public void verifyTotal(springNatureContentType.Type expectedType, String expectedTotal) {

        log.info("Expected Type: " + expectedType.toString() + " Expected Total: " + expectedTotal + " Actual Total: " + springcontentType.getExistingTotalsNumber(expectedType));

        Assert.assertTrue(expectedTotal.equals(springcontentType.getExistingTotalsNumber(expectedType)));


    }


    public void selectDay(String ExpectedDay) throws ParseException {
        SearchContext contentTypeObj = ((SearchContext) contentType);
        List<WebElement> rows = contentTypeObj.findElements(By.xpath("//td[contains(text(),ExpectedDay)]"));

        //for (WebElement row : rows) {
          //  List<WebElement> cols = row.findElements(By.xpath("//td[contains(text(),ExpectedDay)]"));
            for (WebElement col : rows) {
                System.out.println(col.getText());

                if (ExpectedDay.equals(col.getText())) {
                    col.click();
                }
            }

    }

    //setter method for firstName
    public void setFirstName(String firstNameValue)
    {
        firstNameSelected = firstNameValue;
    }
    //setter method for lastName
    public void setLastName(String lastNameValue)
    {
        lastNameSelected = lastNameValue;
    }
    //setter method for price
    public void setPrice(String priceValue)
    {
        priceSelected = priceValue;
    }
    //click delete - need to find the row with the above set
    public void submitDelete() {

        //findRow with first name
        //findRow with last name
        //findRow with price
        WebElement deleteButton=null;
        deleteButton = findRow(firstNameSelected,lastNameSelected, priceSelected);
        driverUtils.clickPageTransitionElement(deleteButton);
    }

    private WebElement findRow(String expectedFirstNameValue, String expectedSurnameValue, String expectedPriceValue) {

        String ColumnName = "Firstname";

        SearchContext contentTypeObj = ((SearchContext) contentType2);

        List<WebElement> rows = contentTypeObj.findElements(By.xpath("//div[@id]"));


        // row shouldn't be the first two
        int colNumber = 0;
        int rowNumber = 0;
        int matchingCol = 0;
        WebElement foundRow=null;
        boolean found = false;
        for (WebElement row : rows) {
            colNumber = 0;
            rowNumber++;
            List<WebElement> cols;
            if (ColumnName.equals("Price"))
                cols = row.findElements(By.className("col-md-1"));
            else
                cols = row.findElements(By.className("col-md-2"));
            for (WebElement col : cols) {
                System.out.println(col.getText());
                colNumber++;
                if (ColumnName.equals(col.getText()))
                    matchingCol = colNumber;
                if ((rowNumber > 1) && (matchingCol == colNumber))

                    if (expectedFirstNameValue.equals(col.getText())) {
                        found = true;
                        foundRow = row;
                    }
            }
        }
        Assert.assertEquals(true, found);
        return foundRow;

/*
        /////
        SearchContext contentTypeObj = ((SearchContext) contentType2);
        List<WebElement> rows = contentTypeObj.findElements(By.className("row"));
        // row shouldn't be the first two
        int colNumber = 0;
        int rowNumber = 0;
        int matchingCol = 0;
        int matchingRow = 0;
        WebElement foundRow = null;
        boolean foundFirstName=false , foundSurname=false, foundPrice = false;

        ArrayList<String> columnTypes = new ArrayList<>();
        columnTypes.add("Firstname");
        columnTypes.add("Surname");
        columnTypes.add("Price");

        for (String column : columnTypes) {

            for (WebElement row : rows) {
                colNumber = 0;
                rowNumber++;
                List<WebElement> cols;
                if (column.equals("Price"))
                    cols = row.findElements(By.className("col-md-1"));
                else
                    cols = row.findElements(By.className("col-md-2"));
                for (WebElement col : cols) {
                    System.out.println(col.getText());
                    colNumber++;
                    if (column.equals(col.getText()))
                        matchingCol = colNumber;
                    if ((rowNumber > 1) && (matchingCol == colNumber))
                        if (column.equals("Firstname") && (expectedFirstNameValue.equals(col.getText()))) {
                            foundFirstName = true;
                            String idValue=row.getAttribute("id");

                        }

                }

            }


        }
        return foundRow;

 */
    }

    public void verifyColumnTotals(String expectedValue, String ColumnName) throws ParseException {
        SearchContext contentTypeObj = ((SearchContext) contentType2);
        List<WebElement> rows = contentTypeObj.findElements(By.className("row"));
        // row shouldn't be the first two
        int colNumber = 0;
        int rowNumber = 0;
        int matchingCol = 0;
        boolean found = false;
        for (WebElement row : rows) {
            colNumber = 0;
            rowNumber++;
            List<WebElement> cols;
            if (ColumnName.equals("Price"))
                cols = row.findElements(By.className("col-md-1"));
            else
                cols = row.findElements(By.className("col-md-2"));
            for (WebElement col : cols) {
                System.out.println(col.getText());
                colNumber++;
                if (ColumnName.equals(col.getText()))
                    matchingCol = colNumber;
                if ((rowNumber > 1) && (matchingCol == colNumber))

                    if (expectedValue.equals(col.getText()))
                        found = true;
            }
        }
        Assert.assertEquals(true, found);

    }
}


