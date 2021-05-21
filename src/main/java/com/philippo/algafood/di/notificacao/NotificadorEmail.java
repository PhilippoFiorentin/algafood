package com.philippo.algafood.di.notificacao;

import org.springframework.stereotype.Component;

import com.philippo.algafood.di.modelo.Cliente;

@Component
public class NotificadorEmail {
	
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s através do e-mail %s: %s\n", cliente.getNome(), cliente.getEmail(), mensagem);
	}
}
