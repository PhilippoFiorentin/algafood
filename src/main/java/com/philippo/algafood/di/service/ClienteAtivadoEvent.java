package com.philippo.algafood.di.service;

import com.philippo.algafood.di.modelo.Cliente;

public class ClienteAtivadoEvent {
	
	private Cliente cliente;

	public ClienteAtivadoEvent(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
}
