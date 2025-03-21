package com.philippo.algafood.api.V1.model.input;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UserWithPasswordInput extends UserInput{

    @NotBlank
    private String password;
}
