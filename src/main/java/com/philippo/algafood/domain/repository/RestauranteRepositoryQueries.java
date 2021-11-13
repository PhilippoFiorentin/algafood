package com.philippo.algafood.domain.repository;

import com.philippo.algafood.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries
{
    List<Restaurante> find (
        String nome,
        BigDecimal taxaFreteInicial,
        BigDecimal taxaFreteFinal );
}
