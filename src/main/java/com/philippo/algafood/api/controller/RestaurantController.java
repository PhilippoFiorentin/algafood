package com.philippo.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.philippo.algafood.api.model.KitchenModel;
import com.philippo.algafood.api.model.RestaurantModel;
import com.philippo.algafood.core.validation.ValidationException;
import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.KitchenNotFoundException;
import com.philippo.algafood.domain.model.Restaurant;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import com.philippo.algafood.domain.repository.RestaurantRepository;
import com.philippo.algafood.domain.service.RegisterRestaurantService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private RegisterRestaurantService registerRestaurant;

	@Autowired
	private SmartValidator validator;

	@GetMapping
	public List<RestaurantModel> listAllRestaurants(){
		return toCollectionModel(restaurantRepository.findAll());
	}

	@GetMapping("/{restaurantId}")
	public RestaurantModel findRestaurant(@PathVariable Long restaurantId) {
		Restaurant restaurant = registerRestaurant.findOrFail(restaurantId);

		return toModel(restaurant);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestaurantModel addRestaurant(@RequestBody @Valid Restaurant restaurant) {
		try{
			return toModel(registerRestaurant.save(restaurant));
		} catch (KitchenNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PutMapping("/{restaurantId}")
	public RestaurantModel updateRestaurant(@PathVariable Long restaurantId, @RequestBody @Valid Restaurant restaurant){

		try{
			Restaurant currentRestaurant = registerRestaurant.findOrFail(restaurantId);

			BeanUtils.copyProperties(restaurant,
						currentRestaurant,
						"id", "paymentMethods", "address", "registerDate", "products");

			return toModel(registerRestaurant.save(currentRestaurant));
		} catch (KitchenNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{restaurantId}")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void delete(@PathVariable Long restaurantId){
		registerRestaurant.delete(restaurantId);
	}


//	@PatchMapping("/{restaurantId}")
//	public RestaurantModel updatePartial(
//			@PathVariable Long restaurantId, @RequestBody Map<String, Object> fields, HttpServletRequest request) {
//
//		Restaurant currentRestaurant = registerRestaurant.findOrFail(restaurantId);
//
//		merge(fields, currentRestaurant, request);
//
//		validate(currentRestaurant, "restaurant");
//
//		return updateRestaurant(restaurantId, currentRestaurant);
//	}

	private void validate(Restaurant restaurant, String objectName) {
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurant, objectName);

		validator.validate(restaurant, bindingResult);

		if (bindingResult.hasErrors()) {
			throw new ValidationException(bindingResult);

		}
	}

	/*

	O método merge irá converter os tipos dos valores, de acordo com o que
	está implementado na entidade declarada.

	Merge method will convert all value types, according to what is implemented
	in the declared entity.

	Depois, irá buscar as propriedades do objeto e setar o(s) novo(s) valor(es) para a
	propriedade específica que está sendo alterada.

	Then the merge method will find the object props and set the new values
	for the specific prop that is being changed.

	*/

//	public void merge(Map<String, Object> sourceData, Restaurant targetRestaurant, HttpServletRequest request) {
//
//		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
//
//		try {
//			ObjectMapper objectMapper = new ObjectMapper();
//			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
//			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
//
//			RestaurantModel sourceRestaurant = objectMapper.convertValue(sourceData, RestaurantModel.class);
//
//
//			sourceData.forEach((propName, propValue) -> {
//				Field field = ReflectionUtils.findField(Restaurant.class, propName);
//
//				field.setAccessible(true);
//
//				Object newValue = ReflectionUtils.getField(field, sourceRestaurant);
//
//				ReflectionUtils.setField(field, targetRestaurant, newValue);
//			});
//
//		} catch (IllegalArgumentException e){
//			Throwable rootCause = ExceptionUtils.getRootCause(e);
//
//			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
//		}
//	}

	private RestaurantModel toModel(Restaurant restaurant) {
		KitchenModel kitchenModel = new KitchenModel();
		kitchenModel.setId(restaurant.getKitchen().getId());
		kitchenModel.setName(restaurant.getKitchen().getName());

		RestaurantModel restaurantModel = new RestaurantModel();
		restaurantModel.setId(restaurant.getId());
		restaurantModel.setName(restaurant.getName());
		restaurantModel.setDeliveryFee(restaurant.getDeliveryFee());
		restaurantModel.setKitchen(kitchenModel);
		return restaurantModel;
	}

	private List<RestaurantModel> toCollectionModel(List<Restaurant> restaurants){
		return restaurants.stream()
				.map(restaurant -> toModel(restaurant))
				.collect(Collectors.toList());
	}
}
