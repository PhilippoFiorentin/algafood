package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.PermissionModel;
import org.springframework.hateoas.CollectionModel;

public interface PermissionControllerOpenApi {

    CollectionModel<PermissionModel> list();
}
