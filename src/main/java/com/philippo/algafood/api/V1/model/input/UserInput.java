package com.philippo.algafood.api.V1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserInput {

    @Schema(example = "John Smith")
    @NotBlank
    private String name;

    @Schema(example = "johnsmith@email.com")
    @NotBlank
    @Email
    private String email;
}
