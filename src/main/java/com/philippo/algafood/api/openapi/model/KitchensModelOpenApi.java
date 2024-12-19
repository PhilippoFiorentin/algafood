package com.philippo.algafood.api.openapi.model;

import com.philippo.algafood.api.model.KitchenModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("KitchensModel")
public class KitchensModelOpenApi extends PagedModelOpenApi<KitchenModel> {

}
