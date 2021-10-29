package com.philippo.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.philippo.algafood.domain.exception.EntidadeEmUsoException;
import com.philippo.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.philippo.algafood.domain.model.Cozinha;
import com.philippo.algafood.domain.model.Restaurante;
import com.philippo.algafood.domain.repository.CozinhaRepository;
import com.philippo.algafood.domain.repository.RestauranteRepository;

import java.util.Optional;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();

		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
			.orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format(
				"Cadastro de restaurante com o código %d não foi encontrado", cozinhaId
			)));
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}
	
	public void excluir(Long restauranteId) {
		try {
			restauranteRepository.deleteById(restauranteId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(
					"Cadastro de restaurante com o código %d não foi encontrado", restauranteId
					)
			);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(
					"Restaurante de código %d não pode ser removido", restauranteId
					)
			);
		}
	}
}
