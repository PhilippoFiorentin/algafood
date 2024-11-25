package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.ProductPhotoModelAssembler;
import com.philippo.algafood.api.model.ProductPhotoModel;
import com.philippo.algafood.api.model.input.ProductPhotoInput;
import com.philippo.algafood.domain.model.Product;
import com.philippo.algafood.domain.model.ProductPhoto;
import com.philippo.algafood.domain.service.ProductPhotoCatalogService;
import com.philippo.algafood.domain.service.RegisterProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @Autowired
    private RegisterProductService registerProductService;

    @Autowired
    private ProductPhotoCatalogService productPhotoCatalogService;

    @Autowired
    private ProductPhotoModelAssembler productPhotoModelAssembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductPhotoModel updatePhoto(@PathVariable Long restaurantId, @PathVariable Long productId,
                                         @Valid ProductPhotoInput productPhotoInput) throws IOException {

        Product product = registerProductService.findOrFail(restaurantId, productId);

        MultipartFile file = productPhotoInput.getFile();

        ProductPhoto photo = new ProductPhoto();
        photo.setProduct(product);
        photo.setDescription(productPhotoInput.getDescription());
        photo.setContentType(file.getContentType());
        photo.setSize(file.getSize());
        photo.setFilename(file.getOriginalFilename());

        ProductPhoto savedPhoto = productPhotoCatalogService.save(photo, file.getInputStream());

        return productPhotoModelAssembler.toModel(savedPhoto);
    }

    @GetMapping
    public ProductPhotoModel find(@PathVariable Long restaurantId, @PathVariable Long productId){
        ProductPhoto photo = productPhotoCatalogService.findOrFail(restaurantId, productId);

        return productPhotoModelAssembler.toModel(photo);
    }
}
