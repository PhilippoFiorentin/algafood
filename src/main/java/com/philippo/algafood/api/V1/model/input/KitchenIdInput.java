package com.philippo.algafood.api.V1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class KitchenIdInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;
}
