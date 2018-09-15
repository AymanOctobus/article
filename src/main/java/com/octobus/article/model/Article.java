package com.octobus.article.model;

import java.time.LocalDateTime;
import java.util.List;

public class Article {

	private String articleId;
	private String title;
	private String video;
	private String content_url;
	private String content_type;
	private int totalPoint;
	private String createBy;
	private String attitude;
	private LocalDateTime whenCreated;
	private List<Comments> comments;
	private List<Point> points;
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getContent_url() {
		return content_url;
	}
	public void setContent_url(String content_url) {
		this.content_url = content_url;
	}
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	public int getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getAttitude() {
		return attitude;
	}
	public void setAttitude(String attitude) {
		this.attitude = attitude;
	}
	public LocalDateTime getWhenCreated() {
		return whenCreated;
	}
	public void setWhenCreated(LocalDateTime whenCreated) {
		this.whenCreated = whenCreated;
	}
	public List<Comments> getComments() {
		return comments;
	}
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	public List<Point> getPoints() {
		return points;
	}
	public void setPoints(List<Point> points) {
		this.points = points;
	}
	
}
