package com.philippo.algafood.domain.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BusinessException(String reason) {
		super(reason);
	}

	public BusinessException(String message, Throwable reason){
		super(message, reason);
	}
}
