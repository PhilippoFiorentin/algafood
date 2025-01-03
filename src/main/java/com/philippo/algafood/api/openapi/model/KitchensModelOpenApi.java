package com.philippo.algafood.api.openapi.model;

import com.philippo.algafood.api.model.KitchenModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("KitchensModel")
@Getter
@Setter
public class KitchensModelOpenApi {

    private EmbeddedKitchenModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi _page;

    @ApiModel("EmbeddedKitchensModel")
    @Data
    public class EmbeddedKitchenModelOpenApi {
        private List<KitchenModel> kitchens;
    }

}
