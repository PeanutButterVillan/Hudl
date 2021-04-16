package com.gvs.framework.pages.enums;

public enum EnrolmentStatusType {
    INCOMPLETE(1),
    PENDING(2),
    COMPLETE_APPROVED(3),
    COMPLETE_REJECTED(4);

    private int value;
    EnrolmentStatusType(int elementValue){ this.value = elementValue; }
    public String toString(){ return String.valueOf(this.value); }
}
