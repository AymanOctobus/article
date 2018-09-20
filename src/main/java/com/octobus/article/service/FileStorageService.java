package com.octobus.article.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.octobus.article.config.FileStorageProperties;
import com.octobus.article.exception.FileStorageException;
import com.octobus.article.exception.ProfilePhotoNotFoundException;

@Service
public class FileStorageService {

    private final Path photoStorageLocation;
    private final Path videoStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.photoStorageLocation = Paths.get(fileStorageProperties.getPhotoUploadDir())
                .toAbsolutePath().normalize();
        this.videoStorageLocation = Paths.get(fileStorageProperties.getVideoUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.photoStorageLocation);
            Files.createDirectories(this.videoStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file, String userOrArticleId, boolean isPhoto) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path targetLocation = null;
        Path userDir = null;
        try {
            if(isPhoto){
            	userDir = Paths.get(photoStorageLocation.toString(),userOrArticleId);
            	Files.createDirectories(userDir);
            	targetLocation = userDir.resolve(fileName);
            }else{
            	userDir = Paths.get(videoStorageLocation.toString(),userOrArticleId);
            	Files.createDirectories(userDir);
                targetLocation = userDir.resolve(fileName);

            }
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName, String userOrArticleId, boolean isPhoto) {
        try {
        	Path userDir = null;
        	if(isPhoto){
        		userDir = Paths.get(photoStorageLocation.toString(),userOrArticleId);
        	}else{
        		userDir = Paths.get(videoStorageLocation.toString(),userOrArticleId);
        	}
            Path filePath = userDir.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new ProfilePhotoNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new ProfilePhotoNotFoundException("File not found " + fileName, ex);
        }
    }
}