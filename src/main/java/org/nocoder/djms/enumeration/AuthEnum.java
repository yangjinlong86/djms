package org.nocoder.djms.enumeration;

/**
 * @author Created by jason on 17/11/2.
 */
public enum AuthEnum {
    // 当前用户
    CURRENT_USER("current_user");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    AuthEnum(String value) {
        this.value = value;
    }
}
