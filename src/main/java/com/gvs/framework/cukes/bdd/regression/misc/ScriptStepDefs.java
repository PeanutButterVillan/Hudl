package com.gvs.framework.cukes.bdd.regression.misc;

import com.gvs.framework.config.regression.RegressionPropertyManager;
import com.gvs.framework.cukes.TestBase;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScriptStepDefs extends TestBase {
//  private final static Logger log = Logger.getLogger(ScriptStepDefs.class);

  private static final Pattern CONTAINER_ID_PATTERN = Pattern.compile("'(GTM-.*)'");
  private static final Pattern INSTRUMENTATION_KEY_PATTERN = Pattern.compile("instrumentationKey:'(.*)'");

  private WebElement scriptElement;

  @When("^I view the page source for the Google tag manager block")
  public void when_i_view_the_page_source_for_the_Google_tag_manager_block() {
    setScriptElement("gtm-script");
  }

  @When("^I view the page source for the App Insights block")
  public void when_i_view_the_page_source_for_the_App_Insights_block() {
    setScriptElement("app-insights-script");
  }

  @When("^I view the page source for the gtm no script block")
  public void when_i_view_the_page_source_for_the_gtm_no_script_block() {
    try {
        scriptElement = driver.findElement(By.tagName("noscript"));
    } catch (NoSuchElementException ex) {
        scriptElement = null;
    }
  }

  @Then("^I should be able to find the App Insights code block with the instrumentation key$")
  public void then_i_should_be_able_to_find_the_App_Insights_code_block() {
    String instrumentationKey = fetchKey(INSTRUMENTATION_KEY_PATTERN);
    String expectedInstrumentationKey = regressionPropertyManager.getEnvProperty(RegressionPropertyManager.APP_INSIGHTS_KEY_PROPERTY_NAME);
    Assert.assertNotNull(scriptElement.getAttribute("innerHTML"));
    Assert.assertNotNull(instrumentationKey);
    Assert.assertEquals(expectedInstrumentationKey, instrumentationKey);
  }

  @Then("^the GTM code block should be part of the page source with the container id$")
  public void then_the_GTM_code_block_should_be_part_of_the_page_source() {
    String containerId = fetchKey(CONTAINER_ID_PATTERN);
    String expectedGtmContainerId = regressionPropertyManager.getEnvProperty(RegressionPropertyManager.GOOGLE_TAG_MANAGER_ID_PROPERTY_NAME);

    Assert.assertNotNull(scriptElement.getAttribute("innerHTML"));
    Assert.assertNotNull(containerId);
    Assert.assertEquals(expectedGtmContainerId, containerId);
  }

  @Then("^the GTM noscript element is present$")
  public void then_the_GTM_noscript_element_is_present() {
    String innerHTML = scriptElement.getAttribute("innerHTML");
    Assert.assertNotNull(innerHTML);
  }

  @Then("^the GTM noscript element is not present$")
  public void then_the_GTM_noscript_element_is_not_present() {
    Assert.assertNull(scriptElement);
  }

  private void setScriptElement(String scriptId) {
    scriptElement = driver.findElement(By.id(scriptId));
  }

  private String fetchKey(Pattern pattern) {
    String innerHTML = scriptElement.getAttribute("innerHTML");
    Matcher m = pattern.matcher(innerHTML);

    if (m.find()) {
      return m.group(1);
    }
    return null;
  }
}
