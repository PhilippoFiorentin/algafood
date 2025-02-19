package com.philippo.algafood.api.V1.openapi.model;

import com.philippo.algafood.api.V1.model.RestaurantOrderSummaryModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("RestaurantOrdersSummaryModel")
@Data
public class RestaurantOrdersSummaryModelOpenApi {

    private EmbeddedRestaurantOrderSummaryModelOpenApi _embedded;
    private Links _links;

    @ApiModel("EmbeddedRestaurantOrdersSummaryModel")
    @Data
    public class EmbeddedRestaurantOrderSummaryModelOpenApi {
        private List<RestaurantOrderSummaryModel> orders;
    }
}
