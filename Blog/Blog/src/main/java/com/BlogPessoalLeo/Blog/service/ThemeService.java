package com.BlogPessoalLeo.Blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogPessoalLeo.Blog.model.ThemeModel;
import com.BlogPessoalLeo.Blog.repository.ThemeRepository;

@Service
public class ThemeService {

	private @Autowired ThemeRepository repository;

	public Optional<ThemeModel> alterTheme(ThemeModel themeToAlter) {

		return repository.findById(themeToAlter.getIdTheme())
				.map(existingTheme -> {
					existingTheme.setTheme(themeToAlter.getTheme());
			
					return Optional.ofNullable(repository.save(existingTheme));
				}).orElseGet(() -> {

					return Optional.empty();
				});
	}

}
