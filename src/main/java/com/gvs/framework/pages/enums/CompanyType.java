package com.gvs.framework.pages.enums;

public enum CompanyType {
    SOLE_TRADER("soleTrader"),
    LIMITED("ltd"),
    PLC("plc"),
    LLP("llp"),
    NON_UK_ORG("nonUkOrganisation"),
    CHARITY("charity");

    private String value;
    CompanyType(String elementValue){ this.value = elementValue; }
    public String toString(){ return this.value; }

    public static CompanyType getMatch(String text) {
        for (CompanyType type : CompanyType.values()) {
            if (type.toString().equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new RuntimeException("Company Type '" + text + "' unsupported");
    }
}
