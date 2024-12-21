package com.philippo.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Fish and chips")
    private String name;

    @ApiModelProperty(example = "Comes with sauce")
    private String description;

    @ApiModelProperty(example = "10.00")
    private BigDecimal price;

    @ApiModelProperty(example = "true")
    private Boolean active;
}
