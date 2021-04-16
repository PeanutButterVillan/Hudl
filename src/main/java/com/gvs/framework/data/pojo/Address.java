package com.gvs.framework.data.pojo;

public class Address {
    private String buildingName;
    private String country;
    private String streetName;
    private String townOrCity;
    private String county;
    private String buildingNumber;
    private String postCode;
    private String subBuildingName;

    public String getBuildingName () { return buildingName; }
    public void setBuildingName (String buildingName) { this.buildingName = buildingName; }

    public String getCountry () { return country; }
    public void setCountry (String country) { this.country = country; }

    public String getStreetName () { return streetName; }
    public void setStreetName (String streetName) { this.streetName = streetName; }

    public String getTownOrCity () { return townOrCity; }
    public void setTownOrCity (String townOrCity) { this.townOrCity = townOrCity; }

    public String getCounty () { return county; }
    public void setCounty (String county) { this.county = county; }

    public String getBuildingNumber () { return buildingNumber; }
    public void setBuildingNumber (String buildingNumber) { this.buildingNumber = buildingNumber; }

    public String getPostCode () { return postCode; }
    public void setPostCode (String postCode) { this.postCode = postCode; }

    public String getSubBuildingName () { return subBuildingName; }
    public void setSubBuildingName (String subBuildingName) { this.subBuildingName = subBuildingName; }

    @Override
    public String toString() {
        return "ClassPojo [buildingName = "+buildingName+", country = "+country+", streetName = "+streetName+", townOrCity = "+townOrCity+", county = "+county+", buildingNumber = "+buildingNumber+", postCode = "+postCode+", subBuildingName = "+subBuildingName+"]";
    }
}
