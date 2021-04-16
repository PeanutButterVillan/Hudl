package com.gvs.framework.pages.enums;

public enum AccountAccessType {
    BUSINESS("business"),
    PERSONAL("personal");

    private String value;
    AccountAccessType(String elementValue){ this.value = elementValue; }
    public String toString(){ return this.value; }
}
