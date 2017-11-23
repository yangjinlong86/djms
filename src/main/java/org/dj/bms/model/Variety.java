package org.dj.bms.model;

public class Variety extends Bms {

	/**
	*/

	private static final long serialVersionUID = 1L;
	/**
	 * 单位ID
	 */
	private String corpId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 品牌
	 */
	private String brand;
	/**
	 * 类型
	 */
	private String type;

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}