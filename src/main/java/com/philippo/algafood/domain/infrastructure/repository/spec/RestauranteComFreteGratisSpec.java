package com.philippo.algafood.domain.infrastructure.repository.spec;

import com.philippo.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serial;
import java.math.BigDecimal;

public class RestauranteComFreteGratisSpec implements Specification<Restaurante> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Override public Predicate toPredicate (
        Root<Restaurante> root,
        CriteriaQuery<?> query,
        CriteriaBuilder builder) {
        return builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }
}
