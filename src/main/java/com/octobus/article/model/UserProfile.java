package com.octobus.article.model;

public class UserProfile {

	private String firstName;
	private String lastName;
	private String middleName;
	private String dob;
	private String photo;
	private String emailId;
	private boolean isUserActive;
	private String whenCreated;
	private String whenUpdated;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public boolean isUserActive() {
		return isUserActive;
	}
	public void setUserActive(boolean isUserActive) {
		this.isUserActive = isUserActive;
	}
	public String getWhenCreated() {
		return whenCreated;
	}
	public void setWhenCreated(String whenCreated) {
		this.whenCreated = whenCreated;
	}
	public String getWhenUpdated() {
		return whenUpdated;
	}
	public void setWhenUpdated(String whenUpdated) {
		this.whenUpdated = whenUpdated;
	}
	
	
}
