package com.philippo.algafood.domain.repository;

import java.util.List;

import com.philippo.algafood.domain.model.Estado;

public interface EstadoRepository {
	
	List<Estado> listar();
	
	Estado buscar(Long id);
	
	Estado salvar(Estado estado);
	
	void excluir(Long estadoId);
}
