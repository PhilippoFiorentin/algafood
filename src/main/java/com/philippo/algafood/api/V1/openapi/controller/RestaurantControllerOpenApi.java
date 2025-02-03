package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.V1.model.BasicRestaurantModel;
import com.philippo.algafood.api.V1.model.RestaurantJustNameModel;
import com.philippo.algafood.api.V1.model.RestaurantModel;
import com.philippo.algafood.api.V1.model.input.RestaurantInput;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "Restaurants")
public interface RestaurantControllerOpenApi {

    @ApiOperation(value = "List restaurants")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Orders projection name",
                    allowableValues = "just-name", name = "projection", paramType = "query", type = "string")
    })
    CollectionModel<BasicRestaurantModel> list();

    @ApiIgnore
    @ApiOperation(value = "List restaurant names", hidden = true)
    CollectionModel<RestaurantJustNameModel> listJustNames();

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
    RestaurantModel find(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId);

    @ApiOperation("Register a restaurant")
    RestaurantModel add(@ApiParam(
            name = "body", value = "Representation of a new restaurant", required = true) RestaurantInput restaurantInput);

    @ApiOperation("Update a restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "City not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    RestaurantModel update(
            @ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId,
            @ApiParam(name = "body", value = "representation of a restaurant with the new data",  required = true) RestaurantInput restaurantInput);

    @ApiOperation("Activate a restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ResponseEntity<Void> activate(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId);

    @ApiOperation("Deactivate a restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ResponseEntity<Void> deactivate(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId);

    @ApiOperation("Activate multiple restaurant")
    void activateManyRestaurants(@ApiParam(
            name = "body", value = "Restaurant IDs",  required = true) List<Long> restaurantIds);

    @ApiOperation("Deactivate multiple restaurant")
    void deactivateManyRestaurants(@ApiParam(
            name = "body", value = "Restaurant IDs",  required = true) List<Long> restaurantIds);

    @ApiOperation("Open a restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ResponseEntity<Void> open(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId);

    @ApiOperation("Close a restaurant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ResponseEntity<Void> close(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId);
}
