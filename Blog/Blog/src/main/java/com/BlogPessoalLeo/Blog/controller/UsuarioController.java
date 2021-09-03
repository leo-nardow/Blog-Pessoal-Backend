package com.BlogPessoalLeo.Blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogPessoalLeo.Blog.model.UsuarioModel;
import com.BlogPessoalLeo.Blog.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private @Autowired UsuarioRepository repository;
	
	@GetMapping("/todes")
	public ResponseEntity<List<UsuarioModel>> pegarTodes() {
		List<UsuarioModel> objetoLista = repository.findAll();
		if(objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

}
