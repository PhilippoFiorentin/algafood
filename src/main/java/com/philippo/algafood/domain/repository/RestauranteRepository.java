package com.philippo.algafood.domain.repository;

import java.util.List;

import com.philippo.algafood.domain.model.Restaurante;

public interface RestauranteRepository {
	
	List<Restaurante> listar();
	
	Restaurante buscar(Long id);
	
	Restaurante adicionar(Restaurante cozinha);
	
	void remover(Restaurante cozinha);
}
