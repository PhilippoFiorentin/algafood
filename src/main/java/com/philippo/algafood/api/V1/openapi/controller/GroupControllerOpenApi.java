package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.GroupModel;
import com.philippo.algafood.api.V1.model.input.GroupInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Groups")
public interface GroupControllerOpenApi {

    @Operation(summary = "List groups")
    CollectionModel<GroupModel> list();

    @Operation(summary = "Find a group by ID")
    GroupModel find(@Parameter(description = "Group ID", example = "1", required = true) Long groupId);

    @Operation(summary = "Register a new group")
    GroupModel add(@RequestBody(description = "New group representation", required = true) GroupInput groupInput);

    @Operation(summary = "Update a group by ID")
    GroupModel update(
            @Parameter(description = "Group ID", example = "1", required = true) Long groupId,
            @RequestBody(description = "Group representation with updated data", required = true)  GroupInput groupInput);

    @Operation(summary = "Delete a group by ID")
    void delete(@Parameter(description = "Group ID", example = "1", required = true) Long groupId);

}
