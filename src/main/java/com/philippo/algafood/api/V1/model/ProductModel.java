package com.philippo.algafood.api.V1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "products")
@Getter
@Setter
public class ProductModel extends RepresentationModel<ProductModel> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Fish and chips")
    private String name;

    @Schema(example = "Fish and chips")
    private String description;

    @Schema(example = "5.00")
    private BigDecimal price;

    @Schema(example = "true")
    private Boolean active;
}
