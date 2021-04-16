package com.gvs.framework.config;

import io.cucumber.core.api.Scenario;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * A config class used to share variables/dynamic states across the cucumber classes.
 */
@Component
@Scope("cucumber-glue")
public class ScenarioContext {
    public static final String DEFAULT_FIRST_NAME = "Joe";
    public static final String DEFAULT_LAST_NAME = "Bloggs";
    public static final String DEFAULT_FULL_NAME = DEFAULT_FIRST_NAME + " " + DEFAULT_LAST_NAME;
    public static final String DEFAULT_PASSWORD = "testpass1*";
    public static final String DEFAULT_MEMORABLE_WORD = "testpass";
    public static final String DEFAULT_SECURITY_WORD = "security";
    public static final String DEFAULT_SECURITY_HINT = "security hint";
    public static final String DEFAULT_PHONE_NUMBER = "12345678901";
    public static final String DEFAULT_POSTCODE = "SA65LU";
    public static final String DEFAULT_COMPANY_NAME = "Capgemini";
    public static final String DEFAULT_SUB_BUILDING_NAME = "subBuilding";
    public static final String DEFAULT_BUILDING_NUMBER = "123";
    public static final String DEFAULT_BUILDING_NAME = "building";
    public static final String DEFAULT_STREET = "street";
    public static final String DEFAULT_TOWN = "town";
    public static final String DEFAULT_COUNTY = "county";
    public static final String DEFAULT_COUNTRY = "France";

    public static final String USER_EMAIL_KEY = "userEmailAddress";
    public static final String USER_ID_KEY = "userID";
    public static final String USER_PASSWORD_KEY = "userPassword";

    public static final String DEFAULT_TM_FIRST_NAME = "TMandy";
    public static final String DEFAULT_TM_LAST_NAME = "TMjones";

    public static final String DEFAULT_DUPLICATE_CHARITY_NUMBER = "1994995";
    public static final String DEFAULT_CHARITY_COUNTRY = "England";

    public static final String DEFAULT_DUPLICATE_LIMITED_NUMBER = "11606664";

    private HashMap<String,String> sharedScope;
    private String debugString;
    private Scenario scenario;

    public String getValue(String key){
        return this.sharedScope.get(key);
    }

    public void addValue(String key, String value){
        this.sharedScope.put(key,value);
        this.debugString += "; " + key + ":" + value;
    }

    public Scenario getScenario() { return scenario; }

    public void setScenario(Scenario scenario) { this.scenario = scenario; }

    public ScenarioContext(){
        sharedScope = new HashMap<>();
        debugString = "";
    }
}
