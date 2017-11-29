package org.dj.bms.model;

import java.util.Date;

public class Order extends Bms {

	/**
	*/

	private static final long serialVersionUID = 1L;

	private String corpId;

	private String customerId;

	private String userId;

	private String type;

	private String useType;

	private Date endValidity;

	private String orderSerive;

	private String orderCount;

	private String remainCount;

	private Double amount;

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public Date getEndValidity() {
		return endValidity;
	}

	public void setEndValidity(Date endValidity) {
		this.endValidity = endValidity;
	}

	public String getOrderSerive() {
		return orderSerive;
	}

	public void setOrderSerive(String orderSerive) {
		this.orderSerive = orderSerive;
	}

	public String getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}

	public String getRemainCount() {
		return remainCount;
	}

	public void setRemainCount(String remainCount) {
		this.remainCount = remainCount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}