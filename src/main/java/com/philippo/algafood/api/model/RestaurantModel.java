package com.philippo.algafood.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.philippo.algafood.api.model.view.RestaurantView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantModel {

    @JsonView(RestaurantView.Summary.class)
    private Long id;

    @JsonView(RestaurantView.Summary.class)
    private String name;

    @JsonView(RestaurantView.Summary.class)
    private BigDecimal deliveryFee;

    @JsonView(RestaurantView.Summary.class)
    private KitchenModel kitchen;

    private Boolean active;
    private AddressModel address;
    private Boolean open;
}
