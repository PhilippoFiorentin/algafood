package com.philippo.algafood.api.V1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddressInput {

    @NotBlank
    private String zipCode;

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    private String additional;

    @NotBlank
    private String district;

    @Valid
    @NotNull
    private CityIdInput city;
}
