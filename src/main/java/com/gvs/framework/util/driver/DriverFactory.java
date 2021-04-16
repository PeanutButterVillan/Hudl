package com.gvs.framework.util.driver;

import com.gvs.framework.config.MavenProfiles;
import com.gvs.framework.config.SpringContext;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.SystemUtils;
//import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
//    private static Logger logger = Logger.getLogger(DriverFactory.class);

    private static final String LOCAL_DRIVER_BINARY_PATH =
            "src/main/resources" + File.separator +
                    "binaries" + File.separator;

    private static final String CHROME_DRIVER_VERSION = "2.43";
    private static final String GECKO_DRIVER_VERSION = "0.23.0";

    private RemoteWebDriver driver;
    private DriverCapabilityManager driverCapabilityManager;

    @Autowired
    protected SpringContext springContext;

    public enum LocalBrowser{
        FIREFOX("firefox"),
        CHROME("chrome");

        private String description;
        LocalBrowser(String browser){this.description = browser;}
        public String toString(){return this.description;}
        public static LocalBrowser getMatch(String text){
            for(LocalBrowser browser : LocalBrowser.values()){
                if(browser.toString().equals(text)){
                    return browser;
                }
            }
            throw new RuntimeException("Local Browser '" + text + "' unsupported.");
        }
    }

    public DriverFactory(DriverCapabilityManager driverCapabilityManager){
        this.driverCapabilityManager = driverCapabilityManager;
    }

    public RemoteWebDriver getInstance()
    {
        if(driver == null || driver.getSessionId() == null){
            System.out.println(springContext.getEnvProperty(SpringContext.MAVEN_PROFILE_PROPERTY_NAME));
            switch(MavenProfiles.getMatch(springContext.getEnvProperty(SpringContext.MAVEN_PROFILE_PROPERTY_NAME))){
                case LOCAL:{
//                    logger.info("Local Maven profile activated, instantiating driver");
                    driver = instantiateLocalDriver();
                    break;
                }
                case GRID:{
                    Preconditions.checkArgument(springContext.getEnvProperty(SpringContext.GRID_URL_PROPERTY_NAME) != null,
                            "In order to use the selenium grid maven profile, pass in the grid url as " +
                                    "'-Dgrid.url=http://127.0.0.1:4444/hub/wb' or set it in the grid config file.");
                    driver = instantiateRemoteDriver(
                            springContext.getEnvProperty(SpringContext.GRID_URL_PROPERTY_NAME),
                            driverCapabilityManager.generateDefaultChromeCapabilities(
                                springContext.getEnvProperty(SpringContext.PROJECT_NAME_PROPERTY_NAME),
                                springContext.getEnvProperty(SpringContext.BUILD_NUM_PROPERTY_NAME)));
                    break;
                }

            }
            resetDriverTimeouts();
        }
        return driver;
    }

    private RemoteWebDriver instantiateLocalDriver(){
        switch(springContext.getEnvProperty(SpringContext.BROWSER_PROPERTY_NAME).toLowerCase()){
            case "firefox":{
                return getFirefoxDriver();
            }
            default:{} //Generate a chrome instance by default.
            case "chrome":{
                return getChromeDriver();
            }
        }
    }

    private RemoteWebDriver instantiateRemoteDriver(String url, Capabilities caps) {
        try {
            return new RemoteWebDriver(new URL(url),caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static FirefoxDriver getFirefoxDriver() {
        String firefoxBinaryPath = "";

        if(SystemUtils.IS_OS_MAC){
            firefoxBinaryPath = LOCAL_DRIVER_BINARY_PATH + "geckodriver-v" + GECKO_DRIVER_VERSION + "-macos/geckodriver";
        } else if(SystemUtils.IS_OS_LINUX){
            firefoxBinaryPath = LOCAL_DRIVER_BINARY_PATH + "geckodriver-v" + GECKO_DRIVER_VERSION + "-linux64/geckodriver";
        } else if(SystemUtils.IS_OS_WINDOWS){
            firefoxBinaryPath = LOCAL_DRIVER_BINARY_PATH + "geckodriver-v" + GECKO_DRIVER_VERSION + "-win64/geckodriver.exe";
        }

        File f = new File(firefoxBinaryPath);
        System.setProperty("webdriver.gecko.driver", f.getAbsolutePath());

        FirefoxDriver driver = new FirefoxDriver(new FirefoxOptions());
        return driver;
    }

    private ChromeDriver getChromeDriver() {
        System.clearProperty("webdriver.ie.driver");

        String chromeBinaryPath = "";

        if(SystemUtils.IS_OS_MAC){
            chromeBinaryPath = LOCAL_DRIVER_BINARY_PATH + "chromedriver-v" + CHROME_DRIVER_VERSION + "-mac64/chromedriver";
        } else if(SystemUtils.IS_OS_LINUX){
            chromeBinaryPath = LOCAL_DRIVER_BINARY_PATH + "chromedriver-v" + CHROME_DRIVER_VERSION + "-linux64/chromedriver";
        } else if(SystemUtils.IS_OS_WINDOWS){
            chromeBinaryPath = LOCAL_DRIVER_BINARY_PATH + "chromedriver-v" + CHROME_DRIVER_VERSION + "-win32/chromedriver.exe";
        }

        File f = new File(chromeBinaryPath);
        System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());

        ChromeDriver driver = new ChromeDriver();
        return driver;
    }

    public void resetDriverTimeouts(){
        /* The driver will wait x seconds for every page to load */
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        /* The driver will wait x seconds for every element to be visible
         Increased the timeout to 10 seconds as IE needs more than 5 */
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
}
