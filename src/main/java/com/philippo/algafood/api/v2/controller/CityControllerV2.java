package com.philippo.algafood.api.v2.controller;

import com.philippo.algafood.api.ResourceUriHelper;
import com.philippo.algafood.api.v2.assembler.CityInputDisassemblerV2;
import com.philippo.algafood.api.v2.assembler.CityModelAssemblerV2;
import com.philippo.algafood.api.v2.model.CityModelV2;
import com.philippo.algafood.api.v2.model.input.CityInputV2;
import com.philippo.algafood.api.v2.openapi.controller.CityControllerV2OpenApi;
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
@RequestMapping(value = "/v2/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityControllerV2 implements CityControllerV2OpenApi {
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private RegisterCityService registerCity;

	@Autowired
	private CityModelAssemblerV2 cityModelAssemblerV2;

	@Autowired
	private CityInputDisassemblerV2 cityInputDisassemblerV2;
	
	@GetMapping
	public CollectionModel<CityModelV2> list() {
		return cityModelAssemblerV2.toCollectionModel(cityRepository.findAll());
	}

	@GetMapping(value = "/{cityId}")
	public CityModelV2 find(@PathVariable Long cityId) {
		City city = registerCity.findOrFail(cityId);
		return cityModelAssemblerV2.toModel(city);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CityModelV2 add(@RequestBody @Valid CityInputV2 cityInput) {
		try {
			City city = cityInputDisassemblerV2.toDomainObject(cityInput);
			CityModelV2 cityModel = cityModelAssemblerV2.toModel(registerCity.save(city));

			ResourceUriHelper.addUriInResponseHeader(cityModel.getCityId());

			return cityModel;
		}catch (StateNotFoundException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PutMapping(value = "/{cityId}")
	public CityModelV2 update(@PathVariable Long cityId, @RequestBody @Valid CityInputV2 cityInput){
		try {
			City currentCity = registerCity.findOrFail(cityId);

			cityInputDisassemblerV2.copyToDomainObject(cityInput, currentCity);

			return cityModelAssemblerV2.toModel(registerCity.save(currentCity));
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
