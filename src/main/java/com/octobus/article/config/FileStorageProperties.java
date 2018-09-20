package com.octobus.article.config;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String videoUploadDir;
    private String photoUploadDir;
	public String getVideoUploadDir() {
		return videoUploadDir;
	}
	public void setVideoUploadDir(String videoUploadDir) {
		this.videoUploadDir = videoUploadDir;
	}
	public String getPhotoUploadDir() {
		return photoUploadDir;
	}
	public void setPhotoUploadDir(String photoUploadDir) {
		this.photoUploadDir = photoUploadDir;
	}
}