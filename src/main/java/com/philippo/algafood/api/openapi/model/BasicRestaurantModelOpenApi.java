package com.philippo.algafood.api.openapi.model;

import com.philippo.algafood.api.model.KitchenModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@ApiModel("BasicRestaurantModel")
@Getter
@Setter
public class BasicRestaurantModelOpenApi {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
    private String name;

    @ApiModelProperty(example = "10.00")
    private BigDecimal deliveryFee;

    private KitchenModel kitchen;
}
