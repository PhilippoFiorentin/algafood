package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.CityModel;
import com.philippo.algafood.api.V1.model.input.CityInput;
import org.springframework.hateoas.CollectionModel;

public interface CityControllerOpenApi {

    CollectionModel<CityModel> list();

    CityModel find(Long cityId);

    CityModel add(CityInput cityInput);

    CityModel update(Long cityId, CityInput cityInput);

    void delete(Long cityId);

}
