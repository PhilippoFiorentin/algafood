package com.philippo.algafood.api.V1.assembler;

import com.philippo.algafood.api.V1.AlgaLinks;
import com.philippo.algafood.api.V1.controller.RestaurantProductPhotoController;
import com.philippo.algafood.api.V1.model.ProductPhotoModel;
import com.philippo.algafood.core.security.AlgaSecurity;
import com.philippo.algafood.domain.model.ProductPhoto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProductPhotoModelAssembler extends RepresentationModelAssemblerSupport<ProductPhoto, ProductPhotoModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public ProductPhotoModelAssembler() {
        super(RestaurantProductPhotoController.class, ProductPhotoModel.class);
    }

    @Override
    public ProductPhotoModel toModel(ProductPhoto photo) {
        ProductPhotoModel productPhotoModel = modelMapper.map(photo, ProductPhotoModel.class);

        if (algaSecurity.canConsultRestaurants()){
            productPhotoModel.add(algaLinks.linkToProductPhoto(photo.getRestaurantId(), photo.getProduct().getId()));
            productPhotoModel.add(algaLinks.linkToProduct(photo.getRestaurantId(), photo.getProduct().getId(), "product"));
        }

        return productPhotoModel;
    }
}
