package com.gvs.framework.config.regression;

import com.gvs.framework.config.SpringContext;
import org.springframework.stereotype.Component;

@Component
public class RegressionPropertyManager {
    private static final String PROPERTY_SEPARATOR = ".";
    private static final String PAGE_PROPERTY_INDICATOR = "page";
    public static final String URL_PROPERTY_INDICATOR = "url";

    private static final String COSTCUTTER_SERVICE_PROPERTY_INDICATOR = "costcutter";
    private static final String SPRINGNATURE_SERVICE_PROPERTY_INDICATOR = "springnature";
    private static final String EQUALEXPERTSHOTEL_SERVICE_PROPERTY_INDICATOR = "equalexperts";
    private static final String RACINGPOST_SERVICE_PROPERTY_INDICATOR = "racingpost";

    private static final String APP_PROPERTY_INDICATOR = "app";
    public static final String PLATFORM_PROPERTY_NAME = "platform";

    public static final String ACCOUNT_MANAGEMENT_PAGE_INDICATOR = "accountManagement";

    private static final String DYNAMICS_PROPERTY_INDICATOR = "dynamics";
    public static final String CLIENT_ID_PROPERTY_INDICATOR = "clientId";
    public static final String CLIENT_SECRET_PROPERTY_INDICATOR = "clientSecret";
    public static final String TENANT_ID_PROPERTY_INDICATOR = "tenantId";

    public static final String PRIVACY_POLICY_VERSION_PROPERTY_NAME = "privacyPolicyVersion";
    public static final String COOKIES_POLICY_VERSION_PROPERTY_NAME = "cookiesPolicyVersion";
    public static final String APP_INSIGHTS_KEY_PROPERTY_NAME = "appInsightsKey";
    public static final String GOOGLE_TAG_MANAGER_ID_PROPERTY_NAME = "googleTagManagerId";

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

    public String getSpringNatureServiceUrl(){
        return springContext.getEnvProperty(
                regressionEnvironment.toString()+PROPERTY_SEPARATOR+
                        SPRINGNATURE_SERVICE_PROPERTY_INDICATOR + PROPERTY_SEPARATOR + URL_PROPERTY_INDICATOR);
    }

    public String getEqualExpertsHotelServiceUrl(){
        return springContext.getEnvProperty(
                regressionEnvironment.toString()+PROPERTY_SEPARATOR+
                        EQUALEXPERTSHOTEL_SERVICE_PROPERTY_INDICATOR + PROPERTY_SEPARATOR + URL_PROPERTY_INDICATOR);
    }

    public String getRacingPostServiceUrl(){
        return springContext.getEnvProperty(
                regressionEnvironment.toString()+PROPERTY_SEPARATOR+
                        RACINGPOST_SERVICE_PROPERTY_INDICATOR + PROPERTY_SEPARATOR + URL_PROPERTY_INDICATOR);
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

    public String getDynamicsProperty(String property){
        return springContext.getEnvProperty(
                regressionEnvironment.toString()+PROPERTY_SEPARATOR+
                        DYNAMICS_PROPERTY_INDICATOR + PROPERTY_SEPARATOR + property);
    }

    public String getFullyQualifiedPageUrl(String page){
        return getAppUrl() + springContext.getEnvProperty(
                PAGE_PROPERTY_INDICATOR + PROPERTY_SEPARATOR+
                        URL_PROPERTY_INDICATOR + PROPERTY_SEPARATOR+
                        page);
    }

    public String getEnvProperty(String propertyName) { return springContext.getEnvProperty(propertyName); }

}
