package com.philippo.algafood.domain.exception;

public class StateNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public StateNotFoundException(String message) {
		super(message);
	}

	public StateNotFoundException(Long stateId){
		this(String.format("There is no state register with code %d", stateId));
	}
}
