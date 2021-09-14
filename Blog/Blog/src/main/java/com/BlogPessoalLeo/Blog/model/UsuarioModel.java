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
public class UsuarioModel {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idUsuario;
	private @NotBlank String usuario;
	private @NotBlank String nome;
	private @NotBlank String email;
	private @NotBlank String senha;
	
	public UsuarioModel(Long idUsuario,String usuario, String nome, String email,
            String senha) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
	
	@OneToMany(mappedBy = "criador", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"criador"})
	private List<PostagemModel> minhasPostagens = new ArrayList<>();
	
	public List<PostagemModel> getMinhasPostagens() {
		return minhasPostagens;
	}

	public void setMinhasPostagens(List<PostagemModel> minhasPostagens) {
		this.minhasPostagens = minhasPostagens;
	}

    public UsuarioModel() {
    }
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
