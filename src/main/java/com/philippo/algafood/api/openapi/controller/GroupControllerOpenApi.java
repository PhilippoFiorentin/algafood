package com.philippo.algafood.api.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.model.GroupModel;
import com.philippo.algafood.api.model.input.GroupInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Api(tags = "Groups")
public interface GroupControllerOpenApi {

    @ApiOperation("List groups")
    List<GroupModel> list();

    @ApiOperation("Search a group by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    description = "Invalid group ID",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Group not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    GroupModel find(@ApiParam(value = "ID of a group", example = "1", required = true) Long groupId);

    @ApiOperation("Register a group")
    GroupModel add(@ApiParam(name = "body", value = "Representation of a new group", required = true) GroupInput groupInput);

    @ApiOperation("Update a group by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Group not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    GroupModel update(@ApiParam(value = "ID of a group", example = "1", required = true) Long groupId,
                             @ApiParam(name = "body", value = "representation of a group with the new data", required = true) GroupInput groupInput);

    @ApiOperation("Delete a group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Group not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    void delete(@ApiParam(value = "ID of a group", example = "1", required = true) Long groupId);

}
