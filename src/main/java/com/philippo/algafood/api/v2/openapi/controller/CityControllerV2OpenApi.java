package com.philippo.algafood.api.v2.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.v2.model.CityModelV2;
import com.philippo.algafood.api.v2.model.input.CityInputV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Cities")
public interface CityControllerV2OpenApi {

    @ApiOperation("List cities")
    CollectionModel<CityModelV2> list();

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
    CityModelV2 find(@ApiParam(value = "ID of a city", example = "1", required = true) Long cityId);

    @ApiOperation("Register a city")
    CityModelV2 add(@ApiParam(name = "body", value = "Representation of a new city", required = true) CityInputV2 cityInput);

    @ApiOperation("Update city by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "City not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    CityModelV2 update(@ApiParam(value = "ID of a city", example = "1", required = true) Long cityId,
                            @ApiParam(name = "body", value = "representation of a city with the new data", required = true) CityInputV2 cityInput);

    @ApiOperation("Delete a city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "City not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    void delete(@ApiParam(value = "ID of a city", example = "1", required = true) Long cityId);

}
