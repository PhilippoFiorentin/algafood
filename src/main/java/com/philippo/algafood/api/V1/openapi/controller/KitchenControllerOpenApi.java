package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.KitchenModel;
import com.philippo.algafood.api.V1.model.input.KitchenInput;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

@SecurityRequirement(name = "security_auth")
public interface KitchenControllerOpenApi {

    PagedModel<KitchenModel> list(Pageable pageable);

    KitchenModel find(Long kitchenId);

    KitchenModel add(KitchenInput kitchenInput);

    KitchenModel update(Long kitchenId, KitchenInput kitchenInput);

    void delete(Long kitchenId);
}
