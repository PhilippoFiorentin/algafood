package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.philippo.algafood.domain.exception.EntityInUseException;
import com.philippo.algafood.domain.exception.EntityNotFoundException;
import com.philippo.algafood.domain.model.Kitchen;
import com.philippo.algafood.domain.repository.KitchenRepository;
import com.philippo.algafood.domain.repository.RestaurantRepository;

@Service
public class RegisterRestaurantService {

	public static final String RESTAURANT_NOT_FOUND = "Restaurant register with code %d could not be found";
	public static final String RESTAURANT_IN_USE = "The Restaurant with code %d could not be deleted";
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RegisterKitchenService registerKitchen;
	
	public Restaurant save(Restaurant restaurant) {
		Long kitchenId = restaurant.getKitchen().getId();

		Kitchen kitchen = registerKitchen.findOrFail(kitchenId);

		restaurant.setKitchen(kitchen);
		
		return restaurantRepository.save(restaurant);
	}
	
	public void delete(Long restaurantId) {
		try {
			restaurantRepository.deleteById(restaurantId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format(
					RESTAURANT_NOT_FOUND, restaurantId
					)
			);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(
					RESTAURANT_IN_USE, restaurantId
					)
			);
		}
	}

	public Restaurant findOrFail(Long restaurantId){
		return restaurantRepository
				.findById(restaurantId)
				.orElseThrow(() -> new EntityNotFoundException(String.format(
						RESTAURANT_NOT_FOUND, restaurantId)));
	}
}
