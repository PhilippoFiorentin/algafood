package com.philippo.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class OrderModel {

    private Long id;
    private BigDecimal subtotal;
    private BigDecimal deliveryFee;
    private BigDecimal total;
    private String status;
    private OffsetDateTime creationDate;
    private OffsetDateTime confirmationDate;
    private OffsetDateTime deliveryDate;
    private OffsetDateTime cancellationDate;
    private RestaurantSummaryModel restaurant;
    private UserModel client;
    private PaymentMethodModel paymentMethod;
    private AddressModel address;
    private List<OrderItemModel> items;

}
