package com.philippo.algafood.api.V1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PaymentMethodIdInput {

    @NotNull
    private Long id;

}
