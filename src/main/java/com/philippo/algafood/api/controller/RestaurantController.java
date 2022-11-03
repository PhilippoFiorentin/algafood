package com.philippo.algafood.api.controller;

import java.util.List;

import com.philippo.algafood.api.assembler.RestaurantModelAssembler;
import com.philippo.algafood.api.model.RestaurantModel;
import com.philippo.algafood.api.model.input.RestaurantInput;
import com.philippo.algafood.core.validation.ValidationException;
import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.KitchenNotFoundException;
import com.philippo.algafood.domain.model.Kitchen;
import com.philippo.algafood.domain.model.Restaurant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import com.philippo.algafood.domain.repository.RestaurantRepository;
import com.philippo.algafood.domain.service.RegisterRestaurantService;

import javax.validation.Valid;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private RegisterRestaurantService registerRestaurant;

	@Autowired
	private RestaurantModelAssembler restaurantModelAssembler;

	@Autowired
	private SmartValidator validator;

	@GetMapping
	public List<RestaurantModel> listAllRestaurants(){
		return restaurantModelAssembler.toCollectionModel(restaurantRepository.findAll());
	}

	@GetMapping("/{restaurantId}")
	public RestaurantModel findRestaurant(@PathVariable Long restaurantId) {
		Restaurant restaurant = registerRestaurant.findOrFail(restaurantId);

		return restaurantModelAssembler.toModel(restaurant);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestaurantModel addRestaurant(@RequestBody @Valid RestaurantInput restaurantInput) {
		try{
			Restaurant restaurant = toDomainObject(restaurantInput);
			return restaurantModelAssembler.toModel(registerRestaurant.save(restaurant));
		} catch (KitchenNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PutMapping("/{restaurantId}")
	public RestaurantModel updateRestaurant(@PathVariable Long restaurantId, @RequestBody @Valid RestaurantInput restaurantInput){

		try{
			Restaurant restaurant = toDomainObject(restaurantInput);
			Restaurant currentRestaurant = registerRestaurant.findOrFail(restaurantId);

			BeanUtils.copyProperties(restaurant,
						currentRestaurant,
						"id", "paymentMethods", "address", "registerDate", "products");

			return restaurantModelAssembler.toModel(registerRestaurant.save(currentRestaurant));
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

	private Restaurant toDomainObject(RestaurantInput restaurantInput){
		Restaurant restaurant = new Restaurant();
		restaurant.setName(restaurantInput.getName());
		restaurant.setDeliveryFee(restaurantInput.getDeliveryFee());

		Kitchen kitchen = new Kitchen();
		kitchen.setId(restaurantInput.getKitchen().getId());

		restaurant.setKitchen(kitchen);

		return restaurant;
	}
}
