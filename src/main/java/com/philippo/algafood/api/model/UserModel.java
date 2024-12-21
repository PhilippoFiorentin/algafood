package com.philippo.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "John Smith")
    private String name;

    @ApiModelProperty(example = "johnsmith@email.com")
    private String email;
}
