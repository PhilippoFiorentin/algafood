package com.philippo.algafood.domain.repository;

import com.philippo.algafood.domain.model.RestaurantOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantOrderRepository extends CustomJpaRepository<RestaurantOrder, Long> {

    Optional<RestaurantOrder> findByUuid(String uuid);

    @Query("from RestaurantOrder ro join fetch ro.client join fetch ro.restaurant r join fetch r.kitchen")
    List<RestaurantOrder> findAll();
}
