package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.RestaurantBasicModelAssembler;
import com.philippo.algafood.api.assembler.RestaurantInputDisassembler;
import com.philippo.algafood.api.assembler.RestaurantJustNameModelAssembler;
import com.philippo.algafood.api.assembler.RestaurantModelAssembler;
import com.philippo.algafood.api.model.BasicRestaurantModel;
import com.philippo.algafood.api.model.RestaurantJustNameModel;
import com.philippo.algafood.api.model.RestaurantModel;
import com.philippo.algafood.api.model.input.RestaurantInput;
import com.philippo.algafood.api.openapi.controller.RestaurantControllerOpenApi;
import com.philippo.algafood.core.security.CheckSecurity;
import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.CityNotFoundException;
import com.philippo.algafood.domain.exception.KitchenNotFoundException;
import com.philippo.algafood.domain.exception.RestaurantNotFoundException;
import com.philippo.algafood.domain.model.Restaurant;
import com.philippo.algafood.domain.repository.RestaurantRepository;
import com.philippo.algafood.domain.service.RegisterRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController implements RestaurantControllerOpenApi {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private RegisterRestaurantService registerRestaurant;

	@Autowired
	private RestaurantModelAssembler restaurantModelAssembler;

	@Autowired
	private RestaurantInputDisassembler restaurantInputDisassembler;

    @Autowired
    private RestaurantBasicModelAssembler restaurantBasicModelAssembler;

	@Autowired
    private RestaurantJustNameModelAssembler restaurantJustNameModelAssembler;


	@CheckSecurity.Restaurants.CanConsult
	@GetMapping
	public CollectionModel<BasicRestaurantModel> list(){
		return restaurantBasicModelAssembler.toCollectionModel(restaurantRepository.findAll());
	}

	@CheckSecurity.Restaurants.CanConsult
	@GetMapping(params="projection=just-name")
	public CollectionModel<RestaurantJustNameModel> listJustNames(){
		return restaurantJustNameModelAssembler.toCollectionModel(restaurantRepository.findAll());
	}

	@CheckSecurity.Restaurants.CanConsult
	@GetMapping("/{restaurantId}")
	public RestaurantModel find(@PathVariable Long restaurantId) {
		Restaurant restaurant = registerRestaurant.findOrFail(restaurantId);

		return restaurantModelAssembler.toModel(restaurant);
	}

	@CheckSecurity.Restaurants.CanEdit
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

	@CheckSecurity.Restaurants.CanEdit
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

	@CheckSecurity.Restaurants.CanEdit
	@PutMapping("/{restaurantId}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> activate(@PathVariable Long restaurantId){
		registerRestaurant.activate(restaurantId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Restaurants.CanEdit
	@DeleteMapping("/{restaurantId}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deactivate(@PathVariable Long restaurantId){
		registerRestaurant.deactivate(restaurantId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Restaurants.CanEdit
	@PutMapping("/activations")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void activateManyRestaurants(@RequestBody List<Long> restaurantIds){
		try {
			registerRestaurant.activate(restaurantIds);
		} catch (RestaurantNotFoundException e) {
		throw new BusinessException(e.getMessage(), e);
		}
	}

	@CheckSecurity.Restaurants.CanEdit
	@DeleteMapping("/activations")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deactivateManyRestaurants(@RequestBody List<Long> restaurantIds){
		try {
			registerRestaurant.deactivate(restaurantIds);
		} catch (RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@CheckSecurity.Restaurants.CanEdit
	@PutMapping("/{restaurantId}/opening")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> open(@PathVariable Long restaurantId){
		registerRestaurant.open(restaurantId);

		return ResponseEntity.noContent().build();
	}

	@CheckSecurity.Restaurants.CanEdit
	@PutMapping("/{restaurantId}/closing")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> close(@PathVariable Long restaurantId){
		registerRestaurant.close(restaurantId);

		return ResponseEntity.noContent().build();
	}

}
