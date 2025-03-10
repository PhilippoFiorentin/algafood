package com.philippo.algafood.api.V1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PasswordInput {

    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;
}
