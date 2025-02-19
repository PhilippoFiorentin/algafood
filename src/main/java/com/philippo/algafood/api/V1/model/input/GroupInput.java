package com.philippo.algafood.api.V1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GroupInput {

    @ApiModelProperty(example = "Manager", required = true)
    @NotBlank
    private String name;
}
