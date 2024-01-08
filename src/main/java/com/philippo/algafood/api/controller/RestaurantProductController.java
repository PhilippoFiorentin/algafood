package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.ProductModelAssembler;
import com.philippo.algafood.api.model.ProductModel;
import com.philippo.algafood.domain.model.Product;
import com.philippo.algafood.domain.model.Restaurant;
import com.philippo.algafood.domain.repository.ProductRepository;
import com.philippo.algafood.domain.service.RegisterProductService;
import com.philippo.algafood.domain.service.RegisterRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products")
public class RestaurantProductController {

	@Autowired
	private RegisterRestaurantService registerRestaurant;

	@Autowired
	private ProductModelAssembler productModelAssembler;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private RegisterProductService registerProduct;

	@GetMapping
	public List<ProductModel> list(@PathVariable Long restaurantId) {
		Restaurant restaurant = registerRestaurant.findOrFail(restaurantId);

		List<Product> allProducts = productRepository.findByRestaurant(restaurant);

		return productModelAssembler.toCollectionModel(allProducts);
	}

	@GetMapping("/{productId}")
	public ProductModel findProduct(@PathVariable Long restaurantId, @PathVariable Long productId) {
		Product product = registerProduct.findOrFail(restaurantId, productId);

		return productModelAssembler.toModel(product);
	}

}