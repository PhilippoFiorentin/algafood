package com.philippo.algafood.api.V1.openapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Orders")
public interface OrderFlowControllerOpenApi {

    @Operation(summary = "Order confirmation", responses = {
            @ApiResponse(responseCode = "204", description = "Order confirmed successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ResponseEntity<Void> confirm(
            @Parameter(description = "Order code", example = "04813f77-79b5-11ec-9a17-0242ac1b0002", required = true) String orderUuid);

    @Operation(summary = "Register order delivery", responses = {
            @ApiResponse(responseCode = "204", description = "Order delivered successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ResponseEntity<Void> deliver(
            @Parameter(description = "Order code", example = "04813f77-79b5-11ec-9a17-0242ac1b0002", required = true) String orderUuid);

    @Operation(summary = "Order cancellation", responses = {
            @ApiResponse(responseCode = "204", description = "Order cancelled successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ResponseEntity<Void> cancel(
            @Parameter(description = "Order code", example = "04813f77-79b5-11ec-9a17-0242ac1b0002", required = true) String orderUuid);
}
