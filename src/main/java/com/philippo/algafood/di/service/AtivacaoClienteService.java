package com.philippo.algafood.di.service;

import org.springframework.stereotype.Component;

import com.philippo.algafood.di.modelo.Cliente;
import com.philippo.algafood.di.notificacao.NotificadorEmail;

@Component
public class AtivacaoClienteService {
	
	private NotificadorEmail notificador;
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		this.notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}  
}
