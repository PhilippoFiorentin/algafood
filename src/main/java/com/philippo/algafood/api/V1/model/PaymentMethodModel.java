package com.philippo.algafood.api.V1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "paymentMethods")
@Getter
@Setter
public class PaymentMethodModel extends RepresentationModel<PaymentMethodModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Credit card")
    private String description;
}
