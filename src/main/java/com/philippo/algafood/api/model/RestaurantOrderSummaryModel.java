package com.philippo.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class RestaurantOrderSummaryModel {

    private String uuid;
    private BigDecimal subtotal;
    private BigDecimal deliveryFee;
    private BigDecimal total;
    private String status;
    private OffsetDateTime creationDate;
    private RestaurantSummaryModel restaurant;
    private UserModel client;

}
