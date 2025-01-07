package com.philippo.algafood.api.v1.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.v1.model.ProductPhotoModel;
import com.philippo.algafood.api.v1.model.input.ProductPhotoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "Products")
public interface RestaurantProductPhotoControllerOpenApi {

    @ApiOperation("Update a restaurant product photo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "Restaurant product not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ProductPhotoModel updatePhoto(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long restaurantId,
                                  @ApiParam(value = "Product ID", example = "1", required = true) Long productId,
                                  ProductPhotoInput productPhotoInput,
                                  @ApiParam(hidden = true) MultipartFile file) throws IOException;

    @ApiOperation("Delete a restaurant product photo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    description = "Invalid product or restaurant ID",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Restaurant product photo not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    void delete(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long restaurantId,
                @ApiParam(value = "Product ID", example = "1", required = true) Long productId);

    @ApiOperation(value = "Find a restaurant product photo", produces = "application/json, image/jpeg, image/png")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    description = "Invalid product or restaurant ID",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "Restaurant product photo not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    ProductPhotoModel find(@ApiParam(value = "Restaurant ID", example = "1", required = true) Long restaurantId,
                           @ApiParam(value = "Product ID", example = "1", required = true) Long productId);

    @ApiOperation(value = "Find a restaurant product photo", hidden = true)
    ResponseEntity<?> servePhoto(Long restaurantId, Long productId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;

}
