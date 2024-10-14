package com.philippo.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemModel {

    private Long id;
    private String productName;
    private Integer quantity;
    private BigDecimal unitaryPrice;
    private BigDecimal totalPrice;
    private BigDecimal observation;
}
