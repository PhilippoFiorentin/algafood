package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.RestaurantOrderModel;
import com.philippo.algafood.api.V1.model.RestaurantOrderSummaryModel;
import com.philippo.algafood.api.V1.model.input.RestaurantOrderInput;
import com.philippo.algafood.core.springdoc.PageableParameter;
import com.philippo.algafood.domain.filter.OrderFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Orders")
public interface RestaurantOrderControllerOpenApi {

    @Operation(
            summary = "Search for orders",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "clientId",
                            description = "Client ID for search filter",
                            example = "1", schema = @Schema(type = "integer")),
                    @Parameter(in = ParameterIn.QUERY, name = "restaurantId",
                            description = "Restaurant ID for search filter",
                            example = "1", schema = @Schema(type = "integer")),
                    @Parameter(in = ParameterIn.QUERY, name = "startCreationDate",
                            description = "Initial creation date/time for search filter",
                            example = "2025-03-19T00:00:00Z", schema = @Schema(type = "string", format = "date-time")),
                    @Parameter(in = ParameterIn.QUERY, name = "endCreationDate",
                            description = "Final creation date/time for search filter",
                            example = "2025-03-20T23:59:59Z", schema = @Schema(type = "string", format = "date-time"))
            }
    )
    @PageableParameter
    PagedModel<RestaurantOrderSummaryModel> search(@Parameter(hidden = true) Pageable pageable,
                                                   @Parameter(hidden = true) OrderFilter filter);

    @Operation(summary = "Find an order by code", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Order not found", content = {
                    @Content(schema = @Schema(ref = "Problem"))}),
    })
    RestaurantOrderModel find(@Parameter(
            description = "Order code", example = "04813f77-79b5-11ec-9a17-0242ac1b0002", required = true) String orderUuid);

    @Operation(summary = "Register an order", responses = {
            @ApiResponse(responseCode = "201", description = "Registered order"),
    })
    RestaurantOrderModel add(@RequestBody(description = "Representation of a new order", required = true) RestaurantOrderInput orderInput);
}
