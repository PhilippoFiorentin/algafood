package com.philippo.algafood.api.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.model.PaymentMethodModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Api(tags = "Restaurant")
public interface RestaurantPaymentMethodControllerOpenApi {

    @ApiOperation("List the payment methods associated with the restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    List<PaymentMethodModel> list(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId);

    @ApiOperation("Disassociation of restaurant with payment method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    void disaffiliate(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId,
                      @ApiParam(value = "ID of a payment method", example = "1", required = true) Long paymentMethodId);

    @ApiOperation("Association of restaurant with payment method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    void affiliate(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId,
                   @ApiParam(value = "ID of a payment method", example = "1", required = true) Long paymentMethodId);

}
