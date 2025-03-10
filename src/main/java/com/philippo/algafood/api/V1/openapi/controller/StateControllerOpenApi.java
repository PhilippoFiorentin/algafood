package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.StateModel;
import com.philippo.algafood.api.V1.model.input.StateInput;
import org.springframework.hateoas.CollectionModel;

public interface StateControllerOpenApi {

    CollectionModel<StateModel> list();

    StateModel find(Long stateId);

    StateModel add(StateInput stateInput);

    StateModel update(Long stateId, StateInput stateInput);

    void delete(Long stateId);
}
