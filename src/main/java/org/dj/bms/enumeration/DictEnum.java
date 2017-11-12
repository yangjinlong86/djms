package org.dj.bms.enumeration;

/**
 * @ClassName: DictEnum
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月12日 下午5:19:15
 */

public enum DictEnum {
	//行政区划
	AREA("30101004");
	private String code;

	/**
	 * DictEnum
	 * @param code
	 */

	private DictEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
