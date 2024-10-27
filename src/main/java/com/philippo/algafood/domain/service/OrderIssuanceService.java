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
    public RestaurantOrder issue(RestaurantOrder order) {
        validateOrder(order);
        validateItems(order);

        order.setDeliveryFee(order.getRestaurant().getDeliveryFee());
        order.calculateTotalValue();

        return restaurantOrderRepository.save(order);
    }

    public RestaurantOrder findOrFail(Long orderId){
        return restaurantOrderRepository
                .findById(orderId)
                .orElseThrow(() -> new RestaurantOrderNotFoundException(orderId));
    }

    private void validateOrder(RestaurantOrder order){
        City city = registerCityService.findOrFail(order.getDeliveryAddress().getCity().getId());
        User client = registerUserService.findOrFail(order.getClient().getId());
        Restaurant restaurant = registerRestaurantService.findOrFail(order.getRestaurant().getId());
        PaymentMethod paymentMethod = registerPaymentMethodService.findOrFail(order.getPaymentMethod().getId());

        order.getDeliveryAddress().setCity(city);
        order.setClient(client);
        order.setRestaurant(restaurant);
        order.setPaymentMethod(paymentMethod);

        if(restaurant.dontAcceptPaymentMethod(paymentMethod))
            throw new BusinessException(String.format("The payment method %s is not accepted by this restaurant.",
                    paymentMethod.getDescription()));
    }

    private void validateItems(RestaurantOrder order){
        order.getItems().forEach(item -> {
            Product product = registerProductService
                    .findOrFail(order.getRestaurant().getId(), item.getProduct().getId());

            item.setRestaurantOrder(order);
            item.setProduct(product);
            item.setUnitaryPrice(item.getProduct().getPrice());
        });
    }
}
