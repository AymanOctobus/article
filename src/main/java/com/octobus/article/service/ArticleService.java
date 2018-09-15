package com.octobus.article.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.octobus.article.entity.ArticleEntity;
import com.octobus.article.model.Article;
import com.octobus.article.model.Comments;
import com.octobus.article.model.Point;
import com.octobus.article.repository.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository repository;
	
	public Article createArticle(Article arcticle){
		ArticleEntity entity = new ArticleEntity();
		entity.setAttitude(arcticle.getAttitude());
		entity.setComments(arcticle.getComments());
		entity.setContent_type(arcticle.getContent_type());
		entity.setContent_url(arcticle.getContent_url());
		entity.setCreateBy(arcticle.getCreateBy());
		entity.setPoints(arcticle.getPoints());
		entity.setTile(arcticle.getTitle());
		entity.setTotalPoint(arcticle.getTotalPoint());
		entity.setVideo(arcticle.getVideo());
		entity.setWhenCreated(LocalDateTime.now());
		repository.save(entity);
		return arcticle;
	}
	
	public List<Article> getArticles(){
		List<ArticleEntity>  entities = repository.findAll();
		List<Article> articles=new ArrayList<Article>();
		for (ArticleEntity entity :entities) {
			Article article=new Article();
			article.setAttitude(entity.getAttitude());
			article.setComments(entity.getComments());
			article.setContent_type(entity.getContent_type());
			article.setContent_url(entity.getContent_url());
			article.setCreateBy(entity.getCreateBy());
			article.setPoints(entity.getPoints());
			article.setTitle(entity.getTitle());
			article.setTotalPoint(entity.getTotalPoint());
			article.setVideo(entity.getVideo());
			article.setWhenCreated(entity.getWhenCreated());
			articles.add(article);
		}
		return articles;
	}
	
	public Article getArticleById(String articleId) {
		Article article=new Article();
		return article;
	}
	
	public List<Comments> getCommentsById(String articleId) {
		List<Comments> comments=new ArrayList<Comments>();
		return comments;
	}
	
	public Article updateArticle(Article article) {
		return article;
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
}
