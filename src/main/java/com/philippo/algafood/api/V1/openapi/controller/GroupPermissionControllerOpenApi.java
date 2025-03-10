package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.PermissionModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface GroupPermissionControllerOpenApi {

    CollectionModel<PermissionModel> list(Long groupId);

    ResponseEntity<Void> disaffiliate(Long groupId, Long permissionId);

    ResponseEntity<Void> affiliate(Long groupId, Long permissionId);
}
