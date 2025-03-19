package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.ProductPhotoModel;
import com.philippo.algafood.api.V1.model.input.ProductPhotoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Products")
public interface RestaurantProductPhotoControllerOpenApi {

    @Operation(summary = "Update a restaurant product photo")
    ProductPhotoModel updatePhoto(
            @Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
            @Parameter(description = "Restaurant product ID", example = "1", required = true) Long productId,
            @RequestBody(required = true) ProductPhotoInput productPhotoInput) throws IOException;

    void delete(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
                @Parameter(description = "Product ID", example = "1", required = true) Long productId);

    @Operation(summary = "Find a restaurant product photo", responses = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductPhotoModel.class)),
                    @Content(mediaType = "image/jpeg", schema = @Schema(type = "string", format = "binary")),
                    @Content(mediaType = "image/png", schema = @Schema(type = "string", format = "binary"))
            })
    })
    ProductPhotoModel find(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
                           @Parameter(description = "Product ID", example = "1", required = true) Long productId);

    @Operation(hidden = true)
    ResponseEntity<?> servePhoto(@Parameter(description = "Restaurant ID", example = "1", required = true) Long restaurantId,
                                 @Parameter(description = "Product ID", example = "1", required = true) Long productId,
                                 String acceptHeader) throws HttpMediaTypeNotAcceptableException;

}
