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

import com.BlogPessoalLeo.Blog.model.UserModel;
import com.BlogPessoalLeo.Blog.model.util.UserDTO;
import com.BlogPessoalLeo.Blog.repository.UserRepository;
import com.BlogPessoalLeo.Blog.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")

public class UserController {
	
	private @Autowired UserRepository repository;
	private @Autowired UserService service;
	
	//GET
	@GetMapping("/all")
	public ResponseEntity<List<UserModel>> getAll() {
		
		List<UserModel> objectList = repository.findAll();
		if(objectList.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(objectList);
		}
	}
	
	@GetMapping("/{id_user}")
	public ResponseEntity<UserModel> getById(@PathVariable(value = "id_user") Long idUser) {
		Optional<UserModel> objectUser = repository.findById(idUser);
		if(objectUser.isPresent()) {
			return ResponseEntity.status(200).body(objectUser.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}
	
	@GetMapping("/name/{name_user}")
	public ResponseEntity<List<UserModel>> getByName(@PathVariable(value = "name_user") String name) {
		List<UserModel> objectName = repository.findAllByNameContainingIgnoreCase(name);
		
		if(objectName.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objectName);			
		}
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UserModel> getByEmail(@PathVariable(value = "email") String email) {
		return repository.findByEmail(email)
				.map(emailFound -> ResponseEntity.ok(emailFound))
				.orElse(ResponseEntity.notFound().build());	
	}
	
	//POST
	
	@PostMapping("/register")
	public ResponseEntity<Object> signUp(@Valid @RequestBody UserModel newUser) {
		Optional<Object> objectOptional = service.registerUser(newUser);
		
		if (objectOptional.isEmpty()) {
			return ResponseEntity.status(400).build();
		} else {
			return ResponseEntity.status(201).body(objectOptional.get());
		}
	}
	
	//PUT
	
	@PutMapping("/login")
	public ResponseEntity<Object> login(@Valid @RequestBody UserDTO userForAuthentication) {
		Optional<?> objectOptional = service.login(userForAuthentication);
		
		if (objectOptional.isEmpty()) {
			return ResponseEntity.status(400).build();
		} else {
			return ResponseEntity.status(201).body(objectOptional.get());
		}
	}
	
	@PutMapping("/alter")
	public ResponseEntity<Object> alter(@Valid @RequestBody UserDTO userToAlter) {
		Optional<?> objectChanged = service.alterUser(userToAlter);
		
		if (objectChanged.isPresent()) {
			return ResponseEntity.status(201).body(objectChanged.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}
	
	//DELETE
	
	@DeleteMapping("/delete/{id_user}")
	public void deleteUser(@PathVariable(value = "id_user") Long idUser) {
		repository.deleteById(idUser);
		 
	}

}
