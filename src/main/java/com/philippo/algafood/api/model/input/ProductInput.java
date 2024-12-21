package com.philippo.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductInput {

    @ApiModelProperty(example = "Fish and chips", required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(example = "Comes with sauce", required = true)
    @NotBlank
    private String description;

    @ApiModelProperty(example = "10.00", required = true)
    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @ApiModelProperty(example = "true", required = true)
    @NotNull
    private Boolean active;
}
