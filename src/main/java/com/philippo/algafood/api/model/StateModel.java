package com.philippo.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class StateModel extends RepresentationModel<StateModel> {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "Dublin")
    private String name;
}
