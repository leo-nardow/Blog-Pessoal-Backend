package com.BlogPessoalLeo.Blog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class TemaModel {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idTema;
	private @NotBlank String tema;
	
	public Long getIdTema() {
		return idTema;
	}
	public void setIdTema(Long idTema) {
		this.idTema = idTema;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	
	
}
