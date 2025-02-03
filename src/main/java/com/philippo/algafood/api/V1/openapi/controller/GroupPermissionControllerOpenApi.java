package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.V1.model.PermissionModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Groups")
public interface GroupPermissionControllerOpenApi {

    @ApiOperation("List group permisssions")
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
    CollectionModel<PermissionModel> list(@ApiParam(value = "Group ID", example = "1", required = true) Long groupId);

    @ApiOperation("Disassociation of permission with group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Group not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ResponseEntity<Void> disaffiliate(@ApiParam(value = "Group ID", example = "1", required = true) Long groupId,
                      @ApiParam(value = "Group permission ID", example = "1", required = true) Long permissionId);

    @ApiOperation("Association of permission with group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Group not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ResponseEntity<Void> affiliate(@ApiParam(value = "Group ID", example = "1", required = true) Long groupId,
                                   @ApiParam(value = "Group permission ID", example = "1", required = true) Long permissionId);
}
