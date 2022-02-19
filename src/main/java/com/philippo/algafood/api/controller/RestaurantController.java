package com.philippo.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.model.Restaurant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import com.philippo.algafood.domain.exception.EntityNotFoundException;
import com.philippo.algafood.domain.repository.RestaurantRepository;
import com.philippo.algafood.domain.service.RegisterRestaurantService;

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
	public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
		try{
			return registerRestaurant.save(restaurant);
		} catch (EntityNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@PutMapping("/{restaurantId}")
	public Restaurant update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant){

		Restaurant currentRestaurant = registerRestaurant.findOrFail(restaurantId);


		BeanUtils.copyProperties(restaurant,
					currentRestaurant,
					"id", "paymentMethods", "address", "registerDate", "products");

		try{
			return registerRestaurant.save(currentRestaurant);
		} catch (EntityNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@DeleteMapping("/{restaurantId}")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void delete(@PathVariable Long restaurantId){
		registerRestaurant.delete(restaurantId);
	}

	@PatchMapping("/{restaurantId}")
	public Restaurant updatePartial(
		@PathVariable Long restaurantId, @RequestBody Map<String, Object> fields) {

		Restaurant currentRestaurant = registerRestaurant.findOrFail(restaurantId);

		merge(fields, currentRestaurant);

		return update(restaurantId, currentRestaurant);
	}

	/*

	O método merge irá converter os tipos dos valores, de acordo com o que
	está implementado na entidade declarada.

	Merge method will convert all value types, according to what is implemented
	in the declared entity.

	Depois, irá buscar as propriedades do objeto e setar o(s) novo(s) valor(es) para a
	propriedade específica que está sendo alterada.

	Then, the merge method will find the object props and set the new values
	for the specific prop that is being changed.

	*/

	public void merge(Map<String, Object> sourceData, Restaurant targetRestaurant) {

		ObjectMapper objectMapper = new ObjectMapper();
		Restaurant sourceRestaurant = objectMapper.convertValue(sourceData, Restaurant.class);


		sourceData.forEach((propName, propValue) -> {
			Field field = ReflectionUtils.findField(Restaurant.class, propName);

			field.setAccessible(true);

			Object newValue = ReflectionUtils.getField(field, sourceRestaurant);

			ReflectionUtils.setField(field, targetRestaurant, newValue);
		});
	}
}
