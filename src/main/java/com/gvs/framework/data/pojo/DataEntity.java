package com.gvs.framework.data.pojo;

public class DataEntity {
    private TeamMember teamMember;
    private Address address;
    private ChildOrgBusinessDetails childOrgBusinessDetails;
    private AdminOrDirector adminOrDirector;
    private ChildOrgAddress childOrgAddress;
    private BusinessDetails businessDetails;
    private Intermediary intermediary;

    public TeamMember getTeamMember () { return teamMember; }
    public void setTeamMember (TeamMember teamMember) { this.teamMember = teamMember; }

    public Address getAddress () { return address; }
    public void setAddress (Address address) { this.address = address; }

    public ChildOrgBusinessDetails getChildOrgBusinessDetails () { return childOrgBusinessDetails; }
    public void setChildOrgBusinessDetails (ChildOrgBusinessDetails childOrgBusinessDetails) { this.childOrgBusinessDetails = childOrgBusinessDetails; }

    public AdminOrDirector getAdminOrDirector () { return adminOrDirector; }
    public void setAdminOrDirector (AdminOrDirector adminOrDirector) { this.adminOrDirector = adminOrDirector; }

    public ChildOrgAddress getChildOrgAddress () { return childOrgAddress; }
    public void setChildOrgAddress (ChildOrgAddress childOrgAddress) { this.childOrgAddress = childOrgAddress; }

    public BusinessDetails getBusinessDetails () { return businessDetails; }
    public void setBusinessDetails (BusinessDetails businessDetails) { this.businessDetails = businessDetails; }

    public Intermediary getIntermediary () { return intermediary; }
    public void setIntermediary (Intermediary intermediary) { this.intermediary = intermediary; }

    @Override
    public String toString()
    {
        return "ClassPojo [teamMember = "+teamMember+", address = "+address+", childOrgBusinessDetails = "+childOrgBusinessDetails+", adminOrDirector = "+adminOrDirector+", childOrgAddress = "+childOrgAddress+", businessDetails = "+businessDetails+", intermediary = "+intermediary+"]";
    }
}
