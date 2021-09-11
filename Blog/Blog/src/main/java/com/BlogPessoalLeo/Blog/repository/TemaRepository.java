package com.BlogPessoalLeo.Blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BlogPessoalLeo.Blog.model.TemaModel;

@Repository
public interface TemaRepository extends JpaRepository<TemaModel, Long>{
	List<TemaModel> findAllByTemaContainingIgnoreCase(String tema);
}
