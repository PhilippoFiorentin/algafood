package com.philippo.algafood.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.philippo.algafood.api.model.view.RestaurantView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KitchenModel {

    @JsonView(RestaurantView.Summary.class)
    private Long id;

    @JsonView(RestaurantView.Summary.class)
    private String name;
}
