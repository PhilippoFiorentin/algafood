package com.philippo.algafood.api.V1.model;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "04813f77-79b5-11ec-9a17-0242ac1b0002")
    private String uuid;

    @Schema(example = "10.00")
    private BigDecimal subtotal;

    @Schema(example = "1.00")
    private BigDecimal deliveryFee;

    @Schema(example = "11.00")
    private BigDecimal total;

    @Schema(example = "CREATED")
    private String status;

    @Schema(example = "2025-03-18T20:34:04Z")
    private OffsetDateTime creationDate;


    private RestaurantJustNameModel restaurant;
    private UserModel client;

}
