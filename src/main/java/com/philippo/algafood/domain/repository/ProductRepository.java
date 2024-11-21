package com.philippo.algafood.domain.repository;

import com.philippo.algafood.domain.model.Product;
import com.philippo.algafood.domain.model.ProductPhoto;
import com.philippo.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQueries {

    @Query("from Product where restaurant.id = :restaurant and id = :product")
    Optional<Product> findById(@Param("restaurant") Long restaurantId, @Param("product") Long productId);

    List<Product> findAllProductsByRestaurant(Restaurant restaurant);

    @Query("from Product p where p.active = true and p.restaurant = :restaurant")
    List<Product> findActivesByRestaurant(Restaurant restaurant);

    @Query("select ph from ProductPhoto ph join ph.product pr " +
            "where pr.restaurant.id = :restaurantId and ph.product.id = :productId")
    Optional<ProductPhoto> findPhotoById(Long restaurantId, Long productId);

}
