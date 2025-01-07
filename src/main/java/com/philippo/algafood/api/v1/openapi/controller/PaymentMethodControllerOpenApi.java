package com.philippo.algafood.api.v1.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.v1.model.PaymentMethodModel;
import com.philippo.algafood.api.v1.model.input.PaymentMethodInput;
import com.philippo.algafood.api.v1.openapi.model.PaymentMethodsModelOpenApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

@Api(tags = "Payment methods")
public interface PaymentMethodControllerOpenApi {

    @ApiOperation(value = "List payment methods", response = PaymentMethodsModelOpenApi.class)
    ResponseEntity<CollectionModel<PaymentMethodModel>> list(ServletWebRequest request);

    @ApiOperation("Search a payment method by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    description = "Invalid payment method ID",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Payment method not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ResponseEntity<PaymentMethodModel> find(@ApiParam(value = "ID of a payment method", example = "1", required = true) Long paymentMethodId,
                                                   ServletWebRequest request);

    @ApiOperation("Register a payment method")
    PaymentMethodModel add(@ApiParam(name = "body", value = "Representation of a new payment method", required = true) PaymentMethodInput paymentMethodInput);

    @ApiOperation("Update a payment method by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Payment method not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    PaymentMethodModel update(@ApiParam(value = "ID of a payment method", example = "1", required = true) Long paymentMethodId,
                                     @ApiParam(name = "body", value = "representation of a payment method with the new data", required = true) PaymentMethodInput paymentMethodInput);


    @ApiOperation("Delete a payment method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Payment method not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    void delete(@ApiParam(value = "ID of a payment method", example = "1", required = true) Long paymentMethodId);

}
