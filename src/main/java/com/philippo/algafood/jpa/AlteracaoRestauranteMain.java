package com.philippo.algafood.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.philippo.algafood.AlgafoodApiApplication;
import com.philippo.algafood.domain.model.Restaurante;
import com.philippo.algafood.domain.repository.RestauranteRepository;

public class AlteracaoRestauranteMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante restaurante = new Restaurante();
		restaurante.setId(1L);
		restaurante.setNome("Vivenda do Camarão");
		restaurante.setTaxaFrete(new BigDecimal(11.30));
		
		restaurante = restauranteRepository.adicionar(restaurante);
	}
}
