package com.BlogPessoalLeo.Blog.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("com.BlogPessoalLeo.Blog.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metadata())
				.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, responseMessageForGET());
	}
	
	public static ApiInfo metadata() {
		return new ApiInfoBuilder()
				.title("API - Blog Pessoal Leo")
				.description("Projeto API Spring - Blog Pessoal Leo")
				.version("1.0.0")
				.license("MIT License")
				.licenseUrl("https://github.com/leo-nardow/Projeto-Blog-Pessoal/blob/main/LICENSE")
				.contact(contact())
				.build();
		
	}
	
	private static Contact contact() {
		return new Contact("Leonardo Almeida",
				"https://github.com/leo-nardow",
				"leonardoalmeida2703@gmail.com");
	}

	private static List<Response> responseMessageForGET() {
		return new ArrayList<Response>() {
			private static final long serialVersionUID = 1L;	
				{
				add(new ResponseBuilder().code("200").description("Sucesso!").build());
				add(new ResponseBuilder().code("201").description("Objeto Criado!").build());
				add(new ResponseBuilder().code("401").description("Não Autorizado!").build());
				add(new ResponseBuilder().code("403").description("Proibido!").build());
				add(new ResponseBuilder().code("404").description("Não Encontrado!").build());
				add(new ResponseBuilder().code("500").description("Erro!").build());
				}
		};
	}
}
