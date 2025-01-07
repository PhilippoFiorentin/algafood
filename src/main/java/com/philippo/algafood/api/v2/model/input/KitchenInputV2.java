package com.philippo.algafood.api.v2.model.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@ApiModel("KitchenInput")
@Getter
@Setter
public class KitchenInputV2 {

    @ApiModelProperty(example = "Thai", required = true)
    @NotBlank
    private String kitchenName;
}
