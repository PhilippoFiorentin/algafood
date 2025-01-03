package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.exception.EntityInUseException;
import com.philippo.algafood.domain.exception.RestaurantNotFoundException;
import com.philippo.algafood.domain.model.*;
import com.philippo.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegisterRestaurantService {

	public static final String RESTAURANT_IN_USE = "The Restaurant with code %d could not be deleted because it is in use";
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RegisterKitchenService registerKitchen;

	@Autowired
	private RegisterPaymentMethodService registerPaymentMethodService;

	@Autowired
	private RegisterCityService registerCity;

	@Autowired
	private RegisterUserService registerUser;

	@Transactional
	public Restaurant save(Restaurant restaurant) {
		Long kitchenId = restaurant.getKitchen().getId();
		Long cityId = restaurant.getAddress().getCity().getId();

		Kitchen kitchen = registerKitchen.findOrFail(kitchenId);
		City city = registerCity.findOrFail(cityId);

		restaurant.setKitchen(kitchen);
		restaurant.getAddress().setCity(city);
		
		return restaurantRepository.save(restaurant);
	}

	@Transactional
	public void delete(Long restaurantId) {
		try {
			restaurantRepository.deleteById(restaurantId);
			restaurantRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new RestaurantNotFoundException(restaurantId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(RESTAURANT_IN_USE, restaurantId));
		}
	}

	@Transactional
	public void activate(Long restaurantId){
		Restaurant currentRestaurant = findOrFail(restaurantId);
		currentRestaurant.activate();
	}

	@Transactional
	public void deactivate(Long restaurantId){
		Restaurant currentRestaurant = findOrFail(restaurantId);
		currentRestaurant.deactivate();
	}

	//Activate many restaurants at once
	@Transactional
	public void activate(List<Long> restaurantIds){
		restaurantIds.forEach(this::activate);
	}

	//Deactivate many restaurants at once
	@Transactional
	public void deactivate(List<Long> restaurantIds){
		restaurantIds.forEach(this::deactivate);
	}

	@Transactional
	public void open(Long restaurantId){
		Restaurant currentRestaurant = findOrFail(restaurantId);
		currentRestaurant.open();
	}

	@Transactional
	public void close(Long restaurantId){
		Restaurant currentRestaurant = findOrFail(restaurantId);
		currentRestaurant.close();
	}

	@Transactional
	public void disaffiliatePaymentMethod(Long restaurantId, Long paymentMethodId){
		Restaurant restaurant = findOrFail(restaurantId);
		PaymentMethod paymentMethod = registerPaymentMethodService.findOrFail(paymentMethodId);

		restaurant.removePaymentMethod(paymentMethod);
	}

	@Transactional
	public void affiliatePaymentMethod(Long restaurantId, Long paymentMethodId){
		Restaurant restaurant = findOrFail(restaurantId);
		PaymentMethod paymentMethod = registerPaymentMethodService.findOrFail(paymentMethodId);

		restaurant.addPaymentMethod(paymentMethod);
	}

	@Transactional
	public void disaffiliateResponsibleUser(Long restaurantId, Long userId) {
		Restaurant restaurant = findOrFail(restaurantId);
		User user = registerUser.findOrFail(userId);

		restaurant.removeUserResponsible(user);
	}

	@Transactional
	public void affiliateResponsibleUser(Long restaurantId, Long userId){
		Restaurant restaurant = findOrFail(restaurantId);
		User user = registerUser.findOrFail(userId);

		restaurant.addUserResponsible(user);
	}

	public Restaurant findOrFail(Long restaurantId){
		return restaurantRepository
				.findById(restaurantId)
				.orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
	}
}
