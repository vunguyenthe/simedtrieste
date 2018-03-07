package com.small.business.model.user;

import com.small.business.model.base.BaseMessage;

public class User extends BaseMessage {

    private String name;
    private String email;
    private String phoneNumber;
    private String password1;
    private String password2;
    private String address;
    private Integer userType;
    private Integer activated;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getActivated() {
		return activated;
	}
	public void setActivated(Integer activated) {
		this.activated = activated;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", password1=" + password1
				+ ", password2=" + password2 + ", address=" + address
				+ ", userType=" + userType + ", activated=" + activated + "]";
	}
    
}
