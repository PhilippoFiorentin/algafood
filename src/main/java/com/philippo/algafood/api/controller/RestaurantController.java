package com.philippo.algafood.api.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.philippo.algafood.api.assembler.RestaurantModelAssembler;
import com.philippo.algafood.api.assembler.RestaurantInputDisassembler;
import com.philippo.algafood.api.model.RestaurantModel;
import com.philippo.algafood.api.model.input.RestaurantInput;
import com.philippo.algafood.api.model.view.RestaurantView;
import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.CityNotFoundException;
import com.philippo.algafood.domain.exception.KitchenNotFoundException;
import com.philippo.algafood.domain.exception.RestaurantNotFoundException;
import com.philippo.algafood.domain.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
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
	private RestaurantInputDisassembler restaurantInputDisassembler;

// Dynamic method to list using MappingJacksonValue Wrapper class

//	@GetMapping
//	public MappingJacksonValue list(@RequestParam(required = false) String projection){
//		List<Restaurant> restaurants = restaurantRepository.findAll();
//		List<RestaurantModel> restaurantModel = restaurantModelAssembler.toCollectionModel(restaurants);
//
//		MappingJacksonValue restaurantWrapper = new MappingJacksonValue(restaurantModel);
//
//		restaurantWrapper.setSerializationView(RestaurantView.Summary.class);
//
//		if ("just-name".equals(projection)) {
//			restaurantWrapper.setSerializationView(RestaurantView.JustName.class);
//		} else if ("complete".equals(projection)){
//			restaurantWrapper.setSerializationView(null);
//		}
//
//		return restaurantWrapper;
//	}

	@JsonView(RestaurantView.Summary.class)
	@GetMapping
	public List<RestaurantModel> list(){
		return restaurantModelAssembler.toCollectionModel(restaurantRepository.findAll());
	}

	@JsonView(RestaurantView.JustName.class)
	@GetMapping(params="projection=just-name")
	public List<RestaurantModel> listSummary(){
		return list();
	}

	@GetMapping("/{restaurantId}")
	public RestaurantModel find(@PathVariable Long restaurantId) {
		Restaurant restaurant = registerRestaurant.findOrFail(restaurantId);

		return restaurantModelAssembler.toModel(restaurant);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestaurantModel add(@RequestBody @Valid RestaurantInput restaurantInput) {
		try{
			Restaurant restaurant = restaurantInputDisassembler.toDomainObject(restaurantInput);
			return restaurantModelAssembler.toModel(registerRestaurant.save(restaurant));
		} catch (KitchenNotFoundException | CityNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PutMapping("/{restaurantId}")
	public RestaurantModel update(@PathVariable Long restaurantId, @RequestBody @Valid RestaurantInput restaurantInput){

		try{
			Restaurant currentRestaurant = registerRestaurant.findOrFail(restaurantId);

			restaurantInputDisassembler.copyToDomainObject(restaurantInput, currentRestaurant);

			return restaurantModelAssembler.toModel(registerRestaurant.save(currentRestaurant));
		} catch (KitchenNotFoundException | CityNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

//	@DeleteMapping("/{restaurantId}")
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public void delete(@PathVariable Long restaurantId){
//		registerRestaurant.delete(restaurantId);
//	}

//	private void validate(Restaurant restaurant, String objectName) {
//		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurant, objectName);
//
//		validator.validate(restaurant, bindingResult);
//
//		if (bindingResult.hasErrors()) {
//			throw new ValidationException(bindingResult);
//
//		}
//	}

	@PutMapping("/{restaurantId}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void activate(@PathVariable Long restaurantId){
		registerRestaurant.activate(restaurantId);
	}

	@DeleteMapping("/{restaurantId}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deactivate(@PathVariable Long restaurantId){
		registerRestaurant.deactivate(restaurantId);
	}

	@PutMapping("/activations")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void activateManyRestaurants(@RequestBody List<Long> restaurantIds){
		try {
			registerRestaurant.activate(restaurantIds);
		} catch (RestaurantNotFoundException e) {
		throw new BusinessException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/activations")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deactivateManyRestaurants(@RequestBody List<Long> restaurantIds){
		try {
			registerRestaurant.deactivate(restaurantIds);
		} catch (RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PutMapping("/{restaurantId}/opening")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void open(@PathVariable Long restaurantId){
		registerRestaurant.open(restaurantId);
	}

	@PutMapping("/{restaurantId}/closing")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void close(@PathVariable Long restaurantId){
		registerRestaurant.close(restaurantId);
	}

}
