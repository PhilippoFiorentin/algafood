package com.philippo.algafood.domain.exception;

public class RestaurantOrderNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public RestaurantOrderNotFoundException(String message) {
        super(message);
    }

    public RestaurantOrderNotFoundException(Long orderId) {
        this(String.format("The order register with code %d could not be found", orderId));
    }
}
