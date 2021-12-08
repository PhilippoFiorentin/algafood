package com.philippo.algafood.domain.infrastructure.repository.spec;

import com.philippo.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestaurantSpecs {

    public static Specification<Restaurante> freeDelivery(){
        return ( root, query, builder ) ->
            builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }

    public static Specification<Restaurante> similarName(String nome){
        return ( root, query, builder ) ->
            builder.like(root.get("nome"), "%" + nome + "%");
    }
}
