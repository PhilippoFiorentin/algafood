package com.philippo.algafood.api.V1.openapi.model;

import com.philippo.algafood.api.V1.model.PermissionModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("PermissionsModel")
@Data
public class PermissionsModelOpenApi {

    private EmbeddedPermissionModelOpenApi _embedded;
    private Links _links;

    @ApiModel("EmbeddedPermissionsModel")
    @Data
    public class EmbeddedPermissionModelOpenApi {
        private List<PermissionModel> permissions;
    }
}
