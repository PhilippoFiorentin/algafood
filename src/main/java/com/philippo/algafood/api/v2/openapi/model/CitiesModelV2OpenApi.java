package com.philippo.algafood.api.v2.openapi.model;

import com.philippo.algafood.api.v1.model.CityModel;
import com.philippo.algafood.api.v2.model.CityModelV2;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("CitiesModel")
@Data
public class CitiesModelV2OpenApi {

    private EmbeddedCityModelOpenApi _embedded;
    private Links _links;

    @ApiModel("EmbeddedCitiesModel")
    @Data
    public class EmbeddedCityModelOpenApi {
        private List<CityModelV2> _cities;
    }
}
