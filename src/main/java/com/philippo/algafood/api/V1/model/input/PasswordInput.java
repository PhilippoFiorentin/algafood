package com.philippo.algafood.api.V1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PasswordInput {

    @ApiModelProperty(example = "123", required = true)
    @NotBlank
    private String currentPassword;

    @ApiModelProperty(example = "1234", required = true)
    @NotBlank
    private String newPassword;
}
