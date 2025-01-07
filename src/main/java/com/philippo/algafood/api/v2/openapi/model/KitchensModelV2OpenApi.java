package com.philippo.algafood.api.v2.openapi.model;

import com.philippo.algafood.api.v2.model.KitchenModelV2;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("KitchensModel")
@Getter
@Setter
public class KitchensModelV2OpenApi {

    private EmbeddedKitchenModelOpenApi _embedded;
    private Links _links;
    private PageModelV2OpenApi _page;

    @ApiModel("EmbeddedKitchensModel")
    @Data
    public class EmbeddedKitchenModelOpenApi {
        private List<KitchenModelV2> kitchens;
    }

}
