package com.philippo.algafood.domain.repository;

import com.philippo.algafood.domain.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    @Query("select max(dateUpdated) from PaymentMethod")
    OffsetDateTime getLastDateUpdated();

    @Query("select dateUpdated from PaymentMethod where id = :paymentMethodId")
    OffsetDateTime getLastDateUpdatedById(Long paymentMethodId);
}
