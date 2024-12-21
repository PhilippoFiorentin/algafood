package com.philippo.algafood.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.philippo.algafood.api.model.view.RestaurantView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestaurantModel {

    @ApiModelProperty(example = "1")
    @JsonView({RestaurantView.Summary.class, RestaurantView.JustName.class})
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
    @JsonView({RestaurantView.Summary.class, RestaurantView.JustName.class})
    private String name;

    @ApiModelProperty(example = "10.00")
    @JsonView(RestaurantView.Summary.class)
    private BigDecimal deliveryFee;

    @JsonView(RestaurantView.Summary.class)
    private KitchenModel kitchen;

    private Boolean active;
    private AddressModel address;
    private Boolean open;
}
