package com.philippo.algafood.api.openapi.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Links")
public class LinksModelOpenApi {

    private LinksModel rel;

    @Getter
    @Setter
    @ApiModel("Link")
    private class LinksModel {

        private String href;
        private boolean templated;
    }
}
