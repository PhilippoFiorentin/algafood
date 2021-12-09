package com.philippo.algafood.domain.repository;

import com.philippo.algafood.domain.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}
