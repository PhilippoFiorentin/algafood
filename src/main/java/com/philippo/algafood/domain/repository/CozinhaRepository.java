package com.philippo.algafood.domain.repository;

import com.philippo.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    List<Cozinha> findAllByNomeContaining(String nome);

    Optional<Cozinha> findByNome(String cozinha);

    Boolean existsByNome(String nome);
}
