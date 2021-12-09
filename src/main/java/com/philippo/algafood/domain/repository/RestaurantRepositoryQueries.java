package com.philippo.algafood.domain.repository;

import com.philippo.algafood.domain.model.Restaurant;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepositoryQueries {
    List<Restaurant> find (String name, BigDecimal initialDeliveryFee, BigDecimal finalDeliveryFee);

    List<Restaurant> findFreeDelivery(String name);
}
