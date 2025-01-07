package com.philippo.algafood.api.v1.openapi.controller;

import com.philippo.algafood.api.v1.model.PermissionModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Permissions")
public interface PermissionControllerOpenApi {

    @ApiOperation("List permissions")
    CollectionModel<PermissionModel> list();
}
