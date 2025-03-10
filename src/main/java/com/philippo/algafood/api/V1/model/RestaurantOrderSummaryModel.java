package com.philippo.algafood.api.V1.model;

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

    private String uuid;

    private BigDecimal subtotal;

    private BigDecimal deliveryFee;

    private BigDecimal total;

    private String status;

    private OffsetDateTime creationDate;


    private RestaurantJustNameModel restaurant;
    private UserModel client;

}
