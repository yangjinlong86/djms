package org.dj.bms.model;

public class FormulaDetails extends Bms {

	/**
	 */

	private static final long serialVersionUID = 1L;

	private String formulaId;

	private String productId;

	private String step;

	private String capacity;

	private String mete;

	private Double amount;

	private Short sort;

	public String getFormulaId() {
		return formulaId;
	}

	public void setFormulaId(String formulaId) {
		this.formulaId = formulaId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Short getSort() {
		return sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

}