package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.philippo.algafood.domain.exception.EntityInUseException;
import com.philippo.algafood.domain.exception.EntityNotFoundException;
import com.philippo.algafood.domain.model.State;
import com.philippo.algafood.domain.repository.CityRepository;
import com.philippo.algafood.domain.repository.StateRepository;

import java.util.Optional;

@Service
public class RegisterCityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	public City save( City city) {
		Long stateId = city.getState().getId();
		
		Optional<State> state = stateRepository.findById(stateId);
		
		if (state.isEmpty()) {
			throw new EntityNotFoundException(String.format(
					"State register with code %d was not found", stateId)
			);
		
		}

		city.setState(state.get());
		
		return cityRepository.save(city);
	}
	
	public void delete (Long cityId) {
		try {
			cityRepository.deleteById(cityId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format(
					"City register with code %d was not found", cityId)
			);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(
					"The city with code %d could not be deleted", cityId)
			);
		}
	}
}
