package com.philippo.algafood.api.v1.controller;

import com.philippo.algafood.api.ResourceUriHelper;
import com.philippo.algafood.api.v1.assembler.CityInputDisassembler;
import com.philippo.algafood.api.v1.assembler.CityModelAssembler;
import com.philippo.algafood.api.v1.model.CityModel;
import com.philippo.algafood.api.v1.model.input.CityInput;
import com.philippo.algafood.api.v1.openapi.controller.CityControllerOpenApi;
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
	
	@GetMapping
	public CollectionModel<CityModel> list() {
		return cityModelAssembler.toCollectionModel(cityRepository.findAll());
	}

	@GetMapping(value = "/{cityId}")
	public CityModel find(@PathVariable Long cityId) {
		City city = registerCity.findOrFail(cityId);
		return cityModelAssembler.toModel(city);
	}

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

	@DeleteMapping("/{cityId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long cityId){
		registerCity.delete(cityId);
	}
}
