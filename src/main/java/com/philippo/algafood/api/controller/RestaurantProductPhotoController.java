package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.model.input.ProductPhotoInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updatePhoto(@PathVariable Long restaurantId, @PathVariable Long productId,
                            @Valid ProductPhotoInput productPhotoInput){

        var fileName = UUID.randomUUID().toString() + "_" + productPhotoInput.getFile().getOriginalFilename();
        var photoFile = Path.of("/Users/philipporodrigues/Desktop/catalog", fileName);

        System.out.println(productPhotoInput.getDescription());
        System.out.println(photoFile);
        System.out.println(productPhotoInput.getFile().getContentType());

        try {
            productPhotoInput.getFile().transferTo(photoFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
