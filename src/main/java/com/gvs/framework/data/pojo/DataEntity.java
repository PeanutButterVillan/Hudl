package com.gvs.framework.data.pojo;

public class DataEntity {

    private BranchDetails branchDetails;

    public BranchDetails getBranchDetails () { return branchDetails; }
    public void setBranchDetails (BranchDetails branchDetails) { this.branchDetails = branchDetails; }


    @Override
    public String toString()
    {
        return "ClassPojo [branchDetails = "+branchDetails+"]";
    }
}
