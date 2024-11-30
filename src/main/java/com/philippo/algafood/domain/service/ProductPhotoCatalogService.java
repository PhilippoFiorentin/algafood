package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.exception.ProductPhotoNotFoundException;
import com.philippo.algafood.domain.model.ProductPhoto;
import com.philippo.algafood.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;


@Service
public class ProductPhotoCatalogService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PhotoStorageService photoStorageService;

    @Transactional
    public ProductPhoto save(ProductPhoto photo, InputStream fileData) {
        Long restaurantId  = photo.getRestaurantId();
        Long productId = photo.getProduct().getId();
        String newFilename = photoStorageService.generateFilename(photo.getFilename());
        String existingFilename = null;

        Optional<ProductPhoto> currentPhoto = productRepository.findPhotoById(restaurantId, productId);

        if (currentPhoto.isPresent()){
            existingFilename = currentPhoto.get().getFilename();
            productRepository.delete(currentPhoto.get());
        }

        photo.setFilename(newFilename);
        photo = productRepository.save(photo);
        productRepository.flush();

        PhotoStorageService.NewPhoto newPhoto = PhotoStorageService.NewPhoto.builder()
                                                                .filename(photo.getFilename())
                                                                .contentType(photo.getContentType())
                                                                .inputStream(fileData)
                                                                .build();


        photoStorageService.substitute(existingFilename, newPhoto);

        return photo;
    }

    public void delete(Long restaurantId, Long productId) {
            ProductPhoto photo = findOrFail(restaurantId, productId);

            productRepository.delete(photo);
            productRepository.flush();

            photoStorageService.delete(photo.getFilename());
    }

    public ProductPhoto findOrFail(Long restaurantId, Long productId){
        return productRepository
                .findPhotoById(restaurantId, productId)
                .orElseThrow(() -> new ProductPhotoNotFoundException(restaurantId, productId));
    }
}
