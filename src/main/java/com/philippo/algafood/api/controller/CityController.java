package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.ResourceUriHelper;
import com.philippo.algafood.api.assembler.CityInputDisassembler;
import com.philippo.algafood.api.assembler.CityModelAssembler;
import com.philippo.algafood.api.model.CityModel;
import com.philippo.algafood.api.model.input.CityInput;
import com.philippo.algafood.api.openapi.controller.CityControllerOpenApi;
import com.philippo.algafood.core.security.CheckSecurity;
import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.StateNotFoundException;
import com.philippo.algafood.domain.model.City;
import com.philippo.algafood.domain.repository.CityRepository;
import com.philippo.algafood.domain.service.RegisterCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController implements CityControllerOpenApi {
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private RegisterCityService registerCity;

	@Autowired
	private CityModelAssembler cityModelAssembler;

	@Autowired
	private CityInputDisassembler cityInputDisassembler;

	@CheckSecurity.Cities.CanConsult
	@GetMapping
	public CollectionModel<CityModel> list() {
		return cityModelAssembler.toCollectionModel(cityRepository.findAll());
	}

	@CheckSecurity.Cities.CanConsult
	@GetMapping(value = "/{cityId}")
	public CityModel find(@PathVariable Long cityId) {
		City city = registerCity.findOrFail(cityId);
		return cityModelAssembler.toModel(city);
	}

	@CheckSecurity.Cities.CanEdit
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CityModel add(@RequestBody @Valid CityInput cityInput) {
		try {
			City city = cityInputDisassembler.toDomainObject(cityInput);
			CityModel cityModel = cityModelAssembler.toModel(registerCity.save(city));

			ResourceUriHelper.addUriInResponseHeader(cityModel.getId());

			return cityModel;
		}catch (StateNotFoundException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@CheckSecurity.Cities.CanEdit
	@PutMapping(value = "/{cityId}")
	public CityModel update(@PathVariable Long cityId, @RequestBody @Valid CityInput cityInput){
		try {
			City currentCity = registerCity.findOrFail(cityId);

			cityInputDisassembler.copyToDomainObject(cityInput, currentCity);

			return cityModelAssembler.toModel(registerCity.save(currentCity));
		}catch (StateNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@CheckSecurity.Cities.CanEdit
	@DeleteMapping("/{cityId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long cityId){
		registerCity.delete(cityId);
	}
}
