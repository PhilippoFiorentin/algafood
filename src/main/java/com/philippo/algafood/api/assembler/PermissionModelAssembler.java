package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.model.PermissionModel;
import com.philippo.algafood.domain.model.Permission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissionModelAssembler {

    @Autowired
    private ModelMapper modelmapper;

    public PermissionModel toModel(Permission permission){
        return modelmapper.map(permission, PermissionModel.class);
    }

    public List<PermissionModel> toCollectionModel(Collection<Permission> permissions) {
        return permissions.stream()
                .map(permission -> toModel(permission))
                .collect(Collectors.toList());
    }
}
