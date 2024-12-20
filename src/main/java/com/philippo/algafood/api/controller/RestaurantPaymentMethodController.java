package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.PaymentMethodModelAssembler;
import com.philippo.algafood.api.model.PaymentMethodModel;
import com.philippo.algafood.api.openapi.controller.RestaurantPaymentMethodControllerOpenApi;
import com.philippo.algafood.domain.model.Restaurant;
import com.philippo.algafood.domain.service.RegisterRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/payment-methods", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantPaymentMethodController implements RestaurantPaymentMethodControllerOpenApi {

	@Autowired
	private RegisterRestaurantService registerRestaurant;

	@Autowired
	private PaymentMethodModelAssembler paymentMethodModelAssembler;

	@GetMapping
	public List<PaymentMethodModel> list(@PathVariable Long restaurantId){
		Restaurant restaurant = registerRestaurant.findOrFail(restaurantId);
		return paymentMethodModelAssembler.toCollectionModel(restaurant.getPaymentMethods());
	}

	@DeleteMapping("/{paymentMethodId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void disaffiliate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId){
		registerRestaurant.disaffiliatePaymentMethod(restaurantId, paymentMethodId);
	}

	@PutMapping("/{paymentMethodId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void affiliate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId){
		registerRestaurant.affiliatePaymentMethod(restaurantId, paymentMethodId);
	}

}
