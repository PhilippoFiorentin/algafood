package com.philippo.algafood.domain.exception;

public class ProductNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long restaurantId, Long productId){
        this(String.format("There is no product register with code %d for the restaurant with code %d",
                restaurantId, productId));
    }
}
