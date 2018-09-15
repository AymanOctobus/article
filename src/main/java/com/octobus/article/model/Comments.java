package com.octobus.article.model;

import java.time.LocalDateTime;

public class Comments {

	private String commentedBy;
	private String comment;
	private String profilePhoto;
	private LocalDateTime commentedDate;
	
	public String getCommentedBy() {
		return commentedBy;
	}
	public void setCommentedBy(String commentedBy) {
		this.commentedBy = commentedBy;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getProfilePhoto() {
		return profilePhoto;
	}
	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	public LocalDateTime getCommentedDate() {
		return commentedDate;
	}
	public void setCommentedDate(LocalDateTime commentedDate) {
		this.commentedDate = commentedDate;
	}
}
