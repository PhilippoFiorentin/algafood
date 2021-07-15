package com.philippo.algafood.domain.repository;

import java.util.List;

import com.philippo.algafood.domain.model.Estado;

public interface EstadoRepository {
	
	List<Estado> listar();
	
	Estado buscar(Long id);
	
	Estado adicionar(Estado estado);
	
	void remover(Estado estado);
}
