package com.gvs.framework.pages;

import com.gvs.framework.config.SpringContext;
import com.gvs.framework.util.driver.DriverFactory;
import com.gvs.framework.util.driver.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class BasePage {
    public static final By GENERIC_CONTINUE_BUTTON_1 = By.id("continue");
    public static final By GENERIC_CONTINUE_BUTTON_2 = By.id("button-continue");


    @Autowired
    protected DriverUtils driverUtils;

    @Autowired
    protected DriverFactory driverFactory;

    protected RemoteWebDriver driver;

    @Autowired
    private SpringContext springContext;

    public BasePage(DriverFactory driverFactory){
        this.driver = driverFactory.getInstance();
        PageFactory.initElements(driver, this);
    }
}