package com.philippo.algafood.domain.repository;

import java.util.List;

import com.philippo.algafood.domain.model.Restaurante;

public interface RestauranteRepository {
	
	List<Restaurante> todos();
	
	Restaurante porId(Long id);
	
	Restaurante adicionar(Restaurante cozinha);
	
	void remover(Restaurante cozinha);
}
