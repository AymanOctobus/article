package com.octobus.article.model;

import java.time.LocalDateTime;

public class ArticleUploadResponse {

	private String articleId;
	private LocalDateTime whenPosted;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public LocalDateTime getWhenPosted() {
		return whenPosted;
	}

	public void setWhenPosted(LocalDateTime whenPosted) {
		this.whenPosted = whenPosted;
	}
}
