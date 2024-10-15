package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.exception.RestaurantOrderNotFoundException;
import com.philippo.algafood.domain.model.RestaurantOrder;
import com.philippo.algafood.domain.repository.RestaurantOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderIssuanceService {

    @Autowired
    private RestaurantOrderRepository restaurantOrderRepository;

    public RestaurantOrder findOrFail(Long orderId){
        return restaurantOrderRepository
                .findById(orderId)
                .orElseThrow(() -> new RestaurantOrderNotFoundException(orderId));
    }
}
