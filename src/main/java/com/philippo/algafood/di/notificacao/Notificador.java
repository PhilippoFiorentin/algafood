package com.philippo.algafood.di.notificacao;

import com.philippo.algafood.di.modelo.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);

}