package com.philippo.algafood.api.controller;

import java.util.List;

import com.philippo.algafood.api.assembler.RestaurantModelAssembler;
import com.philippo.algafood.api.assembler.RestaurantInputDisassembler;
import com.philippo.algafood.api.model.RestaurantModel;
import com.philippo.algafood.api.model.input.RestaurantInput;
import com.philippo.algafood.core.validation.ValidationException;
import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.CityNotFoundException;
import com.philippo.algafood.domain.exception.KitchenNotFoundException;
import com.philippo.algafood.domain.model.Restaurant;
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
	private RestaurantInputDisassembler restaurantInputDisassembler;

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
			Restaurant restaurant = restaurantInputDisassembler.toDomainObject(restaurantInput);
			return restaurantModelAssembler.toModel(registerRestaurant.save(restaurant));
		} catch (KitchenNotFoundException | CityNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PutMapping("/{restaurantId}")
	public RestaurantModel updateRestaurant(@PathVariable Long restaurantId, @RequestBody @Valid RestaurantInput restaurantInput){

		try{
			Restaurant currentRestaurant = registerRestaurant.findOrFail(restaurantId);

			restaurantInputDisassembler.copyToDomainObject(restaurantInput, currentRestaurant);

			return restaurantModelAssembler.toModel(registerRestaurant.save(currentRestaurant));
		} catch (KitchenNotFoundException | CityNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{restaurantId}")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void delete(@PathVariable Long restaurantId){
		registerRestaurant.delete(restaurantId);
	}

	private void validate(Restaurant restaurant, String objectName) {
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurant, objectName);

		validator.validate(restaurant, bindingResult);

		if (bindingResult.hasErrors()) {
			throw new ValidationException(bindingResult);

		}
	}

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
