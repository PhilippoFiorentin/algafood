package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.RestaurantOrderNotFoundException;
import com.philippo.algafood.domain.model.*;
import com.philippo.algafood.domain.repository.RestaurantOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderIssuanceService {

    @Autowired
    private RestaurantOrderRepository restaurantOrderRepository;

    @Autowired
    private RegisterCityService registerCityService;

    @Autowired
    private RegisterPaymentMethodService registerPaymentMethodService;

    @Autowired
    private RegisterUserService registerUserService;

    @Autowired
    private RegisterRestaurantService registerRestaurantService;
    @Autowired
    private RegisterProductService registerProductService;

    @Transactional
    public RestaurantOrder save(RestaurantOrder order) {
        return restaurantOrderRepository.save(order);
    }

    public RestaurantOrder findOrFail(Long orderId){
        return restaurantOrderRepository
                .findById(orderId)
                .orElseThrow(() -> new RestaurantOrderNotFoundException(orderId));
    }

    private void validateOrder(RestaurantOrder order){
        City city = registerCityService.findOrFail(order.getDeliveryAddress().getCity().getId());
        PaymentMethod paymentMethod = registerPaymentMethodService.findOrFail(order.getPaymentMethod().getId());
        User client = registerUserService.findOrFail(order.getClient().getId());
        Restaurant restaurant = registerRestaurantService.findOrFail(order.getRestaurant().getId());

        order.getDeliveryAddress().setCity(city);
        order.setPaymentMethod(paymentMethod);
        order.setClient(client);
        order.setRestaurant(restaurant);

        if(restaurant.dontAcceptPaymentMethod(paymentMethod))
            throw new BusinessException(String.format("The payment method %s is not accepted by this restaurant.",
                    paymentMethod.getDescription()));

    }
}
