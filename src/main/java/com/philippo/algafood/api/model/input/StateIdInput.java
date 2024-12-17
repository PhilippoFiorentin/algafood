package com.philippo.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StateIdInput {

    @ApiModelProperty(example = "1")
    @NotNull
    private Long id;
}