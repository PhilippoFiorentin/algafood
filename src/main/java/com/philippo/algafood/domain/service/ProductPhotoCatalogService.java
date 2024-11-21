package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.model.ProductPhoto;
import com.philippo.algafood.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class ProductPhotoCatalogService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductPhoto save(ProductPhoto photo){
        Long restaurantId  = photo.getRestaurantId();
        Long productId = photo.getProduct().getId();

        Optional<ProductPhoto> currentPhoto = productRepository.findPhotoById(restaurantId, productId);

        if (currentPhoto.isPresent())
            productRepository.delete(currentPhoto.get());

        return productRepository.save(photo);
    }
}
