package com.octobus.article.entity;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

public class UserProfileEntity {

	public String id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String dob;
	private String photoId;
	private String emailId;
	private String myPassword;
	private boolean isUserActive;
	private LocalDateTime whenCreated;
	private LocalDateTime whenUpdated;
	private String city;
	private String country;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
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
	public LocalDateTime getWhenCreated() {
		return whenCreated;
	}
	public void setWhenCreated(LocalDateTime whenCreated) {
		this.whenCreated = whenCreated;
	}
	public LocalDateTime getWhenUpdated() {
		return whenUpdated;
	}
	public void setWhenUpdated(LocalDateTime whenUpdated) {
		this.whenUpdated = whenUpdated;
	}
	public String getMyPassword() {
		return myPassword;
	}
	public void setMyPassword(String myPassword) {
		this.myPassword = myPassword;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
