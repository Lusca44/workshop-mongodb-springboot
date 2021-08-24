package com.lusca44.workshopmongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lusca44.workshopmongo.domain.Post;
import com.lusca44.workshopmongo.repository.PostRepository;
import com.lusca44.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepo;
	
	public Post findById(String id) {
		Optional<Post> post = postRepo.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
	}
	
	public List<Post> findByTitle(String text){
		return postRepo.findByTitleContainingIgnoreCase(text);
	}
}
