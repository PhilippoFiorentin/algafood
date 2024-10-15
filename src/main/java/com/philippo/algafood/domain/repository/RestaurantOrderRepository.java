package com.philippo.algafood.domain.repository;

import com.philippo.algafood.domain.model.RestaurantOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantOrderRepository extends CustomJpaRepository<RestaurantOrder, Long> {

}
