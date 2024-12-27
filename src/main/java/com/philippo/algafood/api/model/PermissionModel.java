package com.philippo.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "SEARCH_KITCHENS")
    private String name;

    @ApiModelProperty(example = "It allows to search for kitchens")
    private String description;
}
