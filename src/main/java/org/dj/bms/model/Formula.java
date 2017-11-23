package org.dj.bms.model;

/**
 * 美容方案信息
 * 
 * @ClassName: Formula
 * @author pufangfei@163.com
 * @date 2017年11月23日 下午9:51:13
 */
public class Formula extends Bms {

	private static final long serialVersionUID = 1L;
	/**
	 * 单位id
	 */
	private String corpId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 应用场景 单值代码
	 */
	private String scene;
	/**
	 * 类型 单值代码
	 */
	private String type;
	/**
	 * 预估金额 单位 元
	 */
	private Double amount;

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId == null ? null : corpId.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene == null ? null : scene.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}