package com.BlogPessoalLeo.Blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BlogPessoalLeo.Blog.model.ThemeModel;

@Repository
public interface ThemeRepository extends JpaRepository<ThemeModel, Long>{
	List<ThemeModel> findAllByThemeContainingIgnoreCase(String theme);
}
