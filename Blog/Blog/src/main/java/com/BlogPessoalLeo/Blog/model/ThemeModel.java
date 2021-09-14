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

@Entity
public class ThemeModel {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idTheme;
	private @NotBlank String theme;
	@OneToMany(mappedBy = "relatedTheme", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({ "relatedTheme" })
	private List<PostModel> posts = new ArrayList<>();

	public Long getIdTheme() {
		return idTheme;
	}

	public void setIdTheme(Long idTheme) {
		this.idTheme = idTheme;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public List<PostModel> getPosts() {
		return posts;
	}

	public void setPosts(List<PostModel> posts) {
		this.posts = posts;
	}

}
