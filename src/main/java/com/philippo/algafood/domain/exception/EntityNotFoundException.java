package com.philippo.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends ResponseStatusException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException (HttpStatus status, String reason) {
		super(status, reason);
	}

	public EntityNotFoundException (String mensagem) {
		this(HttpStatus.NOT_FOUND,mensagem);
	}
}
