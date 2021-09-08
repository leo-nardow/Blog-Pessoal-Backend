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

import com.BlogPessoalLeo.Blog.model.PostagemModel;
import com.BlogPessoalLeo.Blog.repository.PostagemRepository;

@RestController
@RequestMapping("/postagem")
@CrossOrigin("*")
public class PostagemController {
	
	private @Autowired PostagemRepository repository;
	
	@GetMapping("/todaspostagens")
	public ResponseEntity<List<PostagemModel>> getAll() {
		List<PostagemModel> objetoLista = repository.findAll();
		if(objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}
	
	/*
	 * public ResponseEntity<List<PostagemModel>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}*/
	
	@GetMapping("/{idPostagem}")
	public ResponseEntity<PostagemModel> findById (@PathVariable(value = "idPostagem") Long idPostagem) {
		Optional<PostagemModel> objetoPostagem = repository.findById(idPostagem);
		
		if(objetoPostagem.isPresent()) {
			return ResponseEntity.status(200).body(objetoPostagem.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}
	
	@GetMapping("/titulo/{tituloPostagem}")
	public ResponseEntity<List<PostagemModel>> findAllByTitulo(@PathVariable(value = "tituloPostagem") String tituloPostagem) {
		List<PostagemModel> objetoPostagem = repository.findAllByTituloContainingIgnoreCase(tituloPostagem);
		if(objetoPostagem.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoPostagem);
		}		
	}
	
	@PostMapping("/criar")
	public ResponseEntity<PostagemModel> criarPostagem(@Valid @RequestBody PostagemModel novaPostagem) {
		return ResponseEntity.status(201).body(repository.save(novaPostagem));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<PostagemModel> atualizarPostagem (@Valid @RequestBody PostagemModel atualizadoPostagem) {
		return ResponseEntity.status(201).body(repository.save(atualizadoPostagem));
	}
	
	@DeleteMapping("/deletar/{idPostagem}")
	public void deletarPostagem(@PathVariable(value = "idPostagem") Long idPostagem) {
		repository.deleteById(idPostagem);
	}
}
