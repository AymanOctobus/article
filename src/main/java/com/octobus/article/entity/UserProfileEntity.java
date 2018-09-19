package com.octobus.article.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.octobus.article.model.City;
import com.octobus.article.model.Country;

@Document(collection = "user_profile")
public class UserProfileEntity {

	@Id
	private String userId;
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
	private City city;
	private Country country;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
}
