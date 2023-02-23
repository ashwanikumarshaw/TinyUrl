package com.tiny.url.controller;

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
	public String tinyURL(@RequestParam String link) {

//		String hash=UUID.randomUUID().toString();
		String hash = "" + link.hashCode();
		URL url = new URL();
		url.setHash(hash);
		url.setUrl(link);
		repo.save(url);
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
