package com.philippo.algafood.core.modelmapper;

import com.philippo.algafood.api.model.AddressModel;
import com.philippo.algafood.api.model.input.OrderItemInput;
import com.philippo.algafood.domain.model.Address;
import com.philippo.algafood.domain.model.OrderItem;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

//		modelMapper.createTypeMap(Restaurant.class, RestaurantModel.class)
//			.addMapping(Restaurant::getDeliveryFee, RestaurantModel::setDeliveryFee);

        modelMapper.createTypeMap(OrderItemInput.class, OrderItem.class)
                .addMappings(mapper -> mapper.skip(OrderItem::setId));

        var addressToAddressModelTypeMap = modelMapper.createTypeMap(
                Address.class, AddressModel.class);

        addressToAddressModelTypeMap.<String>addMapping(
                addressSrc -> addressSrc.getCity().getState().getName(),
                (addressModelTarget, value) -> addressModelTarget.getCity().setState(value));

        return modelMapper;
    }
}
