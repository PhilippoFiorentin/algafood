package com.philippo.algafood.domain.exception;

public class PaymentMethodNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 1L;

    public PaymentMethodNotFoundException(String message) {
        super(message);
    }

    public PaymentMethodNotFoundException(Long paymentMethodId){
        this(String.format("There is no payment method register with code %d", paymentMethodId));
    }
}
