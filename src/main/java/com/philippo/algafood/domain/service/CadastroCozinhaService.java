package com.philippo.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.philippo.algafood.domain.exception.EntidadeEmUsoException;
import com.philippo.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.philippo.algafood.domain.model.Cozinha;
import com.philippo.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(
							"Não existe cadastro de cozinha com o código %d", cozinhaId
							));
		} catch (DataIntegrityViolationException e) {
			 throw new EntidadeEmUsoException(
					 String.format(
							 "Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId
							 ));
		}
	}
}
