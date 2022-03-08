package com.gvs.framework.config.regression;

import com.gvs.framework.config.SpringContext;
import org.springframework.stereotype.Component;

@Component
public class RegressionPropertyManager {
    private static final String PROPERTY_SEPARATOR = ".";
    private static final String PAGE_PROPERTY_INDICATOR = "page";
    public static final String URL_PROPERTY_INDICATOR = "url";

    private static final String COSTCUTTER_SERVICE_PROPERTY_INDICATOR = "costcutter";

    private static final String APP_PROPERTY_INDICATOR = "app";
    public static final String PLATFORM_PROPERTY_NAME = "platform";


    private SpringContext springContext;

    private RegressionEnvironments regressionEnvironment;

    public RegressionPropertyManager(SpringContext springContext){
        this.springContext = springContext;
        if(springContext.getEnvProperty("spring.profiles.active").equalsIgnoreCase("Regression")){
            regressionEnvironment = RegressionEnvironments.getMatch(springContext.getEnvProperty(SpringContext.PLATFORM_PROPERTY_NAME));
        } else {
            regressionEnvironment = RegressionEnvironments.IDEV;
        }
    }

    public String getCostCutterServiceUrl(){
        return springContext.getEnvProperty(
                regressionEnvironment.toString()+PROPERTY_SEPARATOR+
                        COSTCUTTER_SERVICE_PROPERTY_INDICATOR + PROPERTY_SEPARATOR + URL_PROPERTY_INDICATOR);
    }


   public String getAppUrl(){
        return springContext.getEnvProperty(
                regressionEnvironment.toString()+PROPERTY_SEPARATOR+
                        APP_PROPERTY_INDICATOR + PROPERTY_SEPARATOR + URL_PROPERTY_INDICATOR);
    }

    public String getPageUrl(String page){
        return springContext.getEnvProperty(
                PAGE_PROPERTY_INDICATOR + PROPERTY_SEPARATOR+
                        URL_PROPERTY_INDICATOR + PROPERTY_SEPARATOR+
                        page);
    }


    public String getFullyQualifiedPageUrl(String page){
        return getAppUrl() + springContext.getEnvProperty(
                PAGE_PROPERTY_INDICATOR + PROPERTY_SEPARATOR+
                        URL_PROPERTY_INDICATOR + PROPERTY_SEPARATOR+
                        page);
    }

    public String getEnvProperty(String propertyName) { return springContext.getEnvProperty(propertyName); }

}
