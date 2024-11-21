package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.model.ProductPhoto;
import com.philippo.algafood.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductPhotoCatalogService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ProductPhoto save(ProductPhoto photo){
        return productRepository.save(photo);
    }
}
