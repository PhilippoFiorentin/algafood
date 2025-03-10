package com.philippo.algafood.api.V1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "products")
@Getter
@Setter
public class ProductModel extends RepresentationModel<ProductModel> {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Boolean active;
}
