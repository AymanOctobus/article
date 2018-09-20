package com.octobus.article.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.octobus.article.entity.UserProfileEntity;
import com.octobus.article.exception.UserAlreadyExistException;
import com.octobus.article.exception.UserNotFoundException;
import com.octobus.article.model.UserProfile;
import com.octobus.article.repository.UserProfileRepository;

@Service
public class UserProfileService {

	@Autowired
	private UserProfileRepository repository;
	
	
	public UserProfile createUserProfile(UserProfile userProfile) throws UserAlreadyExistException{
		if(!isUserExist(userProfile.getEmailId())){			
			UserProfileEntity entity = new UserProfileEntity();
			entity.setFirstName(userProfile.getFirstName());
			entity.setLastName(userProfile.getLastName());
			entity.setMiddleName(userProfile.getMiddleName());
			entity.setDob(userProfile.getDob());
			entity.setPhoto(null);
			entity.setEmailId(userProfile.getEmailId());
			entity.setMyPassword(userProfile.getMyPassword());
			entity.setCity(userProfile.getCity());
			entity.setCountry(userProfile.getCountry());
			entity.setUserActive(true);
			entity.setWhenCreated(LocalDateTime.now());
			entity.setWhenUpdated(LocalDateTime.now());
			repository.save(entity);
			userProfile.setUserId(entity.getUserId());
			userProfile.setUserActive(entity.isUserActive());
			userProfile.setWhenCreated(entity.getWhenCreated());
			userProfile.setWhenUpdated(entity.getWhenUpdated());
			
		}else{
			throw new UserAlreadyExistException("User already exist with this email id");
		}
		return userProfile;
	}
	
	public UserProfile getUserProfileByEmailId(String emailId) throws UserNotFoundException{
		UserProfileEntity  entity = repository.findByEmailId(emailId);
		if(null == entity){
			throw new UserNotFoundException("User not found with this email id");
		}
		UserProfile userProfile = new UserProfile();
		userProfile.setUserId(entity.getUserId());
		userProfile.setFirstName(entity.getFirstName());
		userProfile.setLastName(entity.getLastName());
		userProfile.setMiddleName(entity.getMiddleName());
		userProfile.setDob(entity.getDob());
		userProfile.setPhoto(entity.getPhoto());
		userProfile.setEmailId(entity.getEmailId());
		userProfile.setMyPassword(entity.getMyPassword());
		userProfile.setUserActive(entity.isUserActive());
		userProfile.setCity(entity.getCity());
		userProfile.setCountry(entity.getCountry());
		userProfile.setWhenCreated(entity.getWhenCreated());
		userProfile.setWhenUpdated(entity.getWhenUpdated());
		return userProfile;
	}
	
	private boolean isUserExist(String emailId){
		UserProfileEntity  entity = repository.findByEmailId(emailId);
		if(null != entity){
			return true;
		}
		return false;
	}

	public void updateUserProfile(String userId,String profilePhotoUri) {
		Optional<UserProfileEntity>  optional = repository.findById(userId);
		if(optional.isPresent()){
			UserProfileEntity entity = optional.get();
			entity.setPhoto(profilePhotoUri);
			repository.save(entity);
		}	
	}
}
