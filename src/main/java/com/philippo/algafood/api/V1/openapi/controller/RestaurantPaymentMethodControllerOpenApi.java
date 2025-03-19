package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.PaymentMethodModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Restaurants")
public interface RestaurantPaymentMethodControllerOpenApi {

    @Operation(summary = "List all payment methods associated to a restaurant", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    CollectionModel<PaymentMethodModel> list(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId);

    @Operation(summary = "Disassociation of restaurant with payment method", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ResponseEntity<Void> disaffiliate(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
                                      @Parameter(description = "Payment method ID", example = "1", required = true) Long paymentMethodId);

    @Operation(summary = "Association of restaurant with payment method", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ResponseEntity<Void> affiliate(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
                                   @Parameter(description = "Payment method ID", example = "1", required = true) Long paymentMethodId);

}
