package com.philippo.algafood.api.v1.openapi.model;

import com.philippo.algafood.api.v1.model.CityModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CitiesModel")
@Data
public class CitiesModelOpenApi {

    private EmbeddedCityModelOpenApi _embedded;
    private Links _links;

    @ApiModel("EmbeddedCitiesModel")
    @Data
    public class EmbeddedCityModelOpenApi {
        private List<CityModel> _cities;
    }
}
