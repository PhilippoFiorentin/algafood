package com.philippo.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@ApiModel("Problem")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {

    @ApiModelProperty(example = "400")
    private Integer status;

    @ApiModelProperty(example = "2024-12-17T22:23:24.70844Z")
    private OffsetDateTime timestamp;

    @ApiModelProperty(example = "http://algafood.com.br/invalid-data")
    private String type;

    @ApiModelProperty(example = "Invalid Data")
    private String title;

    @ApiModelProperty(example = "One or more fields are invalid. Please fill in the fields correctly and try again.")
    private String detail;

    @ApiModelProperty(example = "One or more fields are invalid. Please fill in the fields correctly and try again.")
    private String userMessage;

    @ApiModelProperty("Objects or fields that generated the error (optional)")
    private List<Object> objects;

    @ApiModel("ProblemObject")
    @Getter
    @Builder
    public static class Object{

        @ApiModelProperty(example = "price")
        private String name;

        @ApiModelProperty(example = "price is required")
        private String userMessage;
    }
}
