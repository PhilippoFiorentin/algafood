package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.model.Kitchen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.philippo.algafood.domain.exception.EntityInUseException;
import com.philippo.algafood.domain.exception.EntityNotFoundException;
import com.philippo.algafood.domain.repository.KitchenRepository;

@Service
public class RegisterKitchenService {
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}
	
	public void delete(Long kitchenId) {
		try {
			kitchenRepository.deleteById(kitchenId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
					String.format(
						"There is no kitchen register with code %d", kitchenId));
		} catch (DataIntegrityViolationException e) {
			 throw new EntityInUseException(
					 String.format(
						 "The kitchen with code %d could not be deleted because it is being used",
						 kitchenId));
		}
	}
}
