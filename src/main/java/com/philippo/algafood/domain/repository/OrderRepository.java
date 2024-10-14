package com.philippo.algafood.domain.repository;

import com.philippo.algafood.domain.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CustomJpaRepository<Order, Long> {

}
