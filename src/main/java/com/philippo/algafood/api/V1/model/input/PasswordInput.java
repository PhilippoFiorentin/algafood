package com.philippo.algafood.api.V1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class PasswordInput {

    @Schema(example = "123", type = "string")
    @NotBlank
    private String currentPassword;

    @Schema(example = "1234", type = "string")
    @NotBlank
    private String newPassword;
}
