package com.philippo.algafood.api.V1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GroupInput {

    @Schema(example = "Manager")
    @NotBlank
    private String name;
}
