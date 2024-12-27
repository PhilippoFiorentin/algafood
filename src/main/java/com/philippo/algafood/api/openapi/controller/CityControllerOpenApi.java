package com.philippo.algafood.api.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.model.CityModel;
import com.philippo.algafood.api.model.input.CityInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Api(tags = "Cities")
public interface CityControllerOpenApi {

    @ApiOperation("List cities")
    List<CityModel> list();

    @ApiOperation("Search for a city by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    description = "Invalid city ID",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "City not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    CityModel find(@ApiParam(value = "ID of a city", example = "1", required = true) Long cityId);

    @ApiOperation("Register a city")
    CityModel add(@ApiParam(name = "body", value = "Representation of a new city", required = true) CityInput cityInput);

    @ApiOperation("Update city by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "City not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    CityModel update(@ApiParam(value = "ID of a city", example = "1", required = true) Long cityId,
                            @ApiParam(name = "body", value = "representation of a city with the new data", required = true) CityInput cityInput);

    @ApiOperation("Delete a city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "City not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    void delete(@ApiParam(value = "ID of a city", example = "1", required = true) Long cityId);

}
