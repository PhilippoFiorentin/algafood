package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.exception.EntityInUseException;
import com.philippo.algafood.domain.exception.KitchenNotFoundException;
import com.philippo.algafood.domain.model.Kitchen;
import com.philippo.algafood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterKitchenService {

	public static final String KITCHEN_IN_USE = "The kitchen with code %d could not be deleted because it is in use";

	@Autowired
	private KitchenRepository kitchenRepository;

	@Transactional
	public Kitchen save(Kitchen kitchen) {
		return kitchenRepository.save(kitchen);
	}

	@Transactional
	public void delete(Long kitchenId) {
		try {
			kitchenRepository.deleteById(kitchenId);
			kitchenRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new KitchenNotFoundException(kitchenId);
		} catch (DataIntegrityViolationException e) {
			 throw new EntityInUseException(String.format(KITCHEN_IN_USE, kitchenId));
		}
	}

	public Kitchen findOrFail(Long kitchenId){
		return kitchenRepository
			.findById(kitchenId)
			.orElseThrow(() -> new KitchenNotFoundException(kitchenId));
	}
}
