package com.BlogPessoalLeo.Blog.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class used as Entity in the Database for UserModel, it has attributes 
 * that will be columns in the database with title: name, email, and password.
 *
 * @author Leonardo Almeida
 * @version 1.0.0
 * 
 */

@Entity
public class UserModel {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idUser;
	private @NotBlank String name;
	private @NotBlank String email;
	private @NotBlank String password;

	public UserModel(Long idUser, String name, String email, String password) {
		this.idUser = idUser;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	@OneToMany(mappedBy = "creator", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "creator" })
	private List<PostModel> myPosts = new ArrayList<>();

	public List<PostModel> getMyPosts() {
		return myPosts;
	}

	public void setMyPosts(List<PostModel> myPosts) {
		this.myPosts = myPosts;
	}

	public UserModel() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
