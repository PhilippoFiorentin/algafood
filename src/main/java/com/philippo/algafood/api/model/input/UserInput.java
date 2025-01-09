package com.philippo.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserInput {

    @ApiModelProperty(example = "John Smith", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(example = "johnsmith@email.com", required = true)
    @NotBlank
    @Email
    private String email;
}
