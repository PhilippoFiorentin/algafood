package com.philippo.algafood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserWithPasswordInput extends UserInput{

    @ApiModelProperty(example = "123", required = true)
    @NotBlank
    private String password;
}
