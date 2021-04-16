package com.gvs.framework.pages.racecards;

import com.gvs.framework.cukes.bdd.regression.search.BranchSearchSteps;
import com.gvs.framework.pages.BasePage;
import com.gvs.framework.util.driver.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
@Scope("cucumber-glue")
public class BigRaceEntries extends BasePage{


    private static final Logger log = Logger.getLogger(BranchSearchSteps.class);

    @FindBy(how = How.NAME, using = "searchLocation")
    public WebElement searchLocation;

    @FindBy(how = How.XPATH, using = "//*[@id=\"findOptions\"]/div[1]/form/fieldset/button")
    public WebElement searchButton;

    Date date = null;
    Date date2 = null;

    public BigRaceEntries(DriverFactory driverFactory) {
        super(driverFactory);
    }


    public Date getTodayDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMM yy");
        Date dateToday = new Date();
        System.out.println(dateFormat.format(dateToday));
        return dateToday;
    }

    public Date selectMonthList() throws ParseException {
        Date ret_value = null;
        //FUNC for get date of first big race on BigRaceEntries page object
        WebElement monthList = driver.findElementByClassName("RC-bigRaces__monthList");
        SearchContext monthListObj = ((SearchContext) monthList);
        List<WebElement> rows = monthListObj.findElements(By.className("RC-bigRaces__race"));

        for(WebElement row : rows)
        {
            List<WebElement> cols = row.findElements(By.xpath(".//div[@class='RC-bigRaces__dateWide']"));
            for(WebElement col : cols)
            {
                System.out.println(col.getText());

                String string = col.getText();
                String string2 =  cols.get(0).getText();
                DateFormat format = new SimpleDateFormat("dd MMM yy", Locale.ENGLISH);
                date = format.parse(string);
                date2 = format.parse(string2);
                System.out.println(date); // Sat Jan 02 00:00:00 GMT 2010
                ret_value = date2;
            }

        }

return ret_value;
    }

}
