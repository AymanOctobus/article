package com.octobus.article.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.octobus.article.entity.UserProfileEntity;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfileEntity, String>{

	public UserProfileEntity findByEmailId(String emailId);
}
