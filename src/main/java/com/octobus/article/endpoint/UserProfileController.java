package com.octobus.article.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.octobus.article.model.UserProfile;

@RestController
public class UserProfileController {

	@PostMapping("user/profile")
	public ResponseEntity<Object> createProfile(@RequestBody UserProfile userProfile){
		return new ResponseEntity<>("Profile Created Successfully",HttpStatus.CREATED);
	}
}
