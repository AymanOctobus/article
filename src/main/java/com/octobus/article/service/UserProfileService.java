package com.octobus.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.octobus.article.entity.UserProfileEntity;
import com.octobus.article.model.UserProfile;
import com.octobus.article.repository.UserProfileRepository;

@Service
public class UserProfileService {

	@Autowired
	private UserProfileRepository repository;
	
	public UserProfile createUserProfile(UserProfile userProfile){
		UserProfileEntity entity = new UserProfileEntity();
		entity.setFirstName(userProfile.getFirstName());
		entity.setLastName(userProfile.getLastName());
		entity.setMiddleName(userProfile.getMiddleName());
		entity.setDob(userProfile.getDob());
		entity.setPhoto(userProfile.getPhoto());
		entity.setEmailId(userProfile.getEmailId());
		entity.setUserActive(userProfile.isUserActive());
		entity.setWhenCreated(userProfile.getWhenCreated());
		entity.setWhenUpdated(userProfile.getWhenUpdated());
		repository.save(entity);
		return userProfile;
	}
	
	public UserProfile getUserProfileByEmailId(String emailId){
		UserProfileEntity  entity = repository.findByEmailId(emailId);
		UserProfile userProfile = new UserProfile();
		userProfile.setFirstName(entity.getFirstName());
		userProfile.setLastName(entity.getLastName());
		userProfile.setMiddleName(entity.getMiddleName());
		userProfile.setDob(entity.getDob());
		userProfile.setPhoto(entity.getPhoto());
		userProfile.setEmailId(entity.getEmailId());
		userProfile.setUserActive(entity.isUserActive());
		userProfile.setWhenCreated(entity.getWhenCreated());
		userProfile.setWhenUpdated(entity.getWhenUpdated());
		return userProfile;
	}
	
}
