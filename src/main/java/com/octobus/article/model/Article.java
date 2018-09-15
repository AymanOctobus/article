package com.octobus.article.model;

public class Article {

	private String articleId;
	private String tile;
	private String video;
	private String content_url;
	private String content_type;
	private String totalPoint;
	private String createBy;
	private String attitude;
	private String whenCreated;
	private Comments[] comments;
	private Points [] points;
	
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getTile() {
		return tile;
	}
	public void setTile(String tile) {
		this.tile = tile;
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
	public String getTotalPoint() {
		return totalPoint;
	}
	public void setTotalPoint(String totalPoint) {
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
	public String getWhenCreated() {
		return whenCreated;
	}
	public void setWhenCreated(String whenCreated) {
		this.whenCreated = whenCreated;
	}
	public Comments[] getComments() {
		return comments;
	}
	public void setComments(Comments[] comments) {
		this.comments = comments;
	}
	public Points[] getPoints() {
		return points;
	}
	public void setPoints(Points[] points) {
		this.points = points;
	}
	
	
	
	
	
	
}
