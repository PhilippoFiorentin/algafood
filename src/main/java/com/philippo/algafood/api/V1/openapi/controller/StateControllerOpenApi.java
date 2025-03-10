package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.StateModel;
import com.philippo.algafood.api.V1.model.input.StateInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
public interface StateControllerOpenApi {

    CollectionModel<StateModel> list();

    StateModel find(Long stateId);

    StateModel add(StateInput stateInput);

    StateModel update(Long stateId, StateInput stateInput);

    void delete(Long stateId);
}
