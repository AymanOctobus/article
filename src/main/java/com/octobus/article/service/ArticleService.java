package com.octobus.article.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.octobus.article.entity.ArticleEntity;
import com.octobus.article.model.Article;
import com.octobus.article.model.Comments;
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
		entity.setTile(arcticle.getTile());
		entity.setTotalPoint(arcticle.getTotalPoint());
		entity.setVideo(arcticle.getVideo());
		entity.setWhenCreated(entity.getWhenCreated());
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
			article.setTile(entity.getTile());
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
}
