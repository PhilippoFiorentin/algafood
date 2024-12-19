package com.philippo.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethodModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Credit card")
    private String description;
}
