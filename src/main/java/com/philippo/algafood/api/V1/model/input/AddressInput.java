package com.philippo.algafood.api.V1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class AddressInput {

    @Schema(example = "D01 AB00")
    @NotBlank
    private String zipCode;

    @Schema(example = "Example street")
    @NotBlank
    private String street;

    @Schema(example = "1")
    @NotBlank
    private String number;

    @Schema(example = "Apartment 1")
    private String additional;

    @Schema(example = "Dublin")
    @NotBlank
    private String district;

    @Valid
    @NotNull
    private CityIdInput city;
}
