package com.philippo.algafood.api.V1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurants")
@Getter
@Setter
public class BasicRestaurantModel extends RepresentationModel<BasicRestaurantModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
    private String name;

    @ApiModelProperty(example = "10.00")
    private BigDecimal deliveryFee;

    private KitchenModel kitchen;
}
