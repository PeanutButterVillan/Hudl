package com.gvs.framework.util.driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverCapabilityManager {


    public DesiredCapabilities generateDefaultChromeCapabilities(String projectName, String buildNumber){
        DesiredCapabilities caps = new DesiredCapabilities();
        ChromeOptions chromeOptions = new ChromeOptions();

        //caps.setBrowserName("chrome");

        if(projectName != null){
            caps.setCapability("project", projectName);
        }
        if(buildNumber != null) {
            caps.setCapability("build", buildNumber);
        }

        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.setExperimentalOption("useAutomationExtension", false);


        caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return caps;
    }
}