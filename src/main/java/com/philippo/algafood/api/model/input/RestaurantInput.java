package com.philippo.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(example = "Thai Gourmet", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(example = "10.00", required = true)
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
