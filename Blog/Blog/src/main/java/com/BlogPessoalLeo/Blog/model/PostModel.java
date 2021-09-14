package com.BlogPessoalLeo.Blog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class PostModel {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idPost;
	private @NotBlank String title;
	private @NotBlank String description;

	@ManyToOne
	@JoinColumn(name = "id_creator")
	@JsonIgnoreProperties({ "myPosts" })
	private UserModel creator;

	@ManyToOne
	@JoinColumn(name = "id_theme")
	@JsonIgnoreProperties({ "posts" })
	private ThemeModel relatedTheme;

	public Long getIdPost() {
		return idPost;
	}

	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserModel getCreator() {
		return creator;
	}

	public void setCreator(UserModel creator) {
		this.creator = creator;
	}

	public ThemeModel getRelatedTheme() {
		return relatedTheme;
	}

	public void setRelatedTheme(ThemeModel relatedTheme) {
		this.relatedTheme = relatedTheme;
	}

}
