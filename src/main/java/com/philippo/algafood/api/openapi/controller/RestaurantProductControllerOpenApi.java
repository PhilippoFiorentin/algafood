package com.philippo.algafood.api.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.model.ProductModel;
import com.philippo.algafood.api.model.input.ProductInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;

@Api(tags = "Products")
public interface RestaurantProductControllerOpenApi {

    @ApiOperation("List the restaurant products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    description = "Invalid restaurant ID",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Restaurant not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    CollectionModel<ProductModel> list(@ApiParam(value = "ID of a restaurant", example = "1", required = true) Long restaurantId,
                                       @ApiParam(value = "Indicates whether or not to include inactive products in the listing result",
                                    example = "false", defaultValue = "false") Boolean addInactives);

    @ApiOperation("Search for a restaurant product ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    description = "Invalid product or restaurant ID",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Product or restaurant not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ProductModel find(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long restaurantId,
                      @ApiParam(value = "Restaurant ID", example = "1", required = true) Long productId);

    @ApiOperation("Register a restaurant product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Product or restaurant not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ProductModel add(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long restaurantId,
                     @ApiParam(name = "body", value = "Representation of a new product", required = true) ProductInput productInput);

    @ApiOperation("Update a restaurant product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Product or restaurant not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ProductModel update(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long restaurantId,
                               @ApiParam(value = "Product ID", example = "1", required = true) Long productId,
                               @ApiParam(name = "body", value = "Representation of a new product with new data", required = true) ProductInput productInput);
}
