package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.CityInputDisassembler;
import com.philippo.algafood.api.assembler.CityModelAssembler;
import com.philippo.algafood.api.model.CityModel;
import com.philippo.algafood.api.model.input.CityInput;
import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.StateNotFoundException;
import com.philippo.algafood.domain.model.City;
import com.philippo.algafood.domain.repository.CityRepository;
import com.philippo.algafood.domain.service.RegisterCityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cities")
@RestController
@RequestMapping("/cities")
public class CityController {
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private RegisterCityService registerCity;

	@Autowired
	private CityModelAssembler cityModelAssembler;

	@Autowired
	private CityInputDisassembler cityInputDisassembler;
	
	@ApiOperation("List cities")
	@GetMapping
	public List<CityModel> list() {
		return cityModelAssembler.toCollectionModel(cityRepository.findAll());
	}

	@ApiOperation("Search a city by ID")
	@GetMapping("/{cityId}")
	public CityModel find(@ApiParam(value = "ID of a city", example = "1") @PathVariable Long cityId) {
		City city = registerCity.findOrFail(cityId);

		return cityModelAssembler.toModel(city);
	}

	@ApiOperation("Register a city")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CityModel add(
			@ApiParam(name = "body", value = "Representation of a new city")
			@RequestBody
			@Valid CityInput cityInput) {
		try {
			City city = cityInputDisassembler.toDomainObject(cityInput);
			return cityModelAssembler.toModel(registerCity.save(city));
		}catch (StateNotFoundException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@ApiOperation("Update a city by ID")
	@PutMapping("/{cityId}")
	public CityModel update(
			@ApiParam(value = "ID of a city", example = "1")
			@PathVariable Long cityId,
			@ApiParam(name = "body", value = "representation of a city with the new data")
			@RequestBody @Valid CityInput cityInput){

		try {
			City currentCity = registerCity.findOrFail(cityId);

			cityInputDisassembler.copyToDomainObject(cityInput, currentCity);

			return cityModelAssembler.toModel(registerCity.save(currentCity));
		}catch (StateNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@ApiOperation("Delete a city")
	@DeleteMapping("/{cityId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@ApiParam(value = "ID of a city", example = "1") @PathVariable Long cityId){
		registerCity.delete(cityId);
	}
}
