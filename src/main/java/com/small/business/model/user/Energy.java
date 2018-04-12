package com.small.business.model.user;

public class Energy extends BaseMessage{
	private Integer room = 0;
	private String csv_file_name ="";
	private Integer month =2;
	private float savedEnergy =0.0f;
	private String dateCollected="";
	public Integer getRoom() {
		return room;
	}
	public void setRoom(Integer room) {
		this.room = room;
	}
	public String getCsv_file_name() {
		return csv_file_name;
	}
	public void setCsv_file_name(String csv_file_name) {
		this.csv_file_name = csv_file_name;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public float getSavedEnergy() {
		return savedEnergy;
	}
	public void setSavedEnergy(float savedEnergy) {
		this.savedEnergy = savedEnergy;
	}
	public String getDateCollected() {
		return dateCollected;
	}
	public void setDateCollected(String dateCollected) {
		this.dateCollected = dateCollected;
	}
	@Override
	public String toString() {
		return "energy [room=" + room + ", csv_file_name=" + csv_file_name + ", month=" + month + ", savedEnergy="
				+ savedEnergy + ", dateCollected=" + dateCollected + "]";
	}
	
}
