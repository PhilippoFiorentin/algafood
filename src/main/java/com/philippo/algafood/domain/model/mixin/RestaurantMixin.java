package com.philippo.algafood.domain.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.philippo.algafood.domain.model.Address;
import com.philippo.algafood.domain.model.Kitchen;
import com.philippo.algafood.domain.model.PaymentMethod;
import com.philippo.algafood.domain.model.Product;

import java.time.OffsetDateTime;
import java.util.List;

public abstract class RestaurantMixin {

    @JsonIgnoreProperties(value = "name", allowGetters = true)
    private Kitchen kitchen;

    @JsonIgnore
    private OffsetDateTime registerDate;

    @JsonIgnore
    private OffsetDateTime updateDate;

    @JsonIgnore
    private Address address;

    @JsonIgnore
    private List<PaymentMethod> paymentMethods;

    @JsonIgnore
    private List<Product> products;
}
