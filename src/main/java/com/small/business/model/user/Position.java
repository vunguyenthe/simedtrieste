package com.small.business.model.user;

import com.small.business.model.base.BaseMessage;

public class Position extends BaseMessage{
	Long userId = 0L;
	Long longtitude = 0L;
	Long latitude = 0L;
	String dateChecked;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getDateChecked() {
		return dateChecked;
	}
	public void setDateChecked(String dateChecked) {
		this.dateChecked = dateChecked;
	}
	@Override
	public String toString() {
		return "Position [userId=" + userId + ", longtitude=" + longtitude
				+ ", latitude=" + latitude + ", dateChecked=" + dateChecked
				+ "]";
	}
	
}
