package org.dj.bms.model;

import java.util.Date;

public class Stock extends Bms {

	/**
	*/

	private static final long serialVersionUID = 4124839761528698168L;
	/**
	 * 单位ID
	 */
	private String corpId;
	/**
	 * 物品ID
	 */
	private String varietyId;
	/**
	 * 物品名称
	 */
	private String name;
	/**
	 * 容量
	 */
	private String capacity;
	/**
	 * 计量单位 单值代码
	 */
	private String mete;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 总金额 单位 元
	 */
	private Double amount;

	private String status;
	/**
	 * 入库时间
	 */
	private Date inDate;
	/**
	 * 总数量
	 */
	private Long count;
	/**
	 * 剩余数量
	 */
	private Long remainCount;
	/**
	 * 有效期
	 */
	private Date endValidity;

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getVarietyId() {
		return varietyId;
	}

	public void setVarietyId(String varietyId) {
		this.varietyId = varietyId;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Long getRemainCount() {
		return remainCount;
	}

	public void setRemainCount(Long remainCount) {
		this.remainCount = remainCount;
	}

	public Date getEndValidity() {
		return endValidity;
	}

	public void setEndValidity(Date endValidity) {
		this.endValidity = endValidity;
	}

}