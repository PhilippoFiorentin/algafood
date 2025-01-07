package com.philippo.algafood.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemModel extends RepresentationModel<OrderItemModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Fish and chips")
    private String productName;

    @ApiModelProperty(example = "2")
    private Integer quantity;

    @ApiModelProperty(example = "11.90")
    private BigDecimal unitaryPrice;

    @ApiModelProperty(example = "23.80")
    private BigDecimal totalPrice;

    @ApiModelProperty(example = "No pepper, please")
    private String observation;
}
