package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.GroupModel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface UserGroupControllerOpenApi {

    CollectionModel<GroupModel> list(Long userId);

    ResponseEntity<Void> disaffiliate(Long userId, Long groupId);

    ResponseEntity<Void> affiliate(Long userId, Long groupId);
}
