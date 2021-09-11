package com.BlogPessoalLeo.Blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogPessoalLeo.Blog.model.TemaModel;
import com.BlogPessoalLeo.Blog.repository.TemaRepository;

@Service
public class TemaService {

	private @Autowired TemaRepository repository;

	public Optional<TemaModel> atualizarTema(TemaModel temaParaAltera) {

		return repository.findById(temaParaAltera.getIdTema())
				.map(temaexistente -> {
					temaexistente.setTema(temaParaAltera.getTema());
			
					return Optional.ofNullable(repository.save(temaexistente));
				}).orElseGet(() -> {

					return Optional.empty();
				});
	}

}
