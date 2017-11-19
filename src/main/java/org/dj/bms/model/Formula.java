package org.dj.bms.model;

public class Formula extends Bms {

	private static final long serialVersionUID = 1L;

	private String corpId;

	private String name;

	private String scene;

	private String type;

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