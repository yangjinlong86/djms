package org.dj.bms.enumeration;

/**
 * Created by jason on 17/11/2.
 */
public enum AuthEnum {
    CURRENT_USER("current_user");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    AuthEnum(String value){
        this.value = value;
    }
}
