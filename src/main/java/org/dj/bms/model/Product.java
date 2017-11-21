package org.dj.bms.model;

import java.util.Date;

public class Product extends Bms {

	/**
	*/

	private static final long serialVersionUID = 1L;

	private String name;

	private String corpId;

	private String capacity;

	private String mete;

	private String type;

	private Double amount;

	private Date endValidity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getMete() {
		return mete;
	}

	public void setMete(String mete) {
		this.mete = mete;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public Date getEndValidity() {
		return endValidity;
	}

	public void setEndValidity(Date endValidity) {
		this.endValidity = endValidity;
	}

}