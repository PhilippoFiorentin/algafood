package com.philippo.algafood.api.V1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductInput {

    @Schema(example = "Fish and chips")
    @NotBlank
    private String name;

    @Schema(example = "Fish and chips")
    @NotBlank
    private String description;

    @Schema(example = "5.00")
    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @Schema(example = "true")
    @NotNull
    private Boolean active;
}
