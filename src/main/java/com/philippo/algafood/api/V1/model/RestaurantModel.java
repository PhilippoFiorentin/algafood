package com.philippo.algafood.api.V1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurants")
@Getter
@Setter
public class RestaurantModel extends RepresentationModel<RestaurantModel> {

    private Long id;

    private String name;

    private BigDecimal deliveryFee;

    private KitchenModel kitchen;

    private Boolean active;
    private AddressModel address;
    private Boolean open;
}
