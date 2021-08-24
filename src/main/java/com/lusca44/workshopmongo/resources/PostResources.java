package com.lusca44.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lusca44.workshopmongo.domain.Post;
import com.lusca44.workshopmongo.resources.util.URL;
import com.lusca44.workshopmongo.service.PostService;


@RestController
@RequestMapping(value = "/posts")
public class PostResources {

	@Autowired
	private PostService service;

	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post post = service.findById(id);
		return ResponseEntity.ok().body(post);
	}

	@RequestMapping(value= "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
}
