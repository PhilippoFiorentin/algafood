package com.philippo.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class OrderItemInput {

    @NotNull
    private Long productId;

    @NotNull
    @Positive
    private Integer quantity;

    private String observation;
}
