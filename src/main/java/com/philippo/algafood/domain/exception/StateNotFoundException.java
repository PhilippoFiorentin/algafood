package com.philippo.algafood.domain.exception;

public class StateNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public StateNotFoundException(String message) {
		super(message);
	}

	public StateNotFoundException(Long stateId){
		this(String.format("The state register with code %d could not be found", stateId));
	}
}
