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

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	public Restaurant save(Restaurant restaurant) {
		Long kitchenId = restaurant.getKitchen().getId();

		Kitchen kitchen = kitchenRepository.findById(kitchenId)
			.orElseThrow(() -> new EntityNotFoundException(
				String.format(
				"Restaurant register with code %d could not be found", kitchenId)));
		
		restaurant.setKitchen(kitchen);
		
		return restaurantRepository.save(restaurant);
	}
	
	public void delete(Long restaurantId) {
		try {
			restaurantRepository.deleteById(restaurantId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format(
					"Restaurant register with code %d could not be found", restaurantId
					)
			);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(
					"The Restaurant with code %d could not be deleted", restaurantId
					)
			);
		}
	}
}
