package com.philippo.algafood.api.V1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "permissions")
@Getter
@Setter
public class PermissionModel extends RepresentationModel<PermissionModel> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "CONSULT_KITCHENS")
    private String name;

    @Schema(example = "It allows to consult kitchens")
    private String description;
}
