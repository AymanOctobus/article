package com.octobus.article.endpoint;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.octobus.article.exception.UserAlreadyExistException;
import com.octobus.article.exception.UserNotFoundException;
import com.octobus.article.model.UploadFileResponse;
import com.octobus.article.model.UserProfile;
import com.octobus.article.service.FileStorageService;
import com.octobus.article.service.UserProfileService;

@RestController
@CrossOrigin("*")
public class UserProfileController {

	@Autowired
	private UserProfileService service;

	@Autowired
    private FileStorageService fileStorageService;
	
	@PostMapping("user/profile")
	public ResponseEntity<Object> createProfile(@RequestBody UserProfile userProfile) {
		try{
			UserProfile createdUserProfile = service.createUserProfile(userProfile);
			return new ResponseEntity<>(createdUserProfile, HttpStatus.CREATED);
		}catch(UserAlreadyExistException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}	
	}

	@GetMapping("user/profile/{emailId}")
	public ResponseEntity<Object> getUserProfile(@PathVariable("emailId") String emailId) {
		try{
			UserProfile userProfile = service.getUserProfileByEmailId(emailId);
			return new ResponseEntity<>(userProfile, HttpStatus.OK);
		}catch(UserNotFoundException e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}

	@PostMapping(value="user/profile/photo/{userId}")
	public ResponseEntity<UploadFileResponse> uploadProfilePhoto(@RequestParam("file") MultipartFile file,@PathVariable("userId") String userId){
		String fileName = fileStorageService.storeFile(file,userId,true);
		String profilePhotoUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/user/profile/photo/"+userId+"/")
                .path(fileName)
                .toUriString();
		service.updateUserProfile(userId,profilePhotoUri);
        return new ResponseEntity<>(new UploadFileResponse(fileName, profilePhotoUri,
                file.getContentType(), file.getSize()), HttpStatus.OK);
	}
	
	@GetMapping(value="user/profile/photo/{userId}/{fileName}")
	public ResponseEntity<Resource> downloadProfilePhoto(@PathVariable("userId") String userId, @PathVariable("fileName") String fileName, HttpServletRequest request) {
        Resource resource = fileStorageService.loadFileAsResource(fileName,userId,true);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        }
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
