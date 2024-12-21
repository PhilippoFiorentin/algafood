package com.philippo.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressModel {

    @ApiModelProperty(example = "D31YX00")
    private String zipCode;

    @ApiModelProperty(example = "Example street")
    private String street;

    @ApiModelProperty(example = "\"1500\"")
    private String number;

    @ApiModelProperty(example = "Apartment 11")
    private String additional;

    @ApiModelProperty(example = "Dublin")
    private String district;

    private CitySummaryModel city;
}
