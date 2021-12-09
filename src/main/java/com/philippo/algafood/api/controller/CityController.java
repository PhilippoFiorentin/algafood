package com.philippo.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import com.philippo.algafood.domain.model.City;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<City> findCity(@PathVariable Long cityId) {
		Optional<City> city = cityRepository.findById(cityId);
		
		if (city.isPresent())
			return ResponseEntity.ok().build();
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> addCity(@RequestBody City city) {
		try {
			city = registerCity.save(city);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(city);
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage()); 
		}
	}

	@PutMapping("/{cityId}")
	public ResponseEntity<?> update(@PathVariable Long cityId, @RequestBody City city){
		City currentCity = cityRepository.findById(cityId).orElse(null);
		
		try {
			if (currentCity != null) {
				BeanUtils.copyProperties(city, currentCity, "id");

				City savedCity = registerCity.save(currentCity);
				return ResponseEntity.ok(savedCity);
			}
			
			return ResponseEntity.notFound().build();
			
		} catch (EntityInUseException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{cityId}")
	public ResponseEntity<City> delete(@PathVariable Long cityId){
		
		try {
			registerCity.delete(cityId);
			
			return ResponseEntity.noContent().build();
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
