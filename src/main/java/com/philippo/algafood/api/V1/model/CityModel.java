package com.philippo.algafood.api.V1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cities")
@Getter
@Setter
public class CityModel extends RepresentationModel<CityModel> {

    private Long id;

    private String name;

    private StateModel state;
}
