package com.gvs.framework.cukes.runner;

import com.gvs.framework.config.SpringContext;
import com.gvs.framework.config.regression.RegressionEnvironments;

import com.google.common.base.Preconditions;
import cucumber.api.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/reports/json/cucumber.json"},
        features = {"src/main/resources/features/regression"},
        glue = {"com/gvs/framework/cukes/bdd"},
        tags = {"@equalExperts"})
public class RunCukesRegressionTest {
    private static final Logger log = Logger.getLogger(RunCukesRegressionTest.class);

    @BeforeClass
    public static void setupSuite() {

        log.info("BEFORECLASS: - we'll add in a restTemplate test here");
/*
        RestTemplate restTemplate = new RestTemplate();
        URI customerApiUrl;
        ResponseEntity<String> response = restTemplate.exchange(customerApiUrl, HttpMethod.GET,null,String.class);
        log.info("Response from Customer API: " + response.getStatusCode() + " : " + response.getBody());
*/
    }
}
