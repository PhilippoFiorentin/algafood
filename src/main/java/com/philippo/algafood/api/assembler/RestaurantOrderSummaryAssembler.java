package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.model.RestaurantOrderModel;
import com.philippo.algafood.api.model.RestaurantOrderSummaryModel;
import com.philippo.algafood.domain.model.RestaurantOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantOrderSummaryAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestaurantOrderSummaryModel toModel(RestaurantOrder restaurantOrder) {
        return modelMapper.map(restaurantOrder, RestaurantOrderSummaryModel.class);
    }

    public List<RestaurantOrderSummaryModel> toCollectionModel(List<RestaurantOrder> orders) {
        return orders.stream()
                .map(order -> toModel(order))
                .collect(Collectors.toList());
    }
}
