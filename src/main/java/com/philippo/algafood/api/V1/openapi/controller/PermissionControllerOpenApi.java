package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.PermissionModel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
public interface PermissionControllerOpenApi {

    CollectionModel<PermissionModel> list();
}
