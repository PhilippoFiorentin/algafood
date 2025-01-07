package com.philippo.algafood.api.v1.openapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("PageModel")
@Getter
@Setter
public class PageModelOpenApi {

    @ApiModelProperty(example = "10", value = "Number of elements per page")
    private Long size;

    @ApiModelProperty(example = "50", value = "total number of records")
    private Long totalElements;

    @ApiModelProperty(example = "5", value = "total number of pages")
    private Long totalPages;

    @ApiModelProperty(example = "0", value = "page number (starts at 0)")
    private Long number;

}
