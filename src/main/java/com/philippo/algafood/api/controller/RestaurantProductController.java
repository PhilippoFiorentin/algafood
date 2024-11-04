package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.ProductInputDisassembler;
import com.philippo.algafood.api.assembler.ProductModelAssembler;
import com.philippo.algafood.api.model.ProductModel;
import com.philippo.algafood.api.model.input.ProductInput;
import com.philippo.algafood.domain.model.Product;
import com.philippo.algafood.domain.model.Restaurant;
import com.philippo.algafood.domain.repository.ProductRepository;
import com.philippo.algafood.domain.service.RegisterProductService;
import com.philippo.algafood.domain.service.RegisterRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products")
public class RestaurantProductController {

	@Autowired
	private RegisterRestaurantService registerRestaurant;

	@Autowired
	private ProductModelAssembler productModelAssembler;

	@Autowired
	private ProductInputDisassembler productInputDisassembler;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private RegisterProductService registerProduct;

	@GetMapping
	public List<ProductModel> list(@PathVariable Long restaurantId, @RequestParam(required = false) boolean addInactives) {
		Restaurant restaurant = registerRestaurant.findOrFail(restaurantId);

		List<Product> allProducts = null;

		if(addInactives){
			allProducts = productRepository.findAllProductsByRestaurant(restaurant);
		} else{
			allProducts = productRepository.findActivesByRestaurant(restaurant);
		}


		return productModelAssembler.toCollectionModel(allProducts);
	}

	@GetMapping("/{productId}")
	public ProductModel find(@PathVariable Long restaurantId, @PathVariable Long productId) {
		Product product = registerProduct.findOrFail(restaurantId, productId);

		return productModelAssembler.toModel(product);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductModel add(@PathVariable Long restaurantId,
									  @RequestBody @Valid ProductInput productInput) {
		Restaurant restaurant = registerRestaurant.findOrFail(restaurantId);
		Product product = productInputDisassembler.toDomainObject(productInput);

		product.setRestaurant(restaurant);
		product = registerProduct.save(product);

		return productModelAssembler.toModel(product);
	}

	@PutMapping("/{productId}")
	public ProductModel update(@PathVariable Long restaurantId,
									  @PathVariable Long productId,
									  @RequestBody @Valid ProductInput productInput){

			Product currentProduct = registerProduct.findOrFail(restaurantId, productId);

			productInputDisassembler.copyToDomainObject(productInput, currentProduct);

			currentProduct = registerProduct.save(currentProduct);

			return productModelAssembler.toModel(currentProduct);
	}

}