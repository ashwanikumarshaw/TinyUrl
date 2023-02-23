package com.tiny.url.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tiny.url.model.URL;


public interface UrlRepo  extends MongoRepository<URL, String>{

}
