package com.philippo.algafood.api.V1.controller;

import com.philippo.algafood.api.V1.assembler.PermissionModelAssembler;
import com.philippo.algafood.api.V1.model.PermissionModel;
import com.philippo.algafood.api.V1.openapi.controller.PermissionControllerOpenApi;
import com.philippo.algafood.domain.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissionController implements PermissionControllerOpenApi {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionModelAssembler permissionModelAssembler;

    @GetMapping
    public CollectionModel<PermissionModel> list() {
        return permissionModelAssembler.toCollectionModel(permissionRepository.findAll());
    }
}
