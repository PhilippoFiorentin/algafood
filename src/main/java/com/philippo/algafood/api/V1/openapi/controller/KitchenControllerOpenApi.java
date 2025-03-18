package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.KitchenModel;
import com.philippo.algafood.api.V1.model.input.KitchenInput;
import com.philippo.algafood.core.springdoc.PageableParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Kitchens")
public interface KitchenControllerOpenApi {

    @PageableParameter
    @Operation(summary = "List kitchens with pagination")
    PagedModel<KitchenModel> list(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Find a kitchen by ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid kitchen ID",
                    content = @Content(schema = @Schema(ref = "Problem"))),
            @ApiResponse(responseCode = "404", description = "Kitchen not found",
                    content = @Content(schema = @Schema(ref = "Problem")))
    })
    KitchenModel find(@Parameter(description = "Kitchen ID", example = "1", required = true) Long kitchenId);

    @Operation(summary = "Register a kitchen", responses = {
            @ApiResponse(responseCode = "201", description = "Kitchen registered"),
    })
    KitchenModel add(@RequestBody(description = "Representation of a new kitchen", required = true) KitchenInput kitchenInput);

    @Operation(summary = "Update a kitchen by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Kitchen updated"),
            @ApiResponse(responseCode = "404", description = "Kitchen not found",
                    content = @Content(schema = @Schema(ref = "Problem"))),
    })
    KitchenModel update(@Parameter(description = "Kitchen ID", example = "1", required = true) Long kitchenId,
                        @RequestBody(description = "Representation of a kitchen with new data", required = true) KitchenInput kitchenInput);

    @Operation(summary = "Delete a kitchen by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Kitchen deleted"),
            @ApiResponse(responseCode = "404", description = "Kitchen not found",
                    content = @Content(schema = @Schema(ref = "Problem")))
    })
    void delete(@Parameter(description = "Kitchen ID", example = "1", required = true) Long kitchenId);
}
