package com.octobus.article.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.octobus.article.model.UserProfile;
import com.octobus.article.service.UserProfileService;

@RestController
public class UserProfileController {

	@Autowired
	private UserProfileService service;
	
	@PostMapping("user/profile")
	public ResponseEntity<Object> createProfile(@RequestBody UserProfile userProfile){
		UserProfile createdUserProfile = service.createUserProfile(userProfile);
		return new ResponseEntity<>(createdUserProfile,HttpStatus.CREATED);
	}
	
	@GetMapping("user/profile/{emailId}")
	public ResponseEntity<UserProfile> getUserProfile(@PathVariable("emailId") String emailId){
		UserProfile userProfile =service.getUserProfileByEmailId(emailId);
		return new ResponseEntity<>(userProfile,HttpStatus.OK);
	}
}
