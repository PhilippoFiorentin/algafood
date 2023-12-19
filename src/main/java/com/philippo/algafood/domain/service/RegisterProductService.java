package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.exception.ProductNotFoundException;
import com.philippo.algafood.domain.model.Product;
import com.philippo.algafood.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public Product save(Product product) {
		return productRepository.save(product);
	}

	public Product findOrFail(Long restaurantId, Long productId){
		return productRepository
				.findById(restaurantId, productId)
				.orElseThrow(() -> new ProductNotFoundException(restaurantId, productId));
	}
}
