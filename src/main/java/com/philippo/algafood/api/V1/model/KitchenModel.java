package com.philippo.algafood.api.V1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "kitchens")
@Getter
@Setter
public class KitchenModel extends RepresentationModel<KitchenModel> {

    private Long id;

    private String name;
}
