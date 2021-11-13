package com.philippo.algafood.api.controller;

import com.philippo.algafood.domain.model.Cozinha;
import com.philippo.algafood.domain.model.Restaurante;
import com.philippo.algafood.domain.repository.CozinhaRepository;
import com.philippo.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> cozinhasPorNome(String nome){
        return cozinhaRepository.findAllByNomeContaining(nome);
     }

    @GetMapping("/cozinhas/unica-por-nome")
    public Optional<Cozinha> cozinhaPorNome(String nome){
        return cozinhaRepository.findByNome(nome);
    }

    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal){
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/por-nome")
    public List<Restaurante> restaurantesPorTaxaFrete(String nome, Long cozinhaId){
        return restauranteRepository.consultarPorNome(nome, cozinhaId);
    }

    @GetMapping("/restaurantes/primeiro-por-nome")
    public Optional<Restaurante> restaurantePrimeiroPorNome(String nome){
        return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/top-2-por-nome")
    public List<Restaurante> restaurantesTop2PorNome(String nome){
        return restauranteRepository.findTop2RestauranteByNomeContaining(nome);
    }

    @GetMapping("/cozinha-exists")
    public Boolean cozinhaExists(String nome){
        return cozinhaRepository.existsByNome(nome);
    }
    @GetMapping("/count-restaurantes-cozinha")
    public Integer restaurantesCountPorCozinha(Long cozinhaId){
        return restauranteRepository.countByCozinhaId(cozinhaId);
    }

}
