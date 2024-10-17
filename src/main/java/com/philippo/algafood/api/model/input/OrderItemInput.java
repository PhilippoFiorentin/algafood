package com.philippo.algafood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class OrderItemInput {

    @NotNull
    private Long id;

    @NotNull
    @PositiveOrZero
    private Integer quantity;

    private String observation;
}
