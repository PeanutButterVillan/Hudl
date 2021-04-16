package com.gvs.framework.util.driver;

import org.apache.commons.io.FileUtils;
//import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * Test Utility Class - used to contain all RemoteWebDriver related utility methods.
 */
@SuppressWarnings("WeakerAccess")
public class DriverUtils {
//    private static final Logger logger = Logger.getLogger(DriverUtils.class);

    private DriverFactory driverFactory;
    protected static final int WAIT_TIME = 60;

    public DriverUtils(DriverFactory driverFactory){
        this.driverFactory = driverFactory;
    }

    public void refreshPage(){
        driverFactory.getInstance().navigate().refresh();
        waitUntilPageFullyLoaded();
    }
    public void waitForElementToBeClickable(WebElement webElement) {
     WebDriverWait   webDriverWait = new WebDriverWait(driverFactory.getInstance(), WAIT_TIME);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
//        logger.info("Waiting for the element" + webElement + " to be clickable");
    }

    public WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait   webDriverWait = new WebDriverWait(driverFactory.getInstance(), WAIT_TIME);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
//        logger.info("Waiting for the element" + locator + " to be clickable");
        return driverFactory.getInstance().findElement(locator);
    }
    public boolean elementExists(By by){
        waitUntilPageFullyLoaded();
        // Set a small element timeout because it is a try catch.
        driverFactory.getInstance().manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        try{
            driverFactory.getInstance().findElement(by);
            driverFactory.resetDriverTimeouts();
            return true;
        } catch(NoSuchElementException e){
            driverFactory.resetDriverTimeouts();
            return false;
        }
    }

    public byte[] getScreenshotBytes(){
        return ((TakesScreenshot)driverFactory.getInstance()).getScreenshotAs(OutputType.BYTES);
    }

    public void takeScreenshot(String filePrefix){
        try {
            File screenshotFile = ((TakesScreenshot)driverFactory.getInstance()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File("./target/reports/" + filePrefix + "." +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("'T'yyyyMMddHHmmssS")) + "." + driverFactory.getInstance().getSessionId() + ".png"));
        } catch (IOException ex) {
//            logger.error("Attempt to take screenshot failed, cause was: " + ex.getCause());
        }
    }

    public void clearFieldAndSendKeys(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }

    public void clickElementWithRetry(WebElement element){
        int retryAttempts = 3;
        int retryDelay = 1500;
        String currentUrl = driverFactory.getInstance().getCurrentUrl();

        for(int i = 0; i <= retryAttempts; i++){
            try{
                driverFactory.getInstance().manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
                clickPageTransitionElement(element);
                driverFactory.resetDriverTimeouts();
                if(!driverFactory.getInstance().getCurrentUrl().equalsIgnoreCase(currentUrl)){
                    break;
                }
            } catch(NoSuchElementException e){
                try { Thread.sleep(retryDelay); } catch (InterruptedException ex) {}
            }
        }
    }
    public void clickElementWithRetry(By elementIdentifier){
        clickElementWithRetry(driverFactory.getInstance().findElement(elementIdentifier));
    }

    public void clickPageTransitionElement(WebElement element) {
        element.click();
        //Wait just long enough for page to trigger dom refresh

        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
        waitUntilPageFullyLoaded();
    }
    public void clickPageTransitionElement(By elementIdentifier){
        clickPageTransitionElement(driverFactory.getInstance().findElement(elementIdentifier));
    }

    public void waitUntilPageFullyLoaded(){
        new WebDriverWait(driverFactory.getInstance(), 60).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        try { Thread.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public boolean isElementVisible(By elementIdentifier){

        try{
            driverFactory.getInstance().findElement(elementIdentifier);
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    /**
     * Generic utility method which enters text into an element based on it's value attribute.
     *
     * @param xpathElement - Used to specify an element's type more uniquely
     * @param value - The 'value' css attribute of the element
     */
    public void clickByValue(String xpathElement, String value){
        WebElement element = driverFactory.getInstance().findElement(
                By.xpath("//" + xpathElement + "[@value='" + value + "']"));
        scrollToElement(element);
        clickPageTransitionElement(element);
    }

    /**
     * Generic utility method which clicks an element based on it's text.
     *
     * @param xpathElement - Used to specify an element's type more uniquely
     * @param text - The search text of the element
     */
    public void clickByText(String xpathElement, String text){
        WebElement element = driverFactory.getInstance().findElement(
                By.xpath("//" + xpathElement + "[text()[contains(normalize-space(.),'" + text + "')]]"));
        //scrollToElement(element);
        clickPageTransitionElement(element);
    }

    /** Will scroll to the element provided, meeting the prerequisite of visibility before interaction. */
    public void scrollToElement(WebElement element){
        ((JavascriptExecutor) driverFactory.getInstance()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /** Overload method for clickByText which clicks any(the first) matching element */
    public void clickByText(String text){ clickByText("*",text); }

    /**
     * Generic utility method which finds any element with the specified search text, returning the element found.
     *
     * @param searchString - The text used to search for within the current page (ignoring irregular formatting).
     * @return WebElement - The element found on the current page, or null if the element is not found.
     */
    public boolean isStringVisible(String xpathElement, String searchString){

        waitUntilPageFullyLoaded();
        // Set a small element timeout because it is a try catch.
        driverFactory.getInstance().manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        try{
            driverFactory.getInstance().findElement(By.xpath(xpathElement + "[text()[contains(normalize-space(.),'" + searchString + "')]]"));
            driverFactory.resetDriverTimeouts();
            return true;
        } catch(NoSuchElementException e){
            driverFactory.resetDriverTimeouts();
            return false;
        }
    }

    /** Overload method for isStringVisible which matches any element regardless of identifier */
    public boolean isStringVisible(String searchString){
        return isStringVisible("//*", searchString);
    }

    /**
     * Generic utility method which determines whether an alert matching the alert text is visible.
     *
     * @param alert - text used to determine if there is an expected alert visible.
     * @return true if an alert with the specified text is visible, false if not.
     */
    public boolean isAlertVisible(String alert){
        Alert alertWindow = new WebDriverWait(driverFactory.getInstance(), 2).until(ExpectedConditions.alertIsPresent());

        driverFactory.getInstance().switchTo().alert();
        if(normaliseString(alertWindow.getText()).equals(alert)){
            alertWindow.accept();
            return true;
        } else {
            alertWindow.accept();
            return false;
        }
    }

    /** Internal utility method which formats all input strings correctly by removing erroneous formatting */
    private String normaliseString(String input){
        return input.trim().replace("\n", " ").replace("  ", " ");
    }

}
