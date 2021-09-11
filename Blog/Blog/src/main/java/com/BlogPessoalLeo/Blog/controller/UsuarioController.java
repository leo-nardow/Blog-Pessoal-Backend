package com.BlogPessoalLeo.Blog.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BlogPessoalLeo.Blog.model.UsuarioModel;
import com.BlogPessoalLeo.Blog.model.util.UsuarioDTO;
import com.BlogPessoalLeo.Blog.repository.UsuarioRepository;
import com.BlogPessoalLeo.Blog.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private @Autowired UsuarioRepository repository;
	private @Autowired UsuarioService service;
	
	//GET
	
	@GetMapping("/todos")
	public ResponseEntity<List<UsuarioModel>> pegarTodos() {
		
		List<UsuarioModel> objetoLista = repository.findAll();
		if(objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}
	
	@GetMapping("/todes")
	public ResponseEntity<List<UsuarioModel>> pegarTodes() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id_usuario}")
	public ResponseEntity<UsuarioModel> findById(@PathVariable(value = "id_usuario") Long idUsuario) {
		Optional<UsuarioModel> objetoUsuario = repository.findById(idUsuario);
		if(objetoUsuario.isPresent()) {
			return ResponseEntity.status(200).body(objetoUsuario.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}
	
	@GetMapping("/nome/{nome_usuario}")
	public ResponseEntity<List<UsuarioModel>> buscarPorNome(@PathVariable(value = "nome_usuario") String nome) {
		List<UsuarioModel> objetoNome = repository.findAllByNomeContainingIgnoreCase(nome);
		
		if(objetoNome.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoNome);			
		}
	}
	
	@GetMapping("/email")
	public ResponseEntity<UsuarioModel> buscarPorEmail(@RequestParam(value = "") String email) {
		return repository.findByEmail(email)
				.map(emailencontrado -> ResponseEntity.ok(emailencontrado))
				.orElse(ResponseEntity.notFound().build());	
	}
	
	
	/*@GetMapping("/email/{email}")
	public ResponseEntity<UsuarioModel> buscarPorEmail(@PathVariable(value = "email") String email) {
		return repository.findByEmail(email)
				.map(emailencontrado -> ResponseEntity.ok(emailencontrado))
				.orElse(ResponseEntity.notFound().build());	
	}*/
	
	//POST
	
	@PostMapping("/salvar")
	public ResponseEntity<Object> salvar(@Valid @RequestBody UsuarioModel novoUsuario) {
		Optional<Object> objetoOptional = service.cadastrarUsuario(novoUsuario);
		
		if (objetoOptional.isEmpty()) {
			return ResponseEntity.status(400).build();
		} else {
			return ResponseEntity.status(201).body(objetoOptional.get());
		}
	}
	
	@PostMapping("/criar")
	public ResponseEntity<UsuarioModel> cadastrarUsuario(@Valid @RequestBody UsuarioModel novoUsuario) {
		return ResponseEntity.status(201).body(repository.save(novoUsuario));
	}
	
	/*
	public ResponseEntity<UsuarioModel> cadastrarUsuario2(@Valid @RequestBody UsuarioModel novoUsuario) {
	 
		ResponseEntity<UsuarioModel>;
	}*/
	
	//PUT
	
	@PutMapping("/credenciais")
	public ResponseEntity<Object> credenciais(UsuarioDTO usuarioParaAutenticar, Long idUsuario) {
		Optional<?> objetoOptional = service.pegarCredenciais(usuarioParaAutenticar);
		
		if (objetoOptional.isEmpty()) {
			return ResponseEntity.status(400).build();
		} else {
			return ResponseEntity.status(201).body(objetoOptional.get());
		}
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<UsuarioModel> atualizarUsuario (@Valid @RequestBody UsuarioModel atualizadoUsuario) {
		return ResponseEntity.status(201).body(repository.save(atualizadoUsuario));
	}
	
	@PutMapping("/alterar")
	public ResponseEntity<Object> alterarUsuario(@Valid @RequestBody UsuarioDTO usuarioParaAlterar) {
		Optional<?> objetoAlterado = service.alterarUsuario(usuarioParaAlterar);
		
		if (objetoAlterado.isPresent()) {
			return ResponseEntity.status(201).body(objetoAlterado.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}
	
	//DELETE
	
	@DeleteMapping("/deletar/{id_usuario}")
	public void deletarUsuario(@PathVariable(value = "id_usuario") Long idUsuario) {
		repository.deleteById(idUsuario);
		 
	}
	
	
	

}
