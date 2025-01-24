package com.philippo.algafood.domain.repository;

import com.philippo.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends
    CustomJpaRepository<Restaurant, Long>,
    RestaurantRepositoryQueries,
    JpaSpecificationExecutor<Restaurant> {

    @Query("from Restaurant r join fetch r.kitchen")
    List<Restaurant> findAll ();

    List<Restaurant> findByDeliveryFeeBetween (
        BigDecimal initialFee,
        BigDecimal finalFee);

//    @Query("from Restaurant where name like %:name% and kitchen.id = :id")
    List<Restaurant> searchByName(String name, @Param("id") Long kitchen);

//    List<Restaurant> findByNameContainingAndKitchenId(String name, Long kitchen);

    Optional<Restaurant> findFirstRestaurantByNameContaining(String name);

    List<Restaurant> findTop2RestaurantByNameContaining(String name);

    Integer countByKitchenId (Long kitchen);

    boolean existsResponsible(Long restaurantId, Long userId);
}

