package com.philippo.algafood.api.V1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Setter
@Getter
public class RestaurantInput {

    @Schema(example = "French Gourmet")
    @NotBlank
    private String name;

    @Schema(example = "10.00")
    @NotNull
    @PositiveOrZero
    private BigDecimal deliveryFee;

    @Valid
    @NotNull
    private KitchenIdInput kitchen;

    @Valid
    @NotNull
    private AddressInput address;
}
