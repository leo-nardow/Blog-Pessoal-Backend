package com.BlogPessoalLeo.Blog.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.BlogPessoalLeo.Blog.model.UsuarioModel;
import com.BlogPessoalLeo.Blog.model.util.UsuarioDTO;
import com.BlogPessoalLeo.Blog.repository.UsuarioRepository;

@Service
public class UsuarioService {
	private @Autowired UsuarioRepository repository;
	
	public Optional<Object> cadastrarUsuario(UsuarioModel novoUsuario) {
		return repository.findByEmail(novoUsuario.getEmail())
				.map(usuarioexistente -> {
					return Optional.empty();})
				.orElseGet(() -> {
					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
					String result = encoder.encode(novoUsuario.getSenha());
					novoUsuario.setSenha(result);
					return Optional.ofNullable(repository.save(novoUsuario));
				});
	}
	
	public Optional<?> pegarCredenciais(UsuarioDTO usuarioParaAutenticar) {
		return repository.findByEmail(usuarioParaAutenticar.getEmail())
				.map(usuarioexistente -> {
					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
					
					if (encoder.matches(usuarioParaAutenticar.getSenha(), usuarioexistente.getSenha())) {
						
						String estruturaBasic = usuarioParaAutenticar.getEmail() + ":" + usuarioParaAutenticar.getSenha();
						byte[] autorizacaoBase64 = Base64.encodeBase64(estruturaBasic.getBytes(Charset.forName("US-ASCII")));
						String autorizacaoHeader = "Basic " + new String(autorizacaoBase64);
						
						usuarioParaAutenticar.setToken(autorizacaoHeader);
						usuarioParaAutenticar.setId(usuarioexistente.getIdUsuario());
						usuarioParaAutenticar.setNome(usuarioexistente.getNome());
						usuarioParaAutenticar.setSenha(usuarioexistente.getSenha());
						
						return Optional.ofNullable(usuarioParaAutenticar);
					} else {
						return Optional.empty();
					}
				}).orElseGet(() -> {
					return Optional.empty(); //senha incorreta
				});
	}
	
	public Optional<?> alterarUsuario(UsuarioDTO usuarioParaAlterar) {
		return repository.findById(usuarioParaAlterar.getId())
				.map(usuarioexistente -> {
					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
					String senhaCriptografada = encoder.encode(usuarioParaAlterar.getSenha());
					
					usuarioexistente.setNome(usuarioParaAlterar.getNome());
					usuarioexistente.setSenha(senhaCriptografada);
					
					return Optional.ofNullable(repository.save(usuarioexistente));
				}).orElseGet(() -> {
					return Optional.empty();
				});
	}
}
