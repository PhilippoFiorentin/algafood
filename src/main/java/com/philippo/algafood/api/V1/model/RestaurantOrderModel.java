package com.philippo.algafood.api.V1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Relation(collectionRelation = "orders")
@Getter
@Setter
public class RestaurantOrderModel extends RepresentationModel<RestaurantOrderModel> {

    private String uuid;

    private BigDecimal subtotal;

    private BigDecimal deliveryFee;

    private BigDecimal total;

    private String status;

    private OffsetDateTime creationDate;

    private OffsetDateTime confirmationDate;

    private OffsetDateTime deliveryDate;

    private OffsetDateTime cancellationDate;

    private RestaurantJustNameModel restaurant;
    private UserModel client;
    private PaymentMethodModel paymentMethod;
    private AddressModel address;
    private List<OrderItemModel> items;

}
