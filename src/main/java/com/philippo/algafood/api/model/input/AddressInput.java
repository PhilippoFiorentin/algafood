package com.philippo.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddressInput {

    @ApiModelProperty(example = "D31YX00", required = true)
    @NotBlank
    private String zipCode;

    @ApiModelProperty(example = "Example street", required = true)
    @NotBlank
    private String street;

    @ApiModelProperty(example = "\"1500\"", required = true)
    @NotBlank
    private String number;

    @ApiModelProperty(example = "Apartment 11")
    private String additional;

    @ApiModelProperty(example = "Dublin", required = true)
    @NotBlank
    private String district;

    @Valid
    @NotNull
    private CityIdInput city;
}
