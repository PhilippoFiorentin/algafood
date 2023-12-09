package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.ProductModelAssembler;
import com.philippo.algafood.domain.service.RegisterRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products")
public class RestaurantProductController {

	@Autowired
	private RegisterRestaurantService registerRestaurant;

	@Autowired
	private ProductModelAssembler productModelAssembler;

//	@GetMapping
//	public RestaurantModel list(@PathVariable Long restaurantId) {
//		Restaurant restaurant = registerRestaurant.findOrFail(restaurantId);
//
//		return (RestaurantModel) productModelAssembler.toCollectionModel(restaurant.getProducts());
//	}

}