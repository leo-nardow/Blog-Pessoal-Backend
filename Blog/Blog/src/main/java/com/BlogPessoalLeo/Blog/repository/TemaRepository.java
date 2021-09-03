package com.BlogPessoalLeo.Blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BlogPessoalLeo.Blog.model.TemaModel;

@Repository
public interface TemaRepository extends JpaRepository<TemaModel, Long>{

}
