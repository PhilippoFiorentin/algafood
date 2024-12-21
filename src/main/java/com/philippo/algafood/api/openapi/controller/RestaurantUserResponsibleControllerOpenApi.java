package com.philippo.algafood.api.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.model.GroupModel;
import com.philippo.algafood.api.model.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api(tags = "Restaurants")
public interface RestaurantUserResponsibleControllerOpenApi {

    @ApiOperation("List the user groups associated with the restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    List<UserModel> list(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId);

    @ApiOperation("Disassociation of restaurant with user group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant or user not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    void disaffiliate(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId,
                      @ApiParam(value = "ID of a user", example = "1", required = true) Long userId);

    @ApiOperation("Association of restaurant with user group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant or user not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    void affiliate(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId,
                   @ApiParam(value = "ID of a user", example = "1", required = true) Long userId);
}
