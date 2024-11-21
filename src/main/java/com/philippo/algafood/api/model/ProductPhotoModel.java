package com.philippo.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoModel {

    private String filename;
    private String description;
    private String contentType;
    private Long size;
}
