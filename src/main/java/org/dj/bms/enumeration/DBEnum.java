package org.dj.bms.enumeration;

/**
 * Created by jason on 17/11/5.
 */
public enum DBEnum {
    // 保存失败
    SAVE_FAILED(0);

    private Integer value;

    public Integer getValue() {
        return value;
    }

    DBEnum(Integer value){
        this.value = value;
    }
}