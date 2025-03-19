package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.BasicRestaurantModel;
import com.philippo.algafood.api.V1.model.RestaurantJustNameModel;
import com.philippo.algafood.api.V1.model.RestaurantModel;
import com.philippo.algafood.api.V1.model.input.RestaurantInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurants")
public interface RestaurantControllerOpenApi {

    @Operation(parameters = {
            @Parameter(
                    name = "projection",
                    description = "Projection name",
                    example = "just-name",
                    in = ParameterIn.QUERY,
                    required = false
            )
    })
    CollectionModel<BasicRestaurantModel> list();

    @Operation(hidden = true)
    CollectionModel<RestaurantJustNameModel> listJustNames();

    @Operation(summary = "Find a restaurant by ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid restaurant ID", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    RestaurantModel find(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId);

    @Operation(summary = "Register a restaurant", responses = {
            @ApiResponse(responseCode = "201", description = "Registered restaurant"),
    })
    RestaurantModel add(@RequestBody(description = "Representation of a new restaurant", required = true) RestaurantInput restaurantInput);

    @Operation(summary = "Update a restaurant by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Updated restaurant"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    RestaurantModel update(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
                           @RequestBody(description = "Representation of a new restaurant", required = true) RestaurantInput restaurantInput);

    @Operation(summary = "Activate a restaurant by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurant successfully activated"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ResponseEntity<Void> activate(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId);

    @Operation(summary = "Deactivate a restaurant by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurant successfully deactivated"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ResponseEntity<Void> deactivate(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId);

    @Operation(summary = "Activate multiple restaurants", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurants successfully activated"),
    })
    ResponseEntity<Void> activateManyRestaurants(
            @RequestBody(description = "Restaurant IDs", required = true) List<Long> restaurantIds);

    @Operation(summary = "Deactivate multiple restaurants", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurants successfully deactivated"),
    })
    ResponseEntity<Void> deactivateManyRestaurants(
            @RequestBody(description = "Restaurant IDs", required = true) List<Long> restaurantIds);

    @Operation(summary = "Open a restaurant by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurant opened successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ResponseEntity<Void> open(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId);

    @Operation(summary = "Close a restaurant by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Restaurant closed successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ResponseEntity<Void> close(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId);
}
