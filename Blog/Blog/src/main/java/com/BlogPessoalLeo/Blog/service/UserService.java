package com.BlogPessoalLeo.Blog.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.BlogPessoalLeo.Blog.model.UserModel;
import com.BlogPessoalLeo.Blog.model.util.UserDTO;
import com.BlogPessoalLeo.Blog.repository.UserRepository;


@Service
public class UserService {
	private @Autowired UserRepository repository;
	
	/*
	 * Cadastrar usuario
	 */
	public Optional<Object> registerUser(UserModel newUser) {
		return repository.findByEmail(newUser.getEmail())
				.map(existingUser -> {
					return Optional.empty();})
				.orElseGet(() -> {
					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
					String result = encoder.encode(newUser.getPassword());
					newUser.setPassword(result);
					return Optional.ofNullable(repository.save(newUser));
				});
	}
	
	/*
	 * Login
	 */
	public Optional<?> login(UserDTO userForAuthentication) {
		return repository.findByEmail(userForAuthentication.getEmail())
				.map(existingUser -> {
					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
					if (encoder.matches(userForAuthentication.getPassword(), existingUser.getPassword())) {
						String basicStruct = userForAuthentication.getEmail() + ":" + userForAuthentication.getPassword();
						byte[] authorizationBase64 = Base64.encodeBase64(basicStruct.getBytes(Charset.forName("US-ASCII")));
						String authorizationHeader = "Basic " + new String(authorizationBase64);
						
						userForAuthentication.setToken(authorizationHeader);
						userForAuthentication.setIdUser(existingUser.getIdUser());
						userForAuthentication.setName(existingUser.getName());
						userForAuthentication.setPassword(existingUser.getPassword());
						userForAuthentication.setPhoto(existingUser.getPhoto());
						userForAuthentication.setType(existingUser.getType());
						
						return Optional.ofNullable(userForAuthentication);
					} else {
						return Optional.empty();
					}
				}).orElseGet(() -> {
					return Optional.empty(); //incorrect password
				});
	}
	
	public Optional<?> alterUser(UserDTO userToAlter) {
		return repository.findById(userToAlter.getIdUser())
				.map(existingUser -> {
					BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
					String encryptedPassword = encoder.encode(userToAlter.getPassword());
					
					existingUser.setName(userToAlter.getName());
					existingUser.setPassword(encryptedPassword);
					
					return Optional.ofNullable(repository.save(existingUser));
				}).orElseGet(() -> {
					return Optional.empty();
				});
	}
}
