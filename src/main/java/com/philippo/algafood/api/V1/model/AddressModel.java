package com.philippo.algafood.api.V1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressModel {

    @Schema(example = "D01 AB00")
    private String zipCode;

    @Schema(example = "Example street")
    private String street;

    @Schema(example = "1")
    private String number;

    @Schema(example = "Apartment 1")
    private String additional;

    @Schema(example = "Dublin")
    private String district;

    @Schema(example = "Dublin")
    private CitySummaryModel city;
}
