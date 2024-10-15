package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.model.RestaurantOrderModel;
import com.philippo.algafood.domain.model.RestaurantOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantOrderModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestaurantOrderModel toModel(RestaurantOrder restaurantOrder) {
        return modelMapper.map(restaurantOrder, RestaurantOrderModel.class);
    }

    public List<RestaurantOrderModel> toCollectionModel(List<RestaurantOrder> restaurantOrders) {
        return restaurantOrders.stream()
                .map(order -> toModel(order))
                .collect(Collectors.toList());
    }
}
