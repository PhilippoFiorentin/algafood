package com.philippo.algafood.api.V1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurants")
@Getter
@Setter
public class BasicRestaurantModel extends RepresentationModel<BasicRestaurantModel> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "French Gourmet")
    private String name;

    @Schema(example = "10.00")
    private BigDecimal deliveryFee;

    private KitchenModel kitchen;
}
