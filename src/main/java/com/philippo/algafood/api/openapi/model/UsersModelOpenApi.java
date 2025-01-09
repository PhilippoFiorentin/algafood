package com.philippo.algafood.api.openapi.model;

import com.philippo.algafood.api.model.UserModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

@ApiModel("UsersModel")
@Data
public class UsersModelOpenApi {

    private EmbeddedUserModelOpenApi _embedded;
    private Links _links;

    @ApiModel("EmbeddedUsersModel")
    @Data
    public class EmbeddedUserModelOpenApi {
        private List<UserModel> users;
    }
}
