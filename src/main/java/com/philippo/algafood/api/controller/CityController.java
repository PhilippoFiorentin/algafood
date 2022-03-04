package com.philippo.algafood.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.EntityNotFoundException;
import com.philippo.algafood.domain.exception.StateNotFoundException;
import com.philippo.algafood.domain.model.City;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.philippo.algafood.domain.repository.CityRepository;
import com.philippo.algafood.domain.service.RegisterCityService;

@RestController
@RequestMapping("/cities")
public class CityController {
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
		try {
			return registerCity.save(city);
		}catch (StateNotFoundException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PutMapping("/{cityId}")
	public City update(@PathVariable Long cityId, @RequestBody City city){

		try {
			City currentCity = registerCity.findOrFail(cityId);

			BeanUtils.copyProperties(city, currentCity, "id");

			return registerCity.save(currentCity);
		}catch (StateNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{cityId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long cityId){
		registerCity.delete(cityId);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e){
		Problem problem = Problem.builder()
				.dateHour(LocalDateTime.now())
				.message(e.getMessage()).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handleBusinessException(BusinessException e){
		Problem problem = Problem.builder()
				.dateHour(LocalDateTime.now())
				.message(e.getMessage()).build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
	}
}
