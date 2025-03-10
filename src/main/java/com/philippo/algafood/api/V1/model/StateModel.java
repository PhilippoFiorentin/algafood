package com.philippo.algafood.api.V1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "states")
@Getter
@Setter
public class StateModel extends RepresentationModel<StateModel> {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Dublin")
    private String name;
}
