package com.BlogPessoalLeo.Blog.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.BlogPessoalLeo.Blog.model.ThemeModel;
import com.BlogPessoalLeo.Blog.repository.ThemeRepository;
import com.BlogPessoalLeo.Blog.service.ThemeService;

@RestController
@RequestMapping("/theme")
public class ThemeController {
	private @Autowired ThemeRepository repository;
	private @Autowired ThemeService service;
	
	@GetMapping("/all")
	public ResponseEntity<List<ThemeModel>> getAll(){
		List<ThemeModel> objectList = repository.findAll();
		if(objectList.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objectList);
		}
	}
	
	@GetMapping("/id/{id_theme}")
	public ResponseEntity<ThemeModel> getById(@PathVariable(value = "id_theme") Long idThema) {
		Optional<ThemeModel> objectTheme = repository.findById(idThema);
		
		if(objectTheme.isPresent()) {
			return ResponseEntity.status(200).body(objectTheme.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}
	
	@GetMapping("/{theme}")
	public ResponseEntity<List<ThemeModel>> findByTheme(@PathVariable(value = "theme") String theme){
		List<ThemeModel> objectList = repository.findAllByThemeContainingIgnoreCase(theme);
		
		if (objectList.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objectList);
		}
	}

	@GetMapping("/search")
	public ResponseEntity<List<ThemeModel>> findByThemeII(@RequestParam(defaultValue = "") String theme){
		List<ThemeModel> objectList = repository.findAllByThemeContainingIgnoreCase(theme);
		
		if (objectList.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objectList);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<ThemeModel> createTheme(@Valid @RequestBody ThemeModel newTheme) {
		return ResponseEntity.status(201).body(repository.save(newTheme));
	}
	
	@PutMapping("/alter")
	public ResponseEntity<ThemeModel> alterTheme(@Valid @RequestBody ThemeModel themeToAlter) {
		Optional<ThemeModel> objectChanged = service.alterTheme(themeToAlter);
		
		if (objectChanged.isPresent()) {
			return ResponseEntity.status(201).body(objectChanged.get());
		} else {
			return ResponseEntity.status(400).build();
		}

	}
	
	@DeleteMapping("/delete/{id_theme}")
	public void deletetheme(@PathVariable(value = "id_theme") Long idTheme) {
		repository.deleteById(idTheme);
	}

}
