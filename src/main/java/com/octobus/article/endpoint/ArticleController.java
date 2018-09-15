package com.octobus.article.endpoint;

import java.util.List;

import com.octobus.article.entity.ArticleEntity;
import com.octobus.article.model.Article;
import com.octobus.article.model.Comments;
import com.octobus.article.model.Points;
import com.octobus.article.model.UserProfile;
import com.octobus.article.service.ArticleService;
import com.octobus.article.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

	@Autowired
	private ArticleService service;
	
	@PostMapping("site/article")
	public ResponseEntity<Article> createArticle(@RequestBody Article arcticle){
		Article createdArcticle = service.createArticle(arcticle);
		return new ResponseEntity<>(createdArcticle,HttpStatus.CREATED);
	}
	
	@GetMapping("site/articles")
	public ResponseEntity<List<Article>> getArticles(){
		List<Article> arcticles =service.getArticles();
		return new ResponseEntity<>(arcticles,HttpStatus.OK);
	}
	
	@GetMapping("site/article/like/{articleId}")
	public ResponseEntity<Article> getArticleById(@PathVariable("articleId") String articleId){
		Article arcticle =service.getArticleById(articleId);
		return new ResponseEntity<>(arcticle,HttpStatus.OK);
	}
	
	@GetMapping("/site/article/comment/{articleId}")
	public ResponseEntity<List<Comments>> getCommentsById(@PathVariable("articleId") String articleId){
		List<Comments> comments =service.getCommentsById(articleId);
		return new ResponseEntity<>(comments,HttpStatus.OK);
	}
	
	@GetMapping("site/article/upload")
	public ResponseEntity<Article> updateArticle(@RequestBody Article arcticle){
		Article createdArcticle = service.createArticle(arcticle);
		return new ResponseEntity<>(createdArcticle,HttpStatus.CREATED);
	}
	
	
	
	
	
	
}
