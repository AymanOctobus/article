package com.octobus.article.repository;

import com.octobus.article.entity.ArticleEntity;
import com.octobus.article.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<ArticleEntity, String>{

	
}
