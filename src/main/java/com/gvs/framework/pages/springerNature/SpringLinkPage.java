package com.gvs.framework.pages.springerNature;

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
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
@Scope("cucumber-glue")

public class SpringLinkPage extends BasePage {

    private static final Logger log = Logger.getLogger(SpringLinkPage.class);

    @Autowired
    private springNatureContentType springcontentType;

    @FindBy(how = How.NAME, using = "query")
    public WebElement query;

    @FindBy(how = How.ID, using = "search")
    public WebElement searchButton;

    @FindBy(how = How.ID, using = "content-type-facet")
    public WebElement contentType;

    public SpringLinkPage(DriverFactory driverFactory) {
        super(driverFactory);
    }


    public void inputAuthor(String authorName) {
        query.clear();
        query.sendKeys(authorName);
    }

    public void submitSearch() {
        driverUtils.clickPageTransitionElement(searchButton);
    }

    public boolean verifyContents(String query) {
        Boolean found = driver.findElement(By.xpath("//*[contains(text(), query)]")).isDisplayed();
        return found;
    }

    public void verifyTotal(springNatureContentType.Type expectedType, String expectedTotal){

        log.info(  "Expected Type: " + expectedType.toString() +  " Expected Total: " + expectedTotal + " Actual Total: " + springcontentType.getExistingTotalsNumber(expectedType));

        Assert.assertTrue(expectedTotal.equals( springcontentType.getExistingTotalsNumber(expectedType)));


    }



    public String getTypeTotal(String expectedTotal, String ExpectedTypeName) throws ParseException {
        SearchContext contentTypeObj = ((SearchContext) contentType);
        List<WebElement> rows = contentTypeObj.findElements(By.className("facet-link"));
        String return_value=null;
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.xpath(".//span[@class='facet-title']"));
            for (WebElement col : cols) {
                System.out.println(col.getText());

                if (ExpectedTypeName.equals(col.getText())){


                    List<WebElement> amounts = row.findElements(By.xpath(".//span[@class='facet-amount']"));
                    for (WebElement col2 : amounts) {
                        System.out.println(col2.getText());
                        return_value = col2.getText();
                        String string = col2.getText();
                        String string2 = cols.get(0).getText();

                    }

                }
                String string = col.getText();
                String string2 = cols.get(0).getText();

            }



        }
return return_value;
    }
}



