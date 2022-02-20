package com.philippo.algafood.domain.exception;

public class RestaurantNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public RestaurantNotFoundException(String message) {
		super(message);
	}

	public RestaurantNotFoundException(Long restaurantId){
		this(String.format("The restaurant register with code %d could not be found", restaurantId));
	}
}
