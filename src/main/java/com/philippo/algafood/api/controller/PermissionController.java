package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.PermissionModelAssembler;
import com.philippo.algafood.api.model.PermissionModel;
import com.philippo.algafood.api.openapi.controller.PermissionControllerOpenApi;
import com.philippo.algafood.domain.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
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
