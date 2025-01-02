package com.philippo.algafood.api.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.model.RestaurantOrderModel;
import com.philippo.algafood.api.model.RestaurantOrderSummaryModel;
import com.philippo.algafood.api.model.input.RestaurantOrderInput;
import com.philippo.algafood.domain.filter.OrderFilter;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@Api(tags = "Restaurant orders")
public interface RestaurantOrderControllerOpenApi {

    @ApiOperation("Search the orders")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Names of properties to filter on in the response, separated by commas",
                    name = "fields", paramType = "query", type = "string")})
    PagedModel<RestaurantOrderSummaryModel> search(Pageable pageable, OrderFilter filter);

    @ApiOperation("Search a restaurant order by ID")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "Names of properties to filter on in the response, separated by commas",
                    name = "fields", paramType = "query", type = "string")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant order not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    RestaurantOrderModel find(
            @ApiParam(value = "ID of a restaurant order", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true) String orderUuid);

    @ApiOperation("Register a restaurant order")
    RestaurantOrderModel add(
            @ApiParam(name = "body", value = "Representation of a new restaurant order", required = true) RestaurantOrderInput orderInput);
}
