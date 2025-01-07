package com.philippo.algafood.api.v1.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api(tags = "Restaurant orders")
public interface OrderFlowControllerOpenApi {

    @ApiOperation("Confirm order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Order not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ResponseEntity<Void> confirm(@ApiParam(value = "ID of an order", example = "9981ca4-5a5e-4da3-af04-933861df3e55", required = true) String orderUuid);

    @ApiOperation("Register order delivery")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Order not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ResponseEntity<Void> deliver(@ApiParam(value = "ID of an order", example = "9981ca4-5a5e-4da3-af04-933861df3e55", required = true) String orderUuid);

    @ApiOperation("Cancel order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Order not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ResponseEntity<Void> cancel(@ApiParam(value = "ID of an order", example = "9981ca4-5a5e-4da3-af04-933861df3e55", required = true) String orderUuid);
}
