package com.philippo.algafood.api.V1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "kitchens")
@Getter
@Setter
public class KitchenModel extends RepresentationModel<KitchenModel> {

    @ApiModelProperty(example = "1")
//    @JsonView(RestaurantView.Summary.class)
    private Long id;

    @ApiModelProperty(example = "Thai")
//    @JsonView(RestaurantView.Summary.class)
    private String name;
}
