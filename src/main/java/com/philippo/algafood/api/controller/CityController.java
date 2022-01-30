package com.philippo.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import com.philippo.algafood.domain.model.City;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.philippo.algafood.domain.exception.EntityInUseException;
import com.philippo.algafood.domain.exception.EntityNotFoundException;
import com.philippo.algafood.domain.repository.CityRepository;
import com.philippo.algafood.domain.service.RegisterCityService;

@RestController
@RequestMapping("/cities")
public class CityController
{
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private RegisterCityService registerCity;
	
	@GetMapping
	public List<City> listAllCities() {
		return cityRepository.findAll();
	}
	
	@GetMapping("/{cityId}")
	public City findCity(@PathVariable Long cityId) {
		return registerCity.findOrFail(cityId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public City addCity(@RequestBody City city) {
		return registerCity.save(city);
	}

	@PutMapping("/{cityId}")
	public City update(@PathVariable Long cityId, @RequestBody City city){
		City currentCity = registerCity.findOrFail(cityId);
		
		BeanUtils.copyProperties(city, currentCity, "id");

		return registerCity.save(city);
	}
	
	@DeleteMapping("/{cityId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long cityId){
		registerCity.delete(cityId);
	}
}
