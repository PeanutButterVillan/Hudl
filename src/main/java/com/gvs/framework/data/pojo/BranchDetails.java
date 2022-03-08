package com.gvs.framework.data.pojo;

public class BranchDetails {

    private String branchTown;

    public String getBranchTown () { return branchTown; }
    public void setBranchTown (String branchTown) { this.branchTown = branchTown; }

    @Override
    public String toString()
    {
        return "ClassPojo [branchTown = " + branchTown + "]";
    }
}
