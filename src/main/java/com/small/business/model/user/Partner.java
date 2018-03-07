package com.small.business.model.user;

import com.small.business.model.base.BaseMessage;

public class Partner extends BaseMessage {
	private Long userId = 0L;
	private String idCard;;
	private String passportNumber;
	private String tempAddress;
	private String permanentAddress;
	private String bankAccountNumberId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getTempAddress() {
		return tempAddress;
	}
	public void setTempAddress(String tempAddress) {
		this.tempAddress = tempAddress;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getBankAccountNumberId() {
		return bankAccountNumberId;
	}
	public void setBankAccountNumberId(String bankAccountNumberId) {
		this.bankAccountNumberId = bankAccountNumberId;
	}
	@Override
	public String toString() {
		return "Partner [idCard=" + idCard + ", passportNumber="
				+ passportNumber + ", tempAddress=" + tempAddress
				+ ", permanentAddress=" + permanentAddress
				+ ", bankAccountNumberId=" + bankAccountNumberId + "]";
	}
	
}
