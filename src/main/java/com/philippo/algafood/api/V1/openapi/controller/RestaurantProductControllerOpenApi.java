package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.ProductModel;
import com.philippo.algafood.api.V1.model.input.ProductInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Products")
public interface RestaurantProductControllerOpenApi {

    @Operation(summary = "List all products of a restaurant", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid restaurant ID", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    CollectionModel<ProductModel> list(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
                                       @Parameter(description = "Include inactive", example = "false", required = false) Boolean addInactives);

    @Operation(summary = "Find a product by ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid restaurant ID", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ProductModel find(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
                      @Parameter(description = "Product ID", example = "1", required = true) Long productId);

    @Operation(summary = "Register a product from a restaurant", responses = {
            @ApiResponse(responseCode = "201", description = "Registered product"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ProductModel add(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
                     @RequestBody(description = "Representation of a new product", required = true) ProductInput productInput);

    @Operation(summary = "Updates a restaurant product", responses = {
            @ApiResponse(responseCode = "200", description = "Updated product"),
            @ApiResponse(responseCode = "404", description = "Restaurant product not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ProductModel update(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
                        @Parameter(description = "Product ID", example = "1", required = true) Long productId,
                        @RequestBody(description = "Representation of a product with new data", required = true) ProductInput productInput);
}
