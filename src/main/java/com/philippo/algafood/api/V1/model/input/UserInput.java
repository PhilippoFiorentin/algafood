package com.philippo.algafood.api.V1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserInput {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;
}
