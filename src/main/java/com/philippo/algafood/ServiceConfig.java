package com.philippo.algafood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.philippo.algafood.di.notificacao.Notificador;
import com.philippo.algafood.di.service.AtivacaoClienteService;

@Configuration
public class ServiceConfig {
	
	@Bean
	public AtivacaoClienteService ativacaoClienteService(Notificador notificador) {
		return new AtivacaoClienteService(notificador);
	}
}
