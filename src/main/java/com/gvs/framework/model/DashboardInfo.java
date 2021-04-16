package com.gvs.framework.model;


//This page is under future implementation

public class DashboardInfo {


    enum CardNames {
        TeamAccess("Manage team access"),
        IntermediaryAccess("Manage intermediary access");

        private String Text;

        CardNames(String text) {
            this.Text = text;
        }

        public String Text() {
            return Text;
        }

    }
}
