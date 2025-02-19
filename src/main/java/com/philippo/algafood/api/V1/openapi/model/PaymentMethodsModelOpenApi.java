package com.philippo.algafood.api.V1.openapi.model;

import com.philippo.algafood.api.V1.model.PaymentMethodModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("PaymentMethodsModel")
@Data
public class PaymentMethodsModelOpenApi {

    private EmbeddedPaymentMethodModelOpenApi _embedded;
    private Links _links;

    @ApiModel("EmbeddedPaymentMethodsModel")
    @Data
    public class EmbeddedPaymentMethodModelOpenApi {
        private List<PaymentMethodModel> paymentMethodModels;
    }
}
