package com.gvs.framework.pages.misc;

import com.gvs.framework.pages.BasePage;
import com.gvs.framework.util.driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class AddSiblingAccountErrorPage extends BasePage {

  @FindBy(how = How.CLASS_NAME, using = "govuk-heading-xl")
  private WebElement errorHeading;

  @FindBy(how = How.ID, using = "duplicate-error-message")
  private WebElement duplicateOrgErrorMessageBody;

  public AddSiblingAccountErrorPage(DriverFactory driverFactory) {
    super(driverFactory);
  }

  public boolean hasCorrectOrganisationName(String company) {
    return duplicateOrgErrorMessageBody.getText().contains(company);
  }
}
