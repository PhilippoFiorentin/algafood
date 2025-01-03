package com.philippo.algafood.api.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.model.GroupModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Api(tags = "Users")
public interface UserGroupControllerOpenApi {

    @ApiOperation("List groups associated with a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "User or group not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    List<GroupModel> list(@ApiParam(value = "User ID", example = "1", required = true) Long userId);

    @ApiOperation("Disassociating a group with a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "User or group not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    void disaffiliate(@ApiParam(value = "User ID", example = "1", required = true) Long userId,
                      @ApiParam(value = "Group ID", example = "1", required = true)Long groupId);

    @ApiOperation("Associating a group with a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "User or group not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    void affiliate(@ApiParam(value = "User ID", example = "1", required = true) Long userId,
                   @ApiParam(value = "Group ID", example = "1", required = true) Long groupId);
}