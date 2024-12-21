package com.philippo.algafood.api.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.model.KitchenModel;
import com.philippo.algafood.api.model.input.KitchenInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Api(tags = "Kitchens")
public interface KitchenControllerOpenApi {

    @ApiOperation("List kitchens with pagination")
    public Page<KitchenModel> list(Pageable pageable);

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
    public KitchenModel find(@ApiParam(value = "ID of a kitchen", example = "1", required = true) Long kitchenId);

    @ApiOperation("Register a kitchen")
    public KitchenModel add(@ApiParam(name = "body", value = "Representation of a new kitchen", required = true) KitchenInput kitchenInput);

    @ApiOperation("Update a kitchen by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Kitchen not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    public KitchenModel update(@ApiParam(value = "ID of a kitchen", example = "1", required = true) Long kitchenId,
                               @ApiParam(name = "body", value = "representation of a kitchen with the new data", required = true) KitchenInput kitchenInput);

    @ApiOperation("Delete a kitchen")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Kitchen not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    public void delete(@ApiParam(value = "ID of a kitchen", example = "1", required = true) Long kitchenId);
}
