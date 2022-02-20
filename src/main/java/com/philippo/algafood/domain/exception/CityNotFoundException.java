package com.philippo.algafood.domain.exception;


public class CityNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public CityNotFoundException(String message) {
		super(message);
	}

	public CityNotFoundException(Long cityId){
		this(String.format("The City register with code %d could not be found", cityId));
	}
}
