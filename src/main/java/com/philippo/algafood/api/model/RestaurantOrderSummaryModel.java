package com.philippo.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class RestaurantOrderSummaryModel {

    @ApiModelProperty(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String uuid;

    @ApiModelProperty(example = "23.80")
    private BigDecimal subtotal;

    @ApiModelProperty(example = "10.00")
    private BigDecimal deliveryFee;

    @ApiModelProperty(example = "33.80")
    private BigDecimal total;

    @ApiModelProperty(example = "CREATED")
    private String status;

    @ApiModelProperty(example = "2024-12-01T20:34:04Z")
    private OffsetDateTime creationDate;


    private RestaurantSummaryModel restaurant;
    private UserModel client;

}
