package com.philippo.algafood.api.v1.assembler;

import com.philippo.algafood.api.v1.AlgaLinks;
import com.philippo.algafood.api.v1.model.PermissionModel;
import com.philippo.algafood.domain.model.Permission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PermissionModelAssembler implements RepresentationModelAssembler<Permission, PermissionModel> {

    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Override
    public PermissionModel toModel(Permission permission){
        return modelmapper.map(permission, PermissionModel.class);
    }

    @Override
    public CollectionModel<PermissionModel> toCollectionModel(Iterable<? extends Permission> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities).add(algaLinks.linkToPermissions());
    }
}
