package com.philippo.algafood.api.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.model.StateModel;
import com.philippo.algafood.api.model.input.StateInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Api(tags = "States")
public interface StateControllerOpenApi {

    @ApiOperation("List states")
    List<StateModel> list();

    @ApiOperation("Search for a state by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    description = "Invalid state ID",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "State not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    StateModel find(@ApiParam(value = "ID of a state", example = "1", required = true) Long stateId);

    @ApiOperation("Register a state")
    StateModel add(@ApiParam(name = "body", value = "Representation of a new state", required = true) StateInput stateInput);

    @ApiOperation("Update a state by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "City not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    StateModel update(
            @ApiParam(value = "ID of a state", example = "1", required = true) Long stateId,
            @ApiParam(name = "body", value = "Representation of a new state", required = true) StateInput stateInput);

    @ApiOperation("Delete a state")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "City not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    void delete(@ApiParam(value = "ID of a state", example = "1", required = true) Long stateId);
}
