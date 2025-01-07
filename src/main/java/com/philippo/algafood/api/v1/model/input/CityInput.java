package com.philippo.algafood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CityInput {

    @ApiModelProperty(example = "Dublin", required = true)
    @NotBlank
    private String name;

    @Valid
    @NotNull
    private StateIdInput state;
}
