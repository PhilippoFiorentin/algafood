package com.philippo.algafood.api.V1.model.input;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class RestaurantOrderInput {

    @Valid
    @NotNull
    private RestaurantIdInput restaurant;

    @Valid
    @NotNull
    private AddressInput deliveryAddress;

    @Valid
    @NotNull
    private PaymentMethodIdInput paymentMethod;

    @Valid
    @NotNull
    @Size(min = 1)
    List<OrderItemInput> items;
}
