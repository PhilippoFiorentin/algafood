package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.model.input.RestaurantInput;
import com.philippo.algafood.domain.model.Kitchen;
import com.philippo.algafood.domain.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class RestaurantInputDisassembler {

    public Restaurant toDomainObject(RestaurantInput restaurantInput){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantInput.getName());
        restaurant.setDeliveryFee(restaurantInput.getDeliveryFee());

        Kitchen kitchen = new Kitchen();
        kitchen.setId(restaurantInput.getKitchen().getId());

        restaurant.setKitchen(kitchen);

        return restaurant;
    }
}
