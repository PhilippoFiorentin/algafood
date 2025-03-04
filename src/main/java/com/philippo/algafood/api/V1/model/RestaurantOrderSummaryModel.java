package com.philippo.algafood.api.V1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Relation(collectionRelation = "orders")
@Getter
@Setter
public class RestaurantOrderSummaryModel extends RepresentationModel<RestaurantOrderSummaryModel> {

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


    private RestaurantJustNameModel restaurant;
    private UserModel client;

}
