package com.BlogPessoalLeo.Blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BlogPessoalLeo.Blog.model.PostagemModel;

@Repository
public interface PostagemRepository extends JpaRepository<PostagemModel, Long>{
	List<PostagemModel> findAllByTituloContainingIgnoreCase(String tituloPostagem);
}