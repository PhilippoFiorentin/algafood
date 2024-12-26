package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.ProductPhotoModelAssembler;
import com.philippo.algafood.api.model.ProductPhotoModel;
import com.philippo.algafood.api.model.input.ProductPhotoInput;
import com.philippo.algafood.api.openapi.controller.RestaurantProductPhotoControllerOpenApi;
import com.philippo.algafood.domain.exception.EntityNotFoundException;
import com.philippo.algafood.domain.model.Product;
import com.philippo.algafood.domain.model.ProductPhoto;
import com.philippo.algafood.domain.service.PhotoStorageService;
import com.philippo.algafood.domain.service.ProductPhotoCatalogService;
import com.philippo.algafood.domain.service.RegisterProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/products/{productId}/photo",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantProductPhotoController implements RestaurantProductPhotoControllerOpenApi {

    @Autowired
    private RegisterProductService registerProductService;

    @Autowired
    private PhotoStorageService photoStorageService;

    @Autowired
    private ProductPhotoCatalogService productPhotoCatalogService;

    @Autowired
    private ProductPhotoModelAssembler productPhotoModelAssembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductPhotoModel updatePhoto(@PathVariable Long restaurantId, @PathVariable Long productId,
                                         @Valid ProductPhotoInput productPhotoInput,
                                         @RequestPart(required = true) MultipartFile file) throws IOException {

        Product product = registerProductService.findOrFail(restaurantId, productId);

//        MultipartFile file = productPhotoInput.getFile();

        ProductPhoto photo = new ProductPhoto();
        photo.setProduct(product);
        photo.setDescription(productPhotoInput.getDescription());
        photo.setContentType(file.getContentType());
        photo.setSize(file.getSize());
        photo.setFilename(file.getOriginalFilename());

        ProductPhoto savedPhoto = productPhotoCatalogService.save(photo, file.getInputStream());

        return productPhotoModelAssembler.toModel(savedPhoto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductPhotoModel find(@PathVariable Long restaurantId, @PathVariable Long productId){
        ProductPhoto photo = productPhotoCatalogService.findOrFail(restaurantId, productId);

        return productPhotoModelAssembler.toModel(photo);
    }

    @GetMapping(produces = MediaType.ALL_VALUE)
    public ResponseEntity<?> servePhoto(@PathVariable Long restaurantId,
                                                          @PathVariable Long productId,
                                                          @RequestHeader(name = "accept") String acceptHeader)
            throws HttpMediaTypeNotAcceptableException {

        try {
            ProductPhoto photo = productPhotoCatalogService.findOrFail(restaurantId, productId);

            MediaType mediaTypePhoto = MediaType.parseMediaType(photo.getContentType());
            List<MediaType> acceptedMediaTypes = MediaType.parseMediaTypes(acceptHeader);

            verifyMediaTypeCompatibility(mediaTypePhoto, acceptedMediaTypes);

            PhotoStorageService.RecoveredPhoto recoveredPhoto = photoStorageService.recover(photo.getFilename());

            if (recoveredPhoto.hasUrl()){
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .header(HttpHeaders.LOCATION, recoveredPhoto.getUrl())
                        .build();
            } else{
                return ResponseEntity
                        .ok()
                        .contentType(mediaTypePhoto)
                        .body(new InputStreamResource(recoveredPhoto.getInputStream()));
            }

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long restaurantId, @PathVariable Long productId) {
        productPhotoCatalogService.delete(restaurantId, productId);
    }

    private void verifyMediaTypeCompatibility(MediaType mediaTypePhoto, List<MediaType> acceptedMediaTypes)
            throws HttpMediaTypeNotAcceptableException {

        boolean compatible = acceptedMediaTypes
                .stream()
                .anyMatch(acceptedMediaType -> acceptedMediaType.isCompatibleWith(mediaTypePhoto));

        if (!compatible) {
            throw new HttpMediaTypeNotAcceptableException(acceptedMediaTypes);
        }
    }
}
