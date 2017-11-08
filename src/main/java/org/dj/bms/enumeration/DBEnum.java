package org.dj.bms.enumeration;

/**
 * @author Created by jason on 17/11/5.
 */
public enum DBEnum {
	// 保存失败
	SAVE_FAILED(0),
	// 单条数据成功
	SAVE_SUCCESS(1);

	private Integer value;

	public Integer getValue() {
		return value;
	}

	DBEnum(Integer value) {
		this.value = value;
	}
}
