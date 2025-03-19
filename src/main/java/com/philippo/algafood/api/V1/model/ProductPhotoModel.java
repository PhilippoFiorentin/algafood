package com.philippo.algafood.api.V1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "photos")
@Getter
@Setter
public class ProductPhotoModel extends RepresentationModel<ProductPhotoModel> {

    @Schema(description = "b8bbd21a-4dd3-4954-835c-3493af2ba6a0_Fish-Chips.jpg")
    private String filename;

    @Schema(description = "Fish and chips")
    private String description;

    @Schema(description = "image/jpeg")
    private String contentType;

    @Schema(description = "20912")
    private Long size;
}
