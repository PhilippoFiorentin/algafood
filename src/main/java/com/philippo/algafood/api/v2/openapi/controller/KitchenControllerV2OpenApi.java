package com.philippo.algafood.api.v2.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.v2.model.KitchenModelV2;
import com.philippo.algafood.api.v2.model.input.KitchenInputV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Kitchens")
public interface KitchenControllerV2OpenApi {

    @ApiOperation("List kitchens with pagination")
    PagedModel<KitchenModelV2> list(Pageable pageable);

    @ApiOperation("Search a kitchen by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    description = "Invalid kitchen ID",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Kitchen not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    KitchenModelV2 find(@ApiParam(value = "ID of a kitchen", example = "1", required = true) Long kitchenId);

    @ApiOperation("Register a kitchen")
    KitchenModelV2 add(@ApiParam(name = "body", value = "Representation of a new kitchen", required = true) KitchenInputV2 kitchenInput);

    @ApiOperation("Update a kitchen by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Kitchen not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    KitchenModelV2 update(@ApiParam(value = "ID of a kitchen", example = "1", required = true) Long kitchenId,
                               @ApiParam(name = "body", value = "representation of a kitchen with the new data", required = true) KitchenInputV2 kitchenInput);

    @ApiOperation("Delete a kitchen")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Kitchen not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    void delete(@ApiParam(value = "ID of a kitchen", example = "1", required = true) Long kitchenId);
}
