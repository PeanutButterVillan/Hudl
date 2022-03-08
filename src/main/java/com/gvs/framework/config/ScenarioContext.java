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

    public static final String DEFAULT_TOWN = "York";

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
