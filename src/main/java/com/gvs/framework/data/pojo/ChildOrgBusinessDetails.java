package com.gvs.framework.data.pojo;

public class ChildOrgBusinessDetails {
    private String businessContactNumber;
    private String businessName;
    private String businessEmailId;

    public String getBusinessContactNumber () { return businessContactNumber; }
    public void setBusinessContactNumber (String businessContactNumber) { this.businessContactNumber = businessContactNumber; }

    public String getBusinessName () { return businessName; }
    public void setBusinessName (String businessName) { this.businessName = businessName; }

    public String getBusinessEmailId () { return businessEmailId; }
    public void setBusinessEmailId (String businessEmailId) { this.businessEmailId = businessEmailId; }

    @Override
    public String toString()
    {
        return "ClassPojo [businessContactNumber = "+businessContactNumber+", businessName = "+businessName+", businessEmailId = "+businessEmailId+"]";
    }
}
