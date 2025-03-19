package com.philippo.algafood.api.V1.model;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String uuid;

    @Schema(example = "298.90")
    private BigDecimal subtotal;

    @Schema(example = "10.00")
    private BigDecimal deliveryFee;

    @Schema(example = "308.90")
    private BigDecimal total;

    @Schema(example = "CREATED")
    private String status;

    @Schema(example = "2025-03-18T20:34:04Z")
    private OffsetDateTime creationDate;

    @Schema(example = "2025-03-18T20:34:04Z")
    private OffsetDateTime confirmationDate;

    @Schema(example = "2025-03-18T21:34:04Z")
    private OffsetDateTime deliveryDate;

    @Schema(example = "2025-03-18T22:34:04Z")
    private OffsetDateTime cancellationDate;

    private RestaurantJustNameModel restaurant;
    private UserModel client;
    private PaymentMethodModel paymentMethod;
    private AddressModel address;
    private List<OrderItemModel> items;

}
