package org.dj.bms.model;

import java.util.Date;

public class Order extends Bms {

	/**
	 * @Fields serialVersionUID : TODO
	 */

	private static final long serialVersionUID = 1L;
	/**
	 * 单位id
	 */
	private String corpId;
	/**
	 * 顾客id
	 */
	private String customerId;
	/**
	 * 美容师ID
	 */
	private String userId;
	/**
	 * 类型 单值代码
	 */
	private String type;
	/**
	 * 使用方式
	 */
	private String useType;
	/**
	 * 有效期
	 */
	private Date endValidity;
	/**
	 * 订单服务
	 */
	private String orderSerive;
	/**
	 * 消费金额
	 */
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}