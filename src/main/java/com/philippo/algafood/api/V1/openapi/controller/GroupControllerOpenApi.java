package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.GroupModel;
import com.philippo.algafood.api.V1.model.input.GroupInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
public interface GroupControllerOpenApi {

    CollectionModel<GroupModel> list();

    GroupModel find(Long groupId);

    GroupModel add(GroupInput groupInput);

    GroupModel update(Long groupId, GroupInput groupInput);

    void delete(Long groupId);

}
