package com.philippo.algafood.api.V1.assembler;

import com.philippo.algafood.api.V1.model.input.RestaurantOrderInput;
import com.philippo.algafood.domain.model.RestaurantOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantOrderInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public RestaurantOrder toDomainObject(RestaurantOrderInput restaurantOrderInput){
        return modelMapper.map(restaurantOrderInput, RestaurantOrder.class);
    }

    public void copyToDomainObject(RestaurantOrderInput restaurantOrderInput, RestaurantOrder restaurantOrder){
        modelMapper.map(restaurantOrderInput, restaurantOrder);
    }
}
