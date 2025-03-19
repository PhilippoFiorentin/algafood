package com.philippo.algafood.api.V1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemModel extends RepresentationModel<OrderItemModel> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Fish and chips")
    private String productName;

    @Schema(example = "1")
    private Integer quantity;

    @Schema(example = "10.00")
    private BigDecimal unitaryPrice;

    @Schema(example = "10.00")
    private BigDecimal totalPrice;

    @Schema(example = "No salt, please")
    private String observation;
}
