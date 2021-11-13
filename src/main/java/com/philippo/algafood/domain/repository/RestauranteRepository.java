package com.philippo.algafood.domain.repository;

import com.philippo.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RestauranteRepository extends
    JpaRepository<Restaurante, Long>,
    RestauranteRepositoryQueries {

    List<Restaurante> findByTaxaFreteBetween(
        BigDecimal taxaInicial,
        BigDecimal taxaFinal);

//    @Query("from Restaurante where nome like %:nome% and  cozinha.id = :id")
    List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);

//    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);

    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

    List<Restaurante> findTop2RestauranteByNomeContaining(String nome);

    Integer countByCozinhaId(Long cozinha);
}

