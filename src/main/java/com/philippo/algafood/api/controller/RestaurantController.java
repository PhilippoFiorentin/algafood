package com.philippo.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.philippo.algafood.Groups;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.philippo.algafood.domain.repository.RestaurantRepository;
import com.philippo.algafood.domain.service.RegisterRestaurantService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private RegisterRestaurantService registerRestaurant;

	@GetMapping
	public List<Restaurant> listAllRestaurants(){
		return restaurantRepository.findAll();
	}

	@GetMapping("/{restaurantId}")
	public Restaurant findRestaurant(@PathVariable Long restaurantId) {
		return registerRestaurant.findOrFail(restaurantId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurant addRestaurant(
			@RequestBody @Validated(Groups.KitchenId.class) Restaurant restaurant) {
		try{
			return registerRestaurant.save(restaurant);
		} catch (KitchenNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PutMapping("/{restaurantId}")
	public Restaurant update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant){

		try{
			Restaurant currentRestaurant = registerRestaurant.findOrFail(restaurantId);

			BeanUtils.copyProperties(restaurant,
						currentRestaurant,
						"id", "paymentMethods", "address", "registerDate", "products");

			return registerRestaurant.save(currentRestaurant);
		} catch (KitchenNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{restaurantId}")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void delete(@PathVariable Long restaurantId){
		registerRestaurant.delete(restaurantId);
	}

	@PatchMapping("/{restaurantId}")
	public Restaurant updatePartial(
			@PathVariable Long restaurantId, @RequestBody Map<String, Object> fields, HttpServletRequest request) {

		Restaurant currentRestaurant = registerRestaurant.findOrFail(restaurantId);

		merge(fields, currentRestaurant, request);

		return update(restaurantId, currentRestaurant);
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

	public void merge(Map<String, Object> sourceData, Restaurant targetRestaurant, HttpServletRequest request) {

		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

			Restaurant sourceRestaurant = objectMapper.convertValue(sourceData, Restaurant.class);


			sourceData.forEach((propName, propValue) -> {
				Field field = ReflectionUtils.findField(Restaurant.class, propName);

				field.setAccessible(true);

				Object newValue = ReflectionUtils.getField(field, sourceRestaurant);

				ReflectionUtils.setField(field, targetRestaurant, newValue);
			});

		} catch (IllegalArgumentException e){
			Throwable rootCause = ExceptionUtils.getRootCause(e);

			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}
	}
}
