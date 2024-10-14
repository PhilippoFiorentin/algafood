package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.model.OrderModel;
import com.philippo.algafood.domain.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public OrderModel toModel(Order order) {
        return modelMapper.map(order, OrderModel.class);
    }

    public List<OrderModel> toCollectionModel(List<Order> orders) {
        return orders.stream()
                .map(order -> toModel(order))
                .collect(Collectors.toList());
    }
}