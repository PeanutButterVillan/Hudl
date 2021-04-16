package com.gvs.framework.config.regression;

public enum RegressionEnvironments {
    IDEV("idev"),
    ITEST("itest");

    private String propertyValue;

    RegressionEnvironments(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    @Override
    public String toString() {
        return this.propertyValue;
    }

    public static RegressionEnvironments getMatch(String text) {
        for (RegressionEnvironments platform : RegressionEnvironments.values()) {
            if (platform.toString().equalsIgnoreCase(text.toLowerCase())) {
                return platform;
            }
        }
        throw new RuntimeException("Regression Environment '" + text + "' unsupported.");
    }
}