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
public class RestaurantModel extends RepresentationModel<RestaurantModel> {

    @ApiModelProperty(example = "1")
//    @JsonView({RestaurantView.Summary.class, RestaurantView.JustName.class})
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
//    @JsonView({RestaurantView.Summary.class, RestaurantView.JustName.class})
    private String name;

    @ApiModelProperty(example = "10.00")
//    @JsonView(RestaurantView.Summary.class)
    private BigDecimal deliveryFee;

//    @JsonView(RestaurantView.Summary.class)
    private KitchenModel kitchen;

    private Boolean active;
    private AddressModel address;
    private Boolean open;
}
