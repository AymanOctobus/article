package com.octobus.article.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.octobus.article.entity.ArticleEntity;
import com.octobus.article.entity.UserProfileEntity;
import com.octobus.article.model.Article;
import com.octobus.article.model.ArticleUploadRequest;
import com.octobus.article.model.ArticleUploadResponse;
import com.octobus.article.model.Comments;
import com.octobus.article.model.Point;
import com.octobus.article.repository.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository repository;
	
	public Article createArticle(Article article){
		ArticleEntity entity = new ArticleEntity();
		entity.setAttitude(article.getAttitude());
		entity.setComments(article.getComments());
		entity.setContentType(article.getContentType());
		entity.setContentUrl(article.getContentUrl());
		entity.setCreatedBy(article.getCreatedBy());
		entity.setPoints(article.getPoints());
		entity.setTile(article.getTitle());
		entity.setTotalPoint(article.getTotalPoint());
		entity.setVideo(article.getVideo());
		entity.setWhenCreated(LocalDateTime.now());
		repository.save(entity);
		article.setArticleId(entity.getArticleId());
		article.setWhenCreated(entity.getWhenCreated());
		return article;
	}
	
	public List<Article> getArticles(){
		List<ArticleEntity>  entities = repository.findAll();
		List<Article> articles=new ArrayList<Article>();
		for (ArticleEntity entity :entities) {
			Article article=new Article();
			article.setArticleId(entity.getArticleId());
			article.setAttitude(entity.getAttitude());
			article.setComments(entity.getComments());
			article.setContentType(entity.getContentType());
			article.setContentUrl(entity.getContentUrl());
			article.setCreatedBy(entity.getCreatedBy());
			article.setPoints(entity.getPoints());
			article.setTitle(entity.getTitle());
			article.setTotalPoint(entity.getTotalPoint());
			article.setVideo(entity.getVideo());
			article.setWhenCreated(entity.getWhenCreated());
			articles.add(article);
		}
		return articles;
	}
	
	public List<Comments> getCommentsByArticleId(String articleId) {
		Optional<ArticleEntity> optional = repository.findById(articleId);
		if(optional.isPresent()){
			ArticleEntity entity = optional.get();
			return entity.getComments();
		}else{
			//throw Article Not Found Exception
		}
		return null;
	}
	
	public Comments postComment(String articleId, Comments comment) {
		Optional<ArticleEntity> optional = repository.findById(articleId);
		if(optional.isPresent()){
			ArticleEntity entity = optional.get();
			List<Comments> comments = entity.getComments();
			if(null == comments){
				comments = new ArrayList<>();
			}
			comment.setCommentedDate(LocalDateTime.now());
			comments.add(comment);
			entity.setComments(comments);
			repository.save(entity);
			return comment;
		}else{
			//throw Article not found exception
		}
		return null;
	}

	public Integer postLike(String articleId,Point point) {
		Optional<ArticleEntity> optional = repository.findById(articleId);
		int totalPoint = 0;
		if(optional.isPresent()){
			ArticleEntity entity = optional.get();
			totalPoint = entity.getTotalPoint() + point.getPoint();
			List<Point> points = entity.getPoints();
			if(null == points){
				points = new ArrayList<>();
			}
			point.setPointGivenOn(LocalDateTime.now());
			points.add(point);
			entity.setPoints(points);
			entity.setTotalPoint(totalPoint);
			repository.save(entity);
		}else{
			//throw Article not found exception
		}
		return totalPoint;
	}
	
	public ArticleUploadResponse uploadArticle(ArticleUploadRequest articleUploadRequest) {
		ArticleEntity entity = new ArticleEntity();
		entity.setContentUrl(articleUploadRequest.getContent_url());
		entity.setCreatedBy(articleUploadRequest.getUserName());
		entity.setTile(articleUploadRequest.getTitle());
		entity.setVideo(articleUploadRequest.getVideo());
		entity.setWhenCreated(LocalDateTime.now());
		repository.save(entity);
		
		ArticleUploadResponse articleUploadResponse=new ArticleUploadResponse();
		articleUploadResponse.setArticleId(entity.getArticleId());
		articleUploadResponse.setWhenPosted(entity.getWhenCreated());
		return articleUploadResponse;
	}

	public void updateArticleVideo(String articleId,String videoUri) {
		Optional<ArticleEntity>  optional = repository.findById(articleId);
		if(optional.isPresent()){
			ArticleEntity entity = optional.get();
			entity.setVideo(videoUri);
			repository.save(entity);
		}	
	}
	
	
}
