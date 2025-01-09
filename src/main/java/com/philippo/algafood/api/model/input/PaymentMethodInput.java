package com.philippo.algafood.api.model.input;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PaymentMethodInput {

    @ApiModelProperty(example = "Credit card", required = true)
    @NotBlank
    private String description;

}
