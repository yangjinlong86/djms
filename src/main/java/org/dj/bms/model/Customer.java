package org.dj.bms.model;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.dj.bms.enumeration.DictEnum;
import org.dj.bms.utils.DateUtils;
import org.dj.bms.utils.ECacheUtils;

public class Customer extends Bms {

	/**
	 * @Fields serialVersionUID : TODO
	 */

	private static final long serialVersionUID = -1016554063470827766L;

	private String corpId;

	private String num;

	private String name;

	private String region;

	private String address;

	private String customerType;

	private Date birthday;

	private String telephone;

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId == null ? null : corpId.trim();
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num == null ? null : num.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region == null ? null : region.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType == null ? null : customerType.trim();
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone == null ? null : telephone.trim();
	}

	public String getBirth() {
		return DateUtils.date2Str(birthday, "yyyy-MM-dd");
	}

	public String getAddr() {
		StringBuilder addr = new StringBuilder();
		addr.append(ECacheUtils.getCodeName(DictEnum.AREA, region));
		if (StringUtils.isNotBlank(address)) {
			addr.append(address);
		}
		return addr.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", corpId=").append(corpId);
		sb.append(", num=").append(num);
		sb.append(", name=").append(name);
		sb.append(", region=").append(region);
		sb.append(", address=").append(address);
		sb.append(", customerType=").append(customerType);
		sb.append(", birthday=").append(birthday);
		sb.append(", telephone=").append(telephone);
		sb.append("]");
		return sb.toString();
	}
}