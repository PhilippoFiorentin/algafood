package com.philippo.algafood.api.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.model.RestaurantModel;
import com.philippo.algafood.api.model.input.RestaurantInput;
import com.philippo.algafood.api.openapi.model.BasicRestaurantModelOpenApi;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Api(tags = "Restaurants")
public interface RestaurantsControllerOpenApi {

    @ApiOperation(value = "List restaurants", response = BasicRestaurantModelOpenApi.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Orders projection name",
                    allowableValues = "just-name", name = "projection", paramType = "query", type = "string")
    })
    public List<RestaurantModel> list();

    @ApiOperation(value = "List restaurants", hidden = true)
    public List<RestaurantModel> listSummary();

    @ApiOperation("Search for a restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    description = "Invalid restaurant ID",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Restaurant not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    public RestaurantModel find(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId);

    @ApiOperation("Register a restaurant")
    public RestaurantModel add(@ApiParam(
            name = "body", value = "Representation of a new restaurant", required = true) RestaurantInput restaurantInput);

    @ApiOperation("Update a restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "City not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    public RestaurantModel update(
            @ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId,
            @ApiParam(name = "body", value = "representation of a restaurant with the new data",  required = true) RestaurantInput restaurantInput);

    @ApiOperation("Activate a restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    public void activate(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId);

    @ApiOperation("Deactivate a restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    public void deactivate(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId);

    @ApiOperation("Activate multiple restaurant")
    public void activateManyRestaurants(@ApiParam(
            name = "body", value = "Restaurant IDs",  required = true) List<Long> restaurantIds);

    @ApiOperation("Deactivate multiple restaurant")
    public void deactivateManyRestaurants(@ApiParam(
            name = "body", value = "Restaurant IDs",  required = true) List<Long> restaurantIds);

    @ApiOperation("Open a restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    public void open(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId);

    @ApiOperation("Close a restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    public void close(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId);
}
