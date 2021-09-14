package com.BlogPessoalLeo.Blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BlogPessoalLeo.Blog.model.PostModel;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Long>{
	List<PostModel> findAllByTitleContainingIgnoreCase(String titlePost);
}