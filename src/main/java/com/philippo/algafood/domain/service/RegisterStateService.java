package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.philippo.algafood.domain.exception.EntityInUseException;
import com.philippo.algafood.domain.exception.EntityNotFoundException;
import com.philippo.algafood.domain.repository.StateRepository;

@Service
public class RegisterStateService {

	@Autowired
	private StateRepository stateRepository;
	
	public State save( State state ) {
		return stateRepository.save(state);
	}
	
	public void delete(Long stateId) {
		try {
			stateRepository.deleteById(stateId);
		} catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format(
				"There is no state register with code %d",
				stateId));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(
				"The state with code %d could not be deleted because it is being used",
				stateId));
		}
	}
}
