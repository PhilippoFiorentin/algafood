package com.philippo.algafood.domain.repository;

import java.util.List;

import com.philippo.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
	
//	List<Cozinha> consultarPorNome(String nome);
	
}
