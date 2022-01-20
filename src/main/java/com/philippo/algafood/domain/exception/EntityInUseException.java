package com.philippo.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityInUseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntityInUseException (String mensagem) {
		super(mensagem);
	}
}
