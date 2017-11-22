package org.dj.bms.model;

import java.util.Date;

public class StockUse extends Bms {

	/**
	*/
	private static final long serialVersionUID = 2939525669766286535L;

	private String stockId;

	private String corpId;

	private String outUserId;

	private Long outCount;

	private Date outDate;

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getOutUserId() {
		return outUserId;
	}

	public void setOutUserId(String outUserId) {
		this.outUserId = outUserId;
	}

	public Long getOutCount() {
		return outCount;
	}

	public void setOutCount(Long outCount) {
		this.outCount = outCount;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

}