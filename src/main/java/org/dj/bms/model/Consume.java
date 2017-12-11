package org.dj.bms.model;

/**
 * 消费记录
 *
 * @author jason
 */
public class Consume extends Bms {
	private static final long serialVersionUID = 1L;
	private String formulaId;

	private String customerId;

	private String corpId;

	private String userId;

	private String consumeType;

    private String amount;

    private String comment;

    private String customerName;
    private String userName;
    private String corpName;

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFormulaId() {
        return formulaId;
    }

	public void setFormulaId(String formulaId) {
		this.formulaId = formulaId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getConsumeType() {
		return consumeType;
	}

	public void setConsumeType(String consumeType) {
		this.consumeType = consumeType;
	}


    @Override
    public String toString() {
        return "Consume{" +
                "formulaId='" + formulaId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", corpId='" + corpId + '\'' +
                ", userId='" + userId + '\'' +
                ", consumeType='" + consumeType + '\'' +
                ", amount='" + amount + '\'' +
                ", comment='" + comment + '\'' +
                ", customerName='" + customerName + '\'' +
                ", userName='" + userName + '\'' +
                ", corpName='" + corpName + '\'' +
                '}';
    }
}