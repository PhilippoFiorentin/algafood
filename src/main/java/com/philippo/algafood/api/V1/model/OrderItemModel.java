package com.philippo.algafood.api.V1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemModel extends RepresentationModel<OrderItemModel> {

    private Long id;

    private String productName;

    private Integer quantity;

    private BigDecimal unitaryPrice;

    private BigDecimal totalPrice;

    private String observation;
}
