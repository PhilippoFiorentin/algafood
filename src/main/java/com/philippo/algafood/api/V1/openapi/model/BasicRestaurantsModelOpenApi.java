package com.philippo.algafood.api.V1.openapi.model;

import com.philippo.algafood.api.V1.model.BasicRestaurantModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("BasicRestaurantsModel")
@Data
public class BasicRestaurantsModelOpenApi {

    private EmbeddedBasicRestaurantsModel _embedded;
    private Links _links;

    @ApiModel("EmbeddedBasicRestaurantsModel")
    @Data
    public class EmbeddedBasicRestaurantsModel {
        private List<BasicRestaurantModel> restaurants;
    }
}
