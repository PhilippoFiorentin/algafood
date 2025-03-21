package com.philippo.algafood.api.V1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Getter
@Setter
public class OrderItemInput {

    @Schema(example = "1")
    @NotNull
    private Long productId;

    @Schema(example = "1")
    @NotNull
    @Positive
    private Integer quantity;

    @Schema(example = "No salt, please")
    private String observation;
}
