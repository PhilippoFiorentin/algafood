package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.V1.model.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Restaurants")
public interface RestaurantResponsibleUserControllerOpenApi {

    @ApiOperation("List the user groups associated with the restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    CollectionModel<UserModel> list(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId);

    @ApiOperation("Disassociation of restaurant with user group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant or user not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ResponseEntity<Void> disaffiliate(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId,
                                @ApiParam(value = "ID of a user", example = "1", required = true) Long userId);

    @ApiOperation("Association of restaurant with user group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant or user not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ResponseEntity<Void> affiliate(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId,
                   @ApiParam(value = "ID of a user", example = "1", required = true) Long userId);
}
