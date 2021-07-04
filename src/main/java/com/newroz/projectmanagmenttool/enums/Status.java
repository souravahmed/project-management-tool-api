package com.newroz.projectmanagmenttool.enums;

public enum Status {
    CREATED("Created"),
    CANCELLED("Cancelled"),
    INPROGRESS("InProgress"),
    COMPLETED("Completed"),
    ONHOLD("OnHold");

    private String displayStatus;

    Status(String displayStatus) {
        this.displayStatus = displayStatus;
    }


    public String getDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(String displayStatus) {
        this.displayStatus = displayStatus;
    }

}
