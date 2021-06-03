package com.philippo.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.philippo.algafood.di.modelo.Cliente;
import com.philippo.algafood.di.notificacao.Notificador;

@Component
public class AtivacaoClienteService {
	
	@Qualifier("urgente")
	@Autowired
	private Notificador notificador;

	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");			
	}
}
