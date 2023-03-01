package com.tiny.url.controller;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiny.url.model.URL;
import com.tiny.url.repository.UrlRepo;

@RestController
public class UrlController {
	@Autowired
	private UrlRepo repo;

	@GetMapping("/tinyUrl")
	public String tinyURL(@RequestParam String link){
		int h;
		int hk=(h =link.hashCode()) ^ (h >>> 16);
		String hash =""+ hk;
		URL old=repo.findByName(link);
		Optional<URL> url = repo.findById(hash);
		if (!url.isEmpty()) {
			if(!url.get().getUrl().equals(link)&&old==null)
				hash=UUID.randomUUID().toString();
			else
				return old.getHash();
			}
		URL uri = new URL();
		uri.setHash(hash);
		uri.setUrl(link);
		repo.save(uri);
		return hash;
	}

	@GetMapping("/longUrl")
	public String longUrl(@RequestParam String link) {
		String[] tinyUrl = link.split("/");
		Optional<URL> url = repo.findById(tinyUrl[tinyUrl.length - 1]);
		if (url.isEmpty())
			return "URL = " + link + " Is Not Correct";
		return url.get().getUrl();
	}
}
