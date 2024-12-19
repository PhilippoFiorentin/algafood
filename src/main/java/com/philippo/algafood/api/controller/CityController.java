package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.CityInputDisassembler;
import com.philippo.algafood.api.assembler.CityModelAssembler;
import com.philippo.algafood.api.controller.openapi.CityControllerOpenApi;
import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.model.CityModel;
import com.philippo.algafood.api.model.input.CityInput;
import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.StateNotFoundException;
import com.philippo.algafood.domain.model.City;
import com.philippo.algafood.domain.repository.CityRepository;
import com.philippo.algafood.domain.service.RegisterCityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cities")
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
	public List<CityModel> list() {
		return cityModelAssembler.toCollectionModel(cityRepository.findAll());
	}

	@GetMapping("/{cityId}")
	public CityModel find(@PathVariable Long cityId) {
		City city = registerCity.findOrFail(cityId);

		return cityModelAssembler.toModel(city);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CityModel add(@RequestBody @Valid CityInput cityInput) {
		try {
			City city = cityInputDisassembler.toDomainObject(cityInput);
			return cityModelAssembler.toModel(registerCity.save(city));
		}catch (StateNotFoundException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PutMapping("/{cityId}")
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
