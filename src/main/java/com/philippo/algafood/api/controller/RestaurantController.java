package com.philippo.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.philippo.algafood.domain.model.Restaurant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.philippo.algafood.domain.exception.EntityInUseException;
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
	public ResponseEntity<Restaurant> findRestaurant(@PathVariable Long restaurantId) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

		if(restaurant.isPresent())
			return ResponseEntity.ok(restaurant.get());

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> addRestaurant(@RequestBody Restaurant restaurant) {
		try {
			restaurant = registerRestaurant.save(restaurant);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
		}catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{restaurantId}")
	public ResponseEntity<?> update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant){

		Restaurant currentRestaurant = restaurantRepository
			.findById(restaurantId)
			.orElse(null);

		try {

			if (currentRestaurant != null) {
				BeanUtils.copyProperties(restaurant,
					currentRestaurant,
					"id",
					"paymentMethods");
				Restaurant savedRestaurant = registerRestaurant.save(currentRestaurant);
				return ResponseEntity.ok(savedRestaurant);
			}

			return ResponseEntity.notFound().build();
		} catch (EntityInUseException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{restaurantId}")
	public ResponseEntity<Restaurant> delete(@PathVariable Long restaurantId){

		try {
			registerRestaurant.delete(restaurantId);

			return ResponseEntity.noContent().build();

		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@PatchMapping("/{restaurantId}")
	public ResponseEntity<?> updatePartial(
		@PathVariable Long restaurantId, @RequestBody Map<String, Object> fields) {
		Restaurant currentRestaurant = restaurantRepository
			.findById(restaurantId).orElse(null);

		if(currentRestaurant == null) {
			return ResponseEntity.notFound().build();
		}

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
