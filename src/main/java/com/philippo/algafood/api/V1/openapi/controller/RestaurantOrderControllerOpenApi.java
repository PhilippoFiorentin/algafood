package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.RestaurantOrderModel;
import com.philippo.algafood.api.V1.model.RestaurantOrderSummaryModel;
import com.philippo.algafood.api.V1.model.input.RestaurantOrderInput;
import com.philippo.algafood.domain.filter.OrderFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

public interface RestaurantOrderControllerOpenApi {

    PagedModel<RestaurantOrderSummaryModel> search(Pageable pageable, OrderFilter filter);

    RestaurantOrderModel find(String orderUuid);

    RestaurantOrderModel add(RestaurantOrderInput orderInput);
}
