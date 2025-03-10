package com.philippo.algafood.api.V1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "groups")
@Getter
@Setter
public class GroupModel extends RepresentationModel<GroupModel> {

    private Long id;

    private String name;

}
