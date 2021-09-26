package com.philippo.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.philippo.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.philippo.algafood.domain.model.Cidade;
import com.philippo.algafood.domain.model.Estado;
import com.philippo.algafood.domain.repository.CidadeRepository;
import com.philippo.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		
		Estado estado = estadoRepository.buscar(estadoId);
		
		if (estado == null) {
			throw new EntidadeNaoEncontradaException(String.format(
					"Cadastro de estado com o código %d não foi encontrado", estadoId
					)
			);
		
		}

		cidade.setEstado(estado);
		
		return cidadeRepository.salvar(cidade);
	}
}
