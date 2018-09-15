package com.octobus.article.service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

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
import com.octobus.article.model.UserProfile;
import com.octobus.article.repository.UserProfileRepository;

@Service
public class UserProfileService {

	@Autowired
	private UserProfileRepository repository;
	
	@Autowired
	GridFsTemplate gridOperations;
	
	
	public UserProfile createUserProfile(UserProfile userProfile){
		
		String photoId = saveProfileImage(userProfile);
		
		UserProfileEntity entity = new UserProfileEntity();
		entity.setFirstName(userProfile.getFirstName());
		entity.setLastName(userProfile.getLastName());
		entity.setMiddleName(userProfile.getMiddleName());
		entity.setDob(userProfile.getDob());
		entity.setPhotoId(photoId);
		entity.setEmailId(userProfile.getEmailId());
		entity.setMyPassword(userProfile.getMyPassword());
		entity.setCity(userProfile.getCity());
		entity.setCountry(userProfile.getCountry());
		entity.setUserActive(true);
		entity.setWhenCreated(LocalDateTime.now());
		entity.setWhenUpdated(LocalDateTime.now());
		repository.save(entity);
		userProfile.setUserActive(entity.isUserActive());
		userProfile.setWhenCreated(entity.getWhenCreated());
		userProfile.setWhenUpdated(entity.getWhenUpdated());
		return userProfile;
	}
	
	public UserProfile getUserProfileByEmailId(String emailId){
		UserProfileEntity  entity = repository.findByEmailId(emailId);
		UserProfile userProfile = new UserProfile();
		userProfile.setFirstName(entity.getFirstName());
		userProfile.setLastName(entity.getLastName());
		userProfile.setMiddleName(entity.getMiddleName());
		userProfile.setDob(entity.getDob());
		//TODO : set the profile photo url
		userProfile.setPhoto(null);
		userProfile.setEmailId(entity.getEmailId());
		userProfile.setMyPassword(entity.getMyPassword());
		userProfile.setUserActive(entity.isUserActive());
		userProfile.setCity(entity.getCity());
		userProfile.setCountry(entity.getCountry());
		userProfile.setWhenCreated(entity.getWhenCreated());
		userProfile.setWhenUpdated(entity.getWhenUpdated());
		return userProfile;
	}
	
	private String saveProfileImage(UserProfile userProfile) {
		String imageId = null;
		if(null != userProfile.getPhoto()){
			try{
				DBObject metaData = new BasicDBObject();
				InputStream imageStream = userProfile.getPhoto().getInputStream();
				metaData.put("emailId", userProfile.getEmailId());
				imageId = gridOperations.store(imageStream, userProfile.getPhoto().getOriginalFilename(),userProfile.getPhoto().getContentType(), metaData).get().toString();
				System.out.println("ImageFileId = " + imageId);
			}catch(IOException e){
				e.printStackTrace();
			}
			
		}
		return imageId;
	}
	
	public GridFsResource retrieveProfileImage(String imageId, String fileName){
		GridFSFile imageFile = gridOperations.findOne(new Query(Criteria.where("_id").is(imageId).and("filename").is(fileName)));
		return new GridFsResource(imageFile);
	}
	
}
