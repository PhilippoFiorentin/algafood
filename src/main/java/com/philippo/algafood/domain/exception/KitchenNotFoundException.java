package com.philippo.algafood.domain.exception;


public class KitchenNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public KitchenNotFoundException(String message) {
		super(message);
	}

	public KitchenNotFoundException(Long kitchenId){
		this(String.format("The kitchen register with code %d could not be found", kitchenId));
	}
}
