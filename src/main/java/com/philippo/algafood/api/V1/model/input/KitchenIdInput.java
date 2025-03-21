package com.philippo.algafood.api.V1.model.input;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Setter
@Getter
public class KitchenIdInput {

    @NotNull
    private Long id;
}
