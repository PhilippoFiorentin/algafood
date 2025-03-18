package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.PaymentMethodModel;
import com.philippo.algafood.api.V1.model.input.PaymentMethodInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Payment Methods")
public interface PaymentMethodControllerOpenApi {

    @Operation(summary = "List payment methods")
    ResponseEntity<CollectionModel<PaymentMethodModel>> list(@Parameter(hidden = true) ServletWebRequest request);

    @Operation(summary = "Find a payment method by ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid payment method ID", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
            @ApiResponse(responseCode = "404", description = "Payment method not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) })
    })
    ResponseEntity<PaymentMethodModel> find(@Parameter(description = "Payment method ID", example = "1", required = true) Long paymentMethodId,
                                            @Parameter(hidden = true) ServletWebRequest request);

    @Operation(summary = "Register a payment method", responses = {
            @ApiResponse(responseCode = "201", description = "Registered payment method")})
    PaymentMethodModel add(@RequestBody(description = "Representation of a new payment method", required = true) PaymentMethodInput paymentMethodInput);

    @Operation(summary = "Update a payment method by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Updated payment method"),
            @ApiResponse(responseCode = "404", description = "Payment method not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) })
    })
    PaymentMethodModel update(@Parameter(description = "Payment method ID", example = "1", required = true) Long paymentMethodId,
                              @RequestBody(description = "Representation of a new payment method", required = true) PaymentMethodInput paymentMethodInput);

    @Operation(summary = "Delete a payment method by ID", responses = {
            @ApiResponse(responseCode = "204", description = "Deleted payment method"),
            @ApiResponse(responseCode = "404", description = "Payment method not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) })
    })
    void delete(@Parameter(description = "Payment method ID", example = "1", required = true) Long paymentMethodId);

}
