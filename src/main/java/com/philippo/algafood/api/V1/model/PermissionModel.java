package com.philippo.algafood.api.V1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "permissions")
@Getter
@Setter
public class PermissionModel extends RepresentationModel<PermissionModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "SEARCH_KITCHENS")
    private String name;

    @ApiModelProperty(example = "It allows to search for kitchens")
    private String description;
}
