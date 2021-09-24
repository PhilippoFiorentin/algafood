package com.philippo.algafood.jpa;

import java.math.BigDecimal;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.philippo.algafood.AlgafoodApiApplication;
import com.philippo.algafood.domain.model.Restaurante;
import com.philippo.algafood.domain.repository.RestauranteRepository;

public class InclusaoRestauranteMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNome("Trattoria");
		restaurante1.setTaxaFrete(new BigDecimal(4.99));
		
		Restaurante restaurante2 = new Restaurante();
		restaurante2.setNome("Viena");
		restaurante2.setTaxaFrete(new BigDecimal(7.49));
		
		restaurante1 = restauranteRepository.salvar(restaurante1);
		restaurante2 = restauranteRepository.salvar(restaurante2);
	}
}
