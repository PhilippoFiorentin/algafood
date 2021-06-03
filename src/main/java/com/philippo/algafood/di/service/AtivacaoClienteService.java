package com.philippo.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.philippo.algafood.di.modelo.Cliente;
import com.philippo.algafood.di.notificacao.NivelUrgencia;
import com.philippo.algafood.di.notificacao.Notificador;
import com.philippo.algafood.di.notificacao.TipoDoNotificador;

@Component
public class AtivacaoClienteService {

	@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
	@Autowired
	private Notificador notificador;

	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");			
	}
}
