package org.dj.bms.enumeration;

/**
 * @author jason
 * @date 17/11/29.
 */
public enum OrganizationEnum {
    CORP("1"), DEPT("2");

    private String value;

    public String val() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    OrganizationEnum(String value) {
        this.value = value;
    }

}
