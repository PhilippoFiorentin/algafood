package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.model.input.RestaurantInput;
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
}
