package com.philippo.algafood.api.V1.openapi.model;

import com.philippo.algafood.api.V1.model.StateModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("StatesModel")
@Getter
@Setter
public class StatesModelOpenApi {

    private EmbeddedStateModelOpenApi _embedded;
    private Links _links;

    @ApiModel("EmbeddedStatesModel")
    @Data
    public class EmbeddedStateModelOpenApi {
        private List<StateModel> states;
    }
}
