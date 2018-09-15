package com.octobus.article.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.octobus.article.model.Article;
import com.octobus.article.model.Comments;
import com.octobus.article.model.Point;
import com.octobus.article.service.ArticleService;

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
	
	@PostMapping("site/article/like/{articleId}")
	public ResponseEntity<Object> getArticleById(@PathVariable("articleId") String articleId, @RequestBody Point point){
		Integer totalPoint = service.postLike(articleId,point);
		return new ResponseEntity<>("totalPoint :"+totalPoint,HttpStatus.OK);
	}
	
	@GetMapping("/site/article/comment/{articleId}")
	public ResponseEntity<List<Comments>> getCommentsById(@PathVariable("articleId") String articleId){
		List<Comments> comments =service.getCommentsByArticleId(articleId);
		return new ResponseEntity<>(comments,HttpStatus.OK);
	}
	
	@PostMapping("/site/article/comment/{articleId}")
	public ResponseEntity<Comments> getCommentsById(@PathVariable("articleId") String articleId,@RequestBody Comments comment){
		Comments postedComment = service.postComment(articleId,comment);
		return new ResponseEntity<>(postedComment,HttpStatus.OK);
	}
	
	
	@GetMapping("site/article/upload")
	public ResponseEntity<Article> updateArticle(@RequestBody Article arcticle){
		Article createdArcticle = service.createArticle(arcticle);
		return new ResponseEntity<>(createdArcticle,HttpStatus.CREATED);
	}
	
	
	
	
	
	
}
