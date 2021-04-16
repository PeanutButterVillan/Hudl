package com.gvs.framework.config;

import com.gvs.framework.config.regression.RegressionPropertyManager;
import com.gvs.framework.util.driver.DriverCapabilityManager;
import com.gvs.framework.util.driver.DriverFactory;
import com.gvs.framework.util.driver.DriverUtils;
//import org.apache.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

/** '@PostConstruct' and '@PreDestroy' run in the setup and teardown of each thread which is reused by tests,
 * so cannot be used as 'BeforeSuite'/'AfterSuite' functionality. */
@ComponentScan("com/gvs/framework")
@PropertySource({"classpath:config/application.properties", "classpath:config/application-${spring.profiles.active}.properties"})
public class SpringContext {
//    private static Logger log = Logger.getLogger(SpringContext.class);

    public static final String MAVEN_PROFILE_PROPERTY_NAME = "maven.profile";
    public static final String GRID_URL_PROPERTY_NAME = "grid.url";
    public static final String LOCAL_ID_PROPERTY_NAME = "localidentifier";
    public static final String PROJECT_NAME_PROPERTY_NAME = "project.name";
    public static final String BUILD_NUM_PROPERTY_NAME = "build";
    public static final String BROWSER_PROPERTY_NAME = "browser";
    public static final String PLATFORM_PROPERTY_NAME = "platform";
    public static final String BSTACK_URL_PROPERTY_NAME = "browserstackurl";
    public static final String BSTACK_LOCAL_BOOL_PROPERTY_NAME = "browserstack.local";
    public static final String IDM_ENV_PROPERTY_NAME = "idm.env";
    public static final String RESOURCES_DIR_PROPERTY_NAME = "resources.dir";

    @Autowired
    public Environment env;

    public String getEnvProperty(String propertyName) {
        if(env.getProperty(propertyName) == null){
//            log.debug("Could not find property with reference: '" + propertyName + "', returning empty string");
            return "";
        } else {
            return env.getProperty(propertyName);
        }
    }

    @Bean
    public DriverCapabilityManager getDriverCapabilityManager(){
        return new DriverCapabilityManager();
    }

    @Bean
    public DriverFactory getDriverFactory(DriverCapabilityManager driverCapabilityManager){
        return new DriverFactory(driverCapabilityManager);
    }

    @Bean
    public DriverUtils getDriverUtils(DriverFactory driverFactory){
        return new DriverUtils(driverFactory);
    }

    @Bean
    public RegressionPropertyManager getRegressionPropertyManager(){
        return new RegressionPropertyManager(this);
    }

    @Bean
    @Scope("cucumber-glue")
    public RemoteWebDriver getDriver(DriverFactory driverFactory) {
        return driverFactory.getInstance();
    }
}
