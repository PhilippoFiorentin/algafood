package com.philippo.algafood.api.V1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurants")
@Getter
@Setter
public class BasicRestaurantModel extends RepresentationModel<BasicRestaurantModel> {

    private Long id;

    private String name;

    private BigDecimal deliveryFee;

    private KitchenModel kitchen;
}
