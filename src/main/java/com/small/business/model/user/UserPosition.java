package com.small.business.model.user;

public class UserPosition {
	Long userId = 0L;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
	Long longtitude = 0L;
	Long latitude = 0L;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(Long longtitude) {
		this.longtitude = longtitude;
	}
	public Long getLatitude() {
		return latitude;
	}
	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}
	
}
