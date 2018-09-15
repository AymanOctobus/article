package com.octobus.article.endpoint;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.octobus.article.model.UserProfile;
import com.octobus.article.service.UserProfileService;

@RestController
@CrossOrigin("*")
public class UserProfileController {

	@Autowired
	private UserProfileService service;

	@PostMapping("user/profile")
	public ResponseEntity<Object> createProfile(@RequestBody UserProfile userProfile) {
		UserProfile createdUserProfile = service.createUserProfile(userProfile);
		return new ResponseEntity<>(createdUserProfile, HttpStatus.CREATED);
	}

	@GetMapping("user/profile/{emailId}")
	public ResponseEntity<UserProfile> getUserProfile(@PathVariable("emailId") String emailId) {
		UserProfile userProfile = service.getUserProfileByEmailId(emailId);
		return new ResponseEntity<>(userProfile, HttpStatus.OK);
	}

	@GetMapping(value="user/profile/photo/{imageId}/{imageName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<GridFsResource> getProfilePhoto(@PathVariable("imageId") String imageId,@PathVariable("imageName") String imageName,HttpServletResponse response)
			throws IOException {
		GridFsResource file = service.retrieveProfileImage(imageId,imageName);
		//byte[] bytes = StreamUtils.copyToByteArray(file.getInputStream());
		try {
	        return ResponseEntity
	            .ok()
	            .header("Content-Disposition", String.format("inline; filename=\"" + file.getFilename() + "\""))
	            .contentType(MediaType.parseMediaType("image/jpeg"))
	            .contentLength(file.contentLength())
	            .body(file);
	    } catch (Exception e) {
	    	return (ResponseEntity<GridFsResource>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
}
