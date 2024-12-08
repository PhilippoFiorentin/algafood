package com.philippo.algafood.domain.event;

import com.philippo.algafood.domain.model.RestaurantOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderCanceledEvent {

    private RestaurantOrder order;
}
