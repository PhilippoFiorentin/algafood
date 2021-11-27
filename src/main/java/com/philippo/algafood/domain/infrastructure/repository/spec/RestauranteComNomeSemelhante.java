package com.philippo.algafood.domain.infrastructure.repository.spec;

import com.philippo.algafood.domain.model.Restaurante;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serial;

@AllArgsConstructor
public class RestauranteComNomeSemelhante implements Specification<Restaurante> {

    private String nome;

    @Serial
    private static final long serialVersionUID = 1L;

    @Override public Predicate toPredicate (
        Root<Restaurante> root,
        CriteriaQuery<?> query,
        CriteriaBuilder builder) {
        return builder.like(root.get("nome"), "%" + nome + "%");
    }
}
