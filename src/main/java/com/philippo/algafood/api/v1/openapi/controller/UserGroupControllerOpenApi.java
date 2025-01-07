package com.philippo.algafood.api.v1.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.v1.model.GroupModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Users")
public interface UserGroupControllerOpenApi {

    @ApiOperation("List groups associated with a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "User or group not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    CollectionModel<GroupModel> list(@ApiParam(value = "User ID", example = "1", required = true) Long userId);

    @ApiOperation("Disassociating a group with a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "User or group not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ResponseEntity<Void> disaffiliate(@ApiParam(value = "User ID", example = "1", required = true) Long userId,
                                @ApiParam(value = "Group ID", example = "1", required = true)Long groupId);

    @ApiOperation("Associating a group with a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "User or group not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ResponseEntity<Void> affiliate(@ApiParam(value = "User ID", example = "1", required = true) Long userId,
                   @ApiParam(value = "Group ID", example = "1", required = true) Long groupId);
}
