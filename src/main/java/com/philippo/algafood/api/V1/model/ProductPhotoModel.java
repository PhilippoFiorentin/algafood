package com.philippo.algafood.api.V1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "photos")
@Getter
@Setter
public class ProductPhotoModel extends RepresentationModel<ProductPhotoModel> {

    private String filename;

    private String description;

    private String contentType;

    private Long size;
}
