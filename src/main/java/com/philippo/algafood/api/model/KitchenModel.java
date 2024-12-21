package com.philippo.algafood.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.philippo.algafood.api.model.view.RestaurantView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KitchenModel {

    @ApiModelProperty(example = "1")
    @JsonView(RestaurantView.Summary.class)
    private Long id;

    @ApiModelProperty(example = "Thai")
    @JsonView(RestaurantView.Summary.class)
    private String name;
}
