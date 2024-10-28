package com.philippo.algafood.domain.exception;

public class RestaurantOrderNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public RestaurantOrderNotFoundException(String orderUuid) {
        super(String.format("The order register with code %s could not be found", orderUuid));
    }
}
