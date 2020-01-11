package org.nocoder.djms.enumeration;

/**
 * @author Created by jason on 17/11/5.
 */
public enum DBEnum {
    // 操作失败
    OPERATION_FAILED(0),
    // 单条数据操作成功
    OPERATION_SUCCESS(1);

    private Integer value;

    public Integer getValue() {
        return value;
    }

    DBEnum(Integer value) {
        this.value = value;
    }
}
