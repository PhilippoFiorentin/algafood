package com.philippo.algafood.api.V1.openapi.model;

import com.philippo.algafood.api.V1.model.GroupModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("GroupsModel")
@Data
public class GroupsModelOpenApi {

    private EmbeddedGroupModelOpenApi _embedded;
    private Links _links;

    @ApiModel("EmbeddedGroupsModel")
    @Data
    public class EmbeddedGroupModelOpenApi {
        private List<GroupModel> groups;
    }
}
