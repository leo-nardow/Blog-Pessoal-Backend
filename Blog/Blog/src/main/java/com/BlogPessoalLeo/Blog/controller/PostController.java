package com.BlogPessoalLeo.Blog.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogPessoalLeo.Blog.model.PostModel;
import com.BlogPessoalLeo.Blog.repository.PostRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/post")

public class PostController {
	
	private @Autowired PostRepository repository;
	
	@GetMapping("/all")
	public ResponseEntity<List<PostModel>> getAll() {
		List<PostModel> objectList = repository.findAll();
		if(objectList.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(objectList);
		}
	}
	
	@GetMapping("/{idPost}")
	public ResponseEntity<PostModel> findById (@PathVariable(value = "idPost") Long idPost) {
		Optional<PostModel> objectPost = repository.findById(idPost);
		
		if(objectPost.isPresent()) {
			return ResponseEntity.status(200).body(objectPost.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}
	
	@GetMapping("/title/{titlePost}")
	public ResponseEntity<List<PostModel>> findAllByTitle(@PathVariable(value = "titlePost") String titlePost) {
		List<PostModel> objectPost = repository.findAllByTitleContainingIgnoreCase(titlePost);
		if(objectPost.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objectPost);
		}		
	}
	
	@PostMapping("/create")
	public ResponseEntity<PostModel> createPost(@Valid @RequestBody PostModel newPost) {
		return ResponseEntity.status(201).body(repository.save(newPost));
	}
	
	@PutMapping("/uptade")
	public ResponseEntity<PostModel> updatePost (@Valid @RequestBody PostModel updatedPost) {
		return ResponseEntity.status(201).body(repository.save(updatedPost));
	}
	
	@DeleteMapping("/delete/{idPost}")
	public void deletePost(@PathVariable(value = "idPost") Long idPost) {
		repository.deleteById(idPost);
	}
}
