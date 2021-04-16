package com.gvs.framework.pages.enums;

public enum PageErrorType {
    ALREADY_AN_INDIVIDUAL_ERROR("You already have an individual account"),
    ORGANISATION_ALREADY_EXISTS_ERROR("Sorry, this organisation already exists"),
    CONTACT_DETAILS_REQUIRED_ERROR("Your contact details must be completed"),
    NAME_DETAILS_REQUIRED_ERROR("Your name must be completed");

    private final String errorMessage;

    private PageErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
