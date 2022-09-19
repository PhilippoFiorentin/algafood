package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.exception.CityNotFoundException;
import com.philippo.algafood.domain.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.philippo.algafood.domain.exception.EntityInUseException;
import com.philippo.algafood.domain.model.State;
import com.philippo.algafood.domain.repository.CityRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterCityService {

	public static final String STATE_IN_USE = "The city with code %d could not be deleted because it is in use";
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private RegisterStateService registerState;

	@Transactional
	public City save( City city) {
		Long stateId = city.getState().getId();

		State state = registerState.findOrFail(stateId);

		city.setState(state);
		
		return cityRepository.save(city);
	}

	@Transactional
	public void delete (Long cityId) {
		try {
			cityRepository.deleteById(cityId);
		} catch (EmptyResultDataAccessException e) {
			throw new CityNotFoundException(cityId);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(STATE_IN_USE, cityId));
		}
	}

	public City findOrFail(Long cityId){
		return cityRepository
				.findById(cityId)
				.orElseThrow(() -> new CityNotFoundException(cityId));
	}
}
