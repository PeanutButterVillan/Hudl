package com.gvs.framework.pages.enums;

public enum FieldErrorType {
  NAME_REQUIRED("enter a first or given name"),
  NAME_LENGTH("too long"),
  NAME_INVALID("without special characters"),
  PHONE_REQUIRED("enter a telephone number"),
  PHONE_LENGTH("between 5 and 20 characters"),
  PHONE_INVALID("using only these characters"),
  POSTCODE_REQUIRED("enter a postcode"),
  POSTCODE_LENGTH("is between 5 and 8 characters"),
  POSTCODE_INVALID("using only these characters"),
  PERSONAL_INTERNAT_POSTCODE_LENGTH("the zip or postal code is too long"),
  INTERNAT_POSTCODE_LENGTH("too long"),
  INTERNAT_POSTCODE_INVALID("without special characters"),
  CHARITY_LENGTH_ENGLAND("between 6-7 characters"),
  CHARITY_LENGTH_SCOTLAND("that is 8 characters"),
  CHARITY_LENGTH_NI("that is 9 characters"),
  CHARITY_NAME_EMPTY("enter a charity name");

  private final String errorMessage;

  private FieldErrorType(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
