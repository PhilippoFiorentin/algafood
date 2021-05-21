package com.philippo.algafood.di.service;

import org.springframework.stereotype.Component;

import com.philippo.algafood.di.modelo.Cliente;
import com.philippo.algafood.di.notificacao.Notificador;

@Component
public class AtivacaoClienteService {
	
	private Notificador notificador;
	
	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
		
		System.out.println("AtivacaoClienteService: " + notificador);
	}



	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		this.notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}  
}
