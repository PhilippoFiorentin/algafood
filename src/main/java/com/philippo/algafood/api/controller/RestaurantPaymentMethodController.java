package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.AlgaLinks;
import com.philippo.algafood.api.assembler.PaymentMethodModelAssembler;
import com.philippo.algafood.api.model.PaymentMethodModel;
import com.philippo.algafood.api.openapi.controller.RestaurantPaymentMethodControllerOpenApi;
import com.philippo.algafood.core.security.AlgaSecurity;
import com.philippo.algafood.core.security.CheckSecurity;
import com.philippo.algafood.domain.model.Restaurant;
import com.philippo.algafood.domain.service.RegisterRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/payment-methods", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantPaymentMethodController implements RestaurantPaymentMethodControllerOpenApi {

	@Autowired
	private RegisterRestaurantService registerRestaurant;

	@Autowired
	private PaymentMethodModelAssembler paymentMethodModelAssembler;

	@Autowired
	private AlgaLinks algaLinks;

	@Autowired
	private AlgaSecurity algaSecurity;

	@CheckSecurity.Restaurants.CanConsult
	@GetMapping
	public CollectionModel<PaymentMethodModel> list(@PathVariable Long restaurantId){
		Restaurant restaurant = registerRestaurant.findOrFail(restaurantId);
		CollectionModel<PaymentMethodModel> paymentMethodModels = paymentMethodModelAssembler.
				toCollectionModel(restaurant.getPaymentMethods()).removeLinks();

		paymentMethodModels.add(algaLinks.linkToRestaurantPaymentMethods(restaurantId));

		if (algaSecurity.canConsultPaymentMethods()) {
			paymentMethodModels.add(algaLinks.linkToAffiliateRestaurantPaymentMethods(restaurantId, "affiliate"));
		}


		paymentMethodModels.getContent().forEach(paymentMethodModel -> {
					paymentMethodModel.add(algaLinks.linkToDisaffiliateRestaurantPaymentMethods(
									restaurantId, paymentMethodModel.getId(), "disaffiliate"));
				});

		return paymentMethodModels;
	}

	@CheckSecurity.Restaurants.CanManageOperation
	@DeleteMapping("/{paymentMethodId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> disaffiliate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId){
		registerRestaurant.disaffiliatePaymentMethod(restaurantId, paymentMethodId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Restaurants.CanManageOperation
	@PutMapping("/{paymentMethodId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> affiliate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId){
		registerRestaurant.affiliatePaymentMethod(restaurantId, paymentMethodId);

		return ResponseEntity.noContent().build();
	}
}
