package com.BlogPessoalLeo.Blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RequestMapping("/")
@RestController
@CrossOrigin("*")
public class BlogApplication {
	
	@Autowired 
	@RequestMapping 
	public String greeting()
	{
		return "Bem-vindo ao Blog Pessoal";
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
