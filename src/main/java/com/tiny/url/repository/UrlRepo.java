package com.tiny.url.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tiny.url.model.URL;


public interface UrlRepo  extends MongoRepository<URL, String>{

	@Query("{'url' : ?0}")
	URL findByName(String link);

}
