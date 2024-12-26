package com.philippo.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoModel {

    @ApiModelProperty(example = "b8bbd21a-4dd3-4954-835c-3493af2ba6a0_Prime-Rib.jpg")
    private String filename;

    @ApiModelProperty(example = "Thai rice")
    private String description;

    @ApiModelProperty(example = "image/jpeg")
    private String contentType;

    @ApiModelProperty(example = "202912")
    private Long size;
}
