package com.philippo.algafood.api.V1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressModel {

    private String zipCode;

    private String street;

    private String number;

    private String additional;

    private String district;

    private CitySummaryModel city;
}
