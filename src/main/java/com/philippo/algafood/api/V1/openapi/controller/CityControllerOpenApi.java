package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.CityModel;
import com.philippo.algafood.api.V1.model.input.CityInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cities")
public interface CityControllerOpenApi {

    @Operation(summary = "List cities")
    CollectionModel<CityModel> list();

    @Operation(summary = "Find a city by ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid city ID", content = @Content(schema = @Schema(ref = "Problem"))),
            @ApiResponse(responseCode = "404", description = "City not found", content = @Content(schema = @Schema(ref = "Problem")))
    })
    CityModel find(@Parameter(description = "City ID", example = "1", required = true) Long cityId);

    @Operation(summary = "Register a city")
    CityModel add(@RequestBody(description = "New city representation", required = true) CityInput cityInput);

    @Operation(summary = "Update a city by ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid city ID", content = @Content(schema = @Schema(ref = "Problem"))),
            @ApiResponse(responseCode = "404", description = "City not found", content = @Content(schema = @Schema(ref = "Problem")))
    })
    CityModel update(@Parameter(description = "City ID", example = "1", required = true) Long cityId,
                     @RequestBody(description = "New city representation with updated data", required = true) CityInput cityInput);

    @Operation(summary = "Delete a city by ID", responses = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", description = "Invalid city ID", content = @Content(schema = @Schema(ref = "Problem"))),
            @ApiResponse(responseCode = "404", description = "City not found", content = @Content(schema = @Schema(ref = "Problem")))
    })
    void delete(@Parameter(description = "City ID", example = "1", required = true) Long cityId);

}
