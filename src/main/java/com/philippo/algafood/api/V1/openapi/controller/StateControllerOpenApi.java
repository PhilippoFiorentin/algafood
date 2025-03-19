package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.StateModel;
import com.philippo.algafood.api.V1.model.input.StateInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "States")
public interface StateControllerOpenApi {

    @Operation(summary = "List all states")
    CollectionModel<StateModel> list();

    @Operation(summary = "Find a state by ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid state ID", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
            @ApiResponse(responseCode = "404", description = "State not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) })
    })
    StateModel find(@Parameter(description = "State ID", example = "1", required = true) Long stateId);

    @Operation(summary = "Register a state", responses = {
            @ApiResponse(responseCode = "201", description = "Registered state")
    })
    StateModel add(@RequestBody(description = "Representation of a new state", required = true) StateInput stateInput);

    @Operation(summary = "Update a state by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Updated state"),
            @ApiResponse(responseCode = "404", description = "State not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) })
    })
    StateModel update(@Parameter(description = "State ID", example = "1", required = true) Long stateId,
                      @RequestBody(description = "Representation of a state with new data", required = true) StateInput stateInput);

    @Operation(summary = "Delete a state by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Deleted state"),
            @ApiResponse(responseCode = "404", description = "State not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) })
    })
    void delete(@Parameter(description = "State ID", example = "1", required = true) Long stateId);
}
