package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.ProductPhotoModel;
import com.philippo.algafood.api.V1.model.input.ProductPhotoInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface RestaurantProductPhotoControllerOpenApi {

    ProductPhotoModel updatePhoto(Long restaurantId, Long productId, ProductPhotoInput productPhotoInput,
                                  MultipartFile file) throws IOException;

    void delete(Long restaurantId, Long productId);

    ProductPhotoModel find(Long restaurantId, Long productId);

    ResponseEntity<?> servePhoto(Long restaurantId, Long productId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;

}
