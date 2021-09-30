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
	private String photo;
	private String type;

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserModel(Long idUser, String name, String email, String password, String photo, String type) {
		this.idUser = idUser;
		this.name = name;
		this.email = email;
		this.password = password;
		this.photo = photo;
		this.type = type;
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
