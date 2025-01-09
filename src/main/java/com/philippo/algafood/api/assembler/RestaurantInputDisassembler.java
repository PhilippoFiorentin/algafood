package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.model.input.RestaurantInput;
import com.philippo.algafood.domain.model.City;
import com.philippo.algafood.domain.model.Kitchen;
import com.philippo.algafood.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurant toDomainObject(RestaurantInput restaurantInput){
        return modelMapper.map(restaurantInput, Restaurant.class);
    }

    public void copyToDomainObject(RestaurantInput restaurantInput, Restaurant restaurant){

        /*
        To avoid the exception org.hibernate.HibernateException: identifier of an instance of
        com.algaworks.algafood.domain.model.Kitchen was altered from 1 to 2
        */
        restaurant.setKitchen(new Kitchen());

        if (restaurant.getAddress() != null)
            restaurant.getAddress().setCity(new City());

        modelMapper.map(restaurantInput, restaurant);
    }
}
