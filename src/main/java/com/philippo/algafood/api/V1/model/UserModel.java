package com.philippo.algafood.api.V1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "users")
@Getter
@Setter
public class UserModel extends RepresentationModel<UserModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "John Smith")
    private String name;

    @ApiModelProperty(example = "johnsmith@email.com")
    private String email;
}
