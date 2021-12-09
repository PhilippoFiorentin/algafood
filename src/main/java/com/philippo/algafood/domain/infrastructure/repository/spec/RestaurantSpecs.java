package com.philippo.algafood.domain.infrastructure.repository.spec;

import com.philippo.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpecs {

    public static Specification<Restaurant> freeDelivery(){
        return ( root, query, builder ) ->
            builder.equal(root.get("deliveryFee"), BigDecimal.ZERO);
    }

    public static Specification<Restaurant> similarName(String name){
        return ( root, query, builder ) ->
            builder.like(root.get("name"), "%" + name + "%");
    }
}
