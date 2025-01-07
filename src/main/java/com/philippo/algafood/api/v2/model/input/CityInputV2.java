package com.philippo.algafood.api.v2.model.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("CityInput")
@Getter
@Setter
public class CityInputV2 {

    @ApiModelProperty(example = "Dublin", required = true)
    @NotBlank
    private String cityName;

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long stateId;
}
